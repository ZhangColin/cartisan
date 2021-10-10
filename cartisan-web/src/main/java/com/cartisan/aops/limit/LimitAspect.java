package com.cartisan.aops.limit;

import com.cartisan.constants.CodeMessage;
import com.cartisan.exception.CartisanException;
import com.google.common.collect.ImmutableList;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.data.redis.core.script.RedisScript;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.Objects;

import static com.cartisan.utils.IpUtil.getIpFromRequest;
import static com.cartisan.utils.RequestHolder.getHttpServletRequest;
import static org.apache.commons.lang3.StringUtils.join;

/**
 * @author colin
 */
@Aspect
@Component
@Slf4j
public class LimitAspect {
    private RedisTemplate<Object, Object> redisTemplate;

    @Autowired
    public LimitAspect(RedisTemplate<Object, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    @Pointcut("@annotation(com.cartisan.aops.limit.Limit)")
    public void pointcut() {

    }

    @Around("pointcut()")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        final HttpServletRequest request = getHttpServletRequest();
        final MethodSignature signature = (MethodSignature)joinPoint.getSignature();
        final Method method = signature.getMethod();
        final Limit limit = method.getAnnotation(Limit.class);
        final LimitType limitType = limit.limitType();
        String key = limit.key();
        if (StringUtils.isEmpty(key)) {
            if (limitType == LimitType.IP) {
                key = getIpFromRequest(request);
            } else {
                key = signature.getName();
            }
        }

        final ImmutableList<Object> keys = ImmutableList.of(join(limit.prefix(), "_", key, "_", request.getRequestURI().replace("/", "_")));

        final String luaScript = buildLuaScript();
        final RedisScript<Number> redisScript = new DefaultRedisScript<>(luaScript, Number.class);
        final Number count = redisTemplate.execute(redisScript, keys, limit.count(), limit.period());

        if (!Objects.isNull(count) && count.intValue() <= limit.count()) {
            log.info("第[{}]次访问[{}]的接口，key为[{}]", count, limit.name(), keys);
            return joinPoint.proceed();
        } else {
            throw new CartisanException(CodeMessage.LIMIT_ERROR);
        }
    }

    /**
     * 限流脚本
     */
    private String buildLuaScript() {
        return "local c" +
                "\nc = redis.call('get',KEYS[1])" +
                "\nif c and tonumber(c) > tonumber(ARGV[1]) then" +
                "\nreturn c;" +
                "\nend" +
                "\nc = redis.call('incr',KEYS[1])" +
                "\nif tonumber(c) == 1 then" +
                "\nredis.call('expire',KEYS[1],ARGV[2])" +
                "\nend" +
                "\nreturn c;";
    }
}
