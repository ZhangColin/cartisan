package com.cartisan.utils;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.map.LRUMap;

/**
 * @author colin
 */
@Slf4j
public class IdempotentUtil {
    private static LRUMap<String, Integer> requestCache = new LRUMap<>(100);

    public static boolean judge(String id, Object lockClass) {
        synchronized (lockClass) {
            if (requestCache.containsKey(id)) {
                log.warn("请匆重复提交。");
                return false;
            }
            requestCache.put(id, 1);
        }

        return true;
    }
}
