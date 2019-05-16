package com.cartisan.common.constants;

/**
 * @author colin
 */
public interface KeyPrefix {
    /**
     * @return 超时时间（毫秒）
     */
    long expireMilliseconds();


    /**
     * 生成 业务前缀:前缀:键 格式的完整键值
     * @param key
     * @return 生成最终完整的键值
     */
    String getFullKey(String key);
}
