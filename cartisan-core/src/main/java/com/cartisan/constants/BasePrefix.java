package com.cartisan.constants;

import org.apache.commons.lang3.StringUtils;

/**
 * @author colin
 */
public abstract class BasePrefix implements KeyPrefix {

    private final long expireMilliseconds;
    private final String prefix;

    public BasePrefix(String prefix) {
        this(1000 * 60 * 60 * 24, prefix);
    }

    public BasePrefix(long expireMilliseconds, String prefix) {
        this.expireMilliseconds = expireMilliseconds;
        this.prefix = prefix;
    }

    /**
     * 0 为永不过期
     * 默认 1000 * 60 * 60 * 24，一天
     *
     * @return 超时时间（毫秒）
     */
    @Override
    public long expireMilliseconds() {
        return expireMilliseconds;
    }

    @Override
    public String getFullKey(String key) {
        if (StringUtils.isEmpty(key)) {
            return businessPrefix() + ":" + prefix;
        }

        return businessPrefix() + ":" + prefix + ":" + key;
    }

    /**
     * 设置业务前缀
     *
     * @return 业务前缀
     */
    protected abstract String businessPrefix();
}
