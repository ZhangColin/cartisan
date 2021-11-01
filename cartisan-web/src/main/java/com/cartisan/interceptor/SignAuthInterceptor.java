package com.cartisan.interceptor;

import com.beust.jcommander.internal.Lists;
import com.cartisan.constant.CodeMessage;
import com.cartisan.exception.CartisanException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * 防篡改、防重放攻击拦截器
 *
 * @author colin
 */
@Slf4j
public class SignAuthInterceptor implements HandlerInterceptor {
    private final RedisTemplate<String, String> redisTemplate;
    private final String key;

    public SignAuthInterceptor(RedisTemplate<String, String> redisTemplate, String key) {
        this.redisTemplate = redisTemplate;
        this.key = key;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 获取时间戳
        final String timestamp = request.getHeader("timestamp");

        // 获取随机字符串
        final String nonceStr = request.getHeader("nonceStr");

        // 获取签名
        final String signature = request.getHeader("signature");

        // 判断时间是否大于 xx 秒（防止重放攻击）
        long NONCE_STR_TIMEOUT_SECONDS = 60L;
        if (StringUtils.isEmpty(timestamp) || Long.parseLong(timestamp) + (1000 * NONCE_STR_TIMEOUT_SECONDS) > System.currentTimeMillis()) {
            throw new CartisanException(CodeMessage.FAIL.fillArgs("invalid timestamp"));
        }

        // 判断该用户的 nonceStr 参数是否已经在 redis 中（防止短时间内的重放攻击）
        final Boolean haveNonceStr = redisTemplate.hasKey(nonceStr);
        if (StringUtils.isEmpty(nonceStr) || Objects.isNull(haveNonceStr) || haveNonceStr) {
            throw new CartisanException(CodeMessage.FAIL.fillArgs("invalid nonceStr"));
        }

        // 对请求头参数进行签名
        if (StringUtils.isEmpty(signature) || !Objects.equals(signature, this.signature(timestamp, nonceStr, request))) {
            throw new CartisanException(CodeMessage.FAIL.fillArgs("invalid signature"));
        }

        // 将本次用户请求的 nonceStr 参数存到 redis 中设置 xx 秒后自动删除
        redisTemplate.opsForValue().set(nonceStr, nonceStr, NONCE_STR_TIMEOUT_SECONDS, TimeUnit.SECONDS);

        return true;
    }

    private String signature(String timestamp, String nonceStr, HttpServletRequest request) throws UnsupportedEncodingException {
        final HashMap<String, String> params = new HashMap<>(16);
        final Enumeration<String> parameterNames = request.getParameterNames();

        while (parameterNames.hasMoreElements()) {
            final String name = parameterNames.nextElement();
            final String value = request.getParameter(name);

            params.put(name, URLEncoder.encode(value, "UTF-8"));
        }

        final String qs = String.format("%s&timestamp=%s&nonceStr=%s&sign=%s", this.sortQueryParamString(params), timestamp, nonceStr, key);

        log.info("qs: {}", qs);
        String sign = DigestUtils.md5Hex(qs).toLowerCase();
        log.info("sign: {}", sign);

        return sign;
    }

    /**
     * 按照字母顺序进行升序排序
     *
     * @param params 请求参数。
     * @return 排序后的结果
     */
    private String sortQueryParamString(Map<String, String> params) {
        final List<String> listKeys = Lists.newArrayList(params.keySet());
        Collections.sort(listKeys);

        final StringBuilder content = new StringBuilder();

        for (String param : listKeys) {
            content.append(param).append("=").append(params.get(param)).append("&");
        }

        if (content.length() > 0) {
            return content.substring(0, content.length() - 1);
        }

        return "";
    }
}
