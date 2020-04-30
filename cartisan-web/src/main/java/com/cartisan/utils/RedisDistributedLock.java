package com.cartisan.utils;

import com.google.common.collect.ImmutableList;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * @author colin
 */
@Component
public class RedisDistributedLock {
    private static final Long RELEASE_SUCCESS = 1L;

    private final RedisTemplate<Object, Object> redisTemplate;

    public RedisDistributedLock(RedisTemplate<Object, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    /**
     * 尝试获取分布式锁
     *
     * @param lockKey    锁
     * @param requestId  请求标识
     * @param expireTime 超期时间
     * @return 是否获取成功
     */
    public Boolean tryGet(String lockKey, String requestId, int expireTime) {

        return redisTemplate.opsForValue().setIfAbsent(lockKey, requestId, expireTime, TimeUnit.MILLISECONDS);
    }



    /**
     * 释放分布式锁
     *
     * @param lockKey   锁
     * @param requestId 请求标识
     * @return 是否释放成功
     */
    public Boolean release(String lockKey, String requestId) {

        String luaScript = "if redis.call('get', KEYS[1]) == ARGV[1] then return redis.call('del', KEYS[1]) else return 0 end";
        final DefaultRedisScript<Long> redisScript = new DefaultRedisScript<>(luaScript, Long.class);
        final Long result = redisTemplate.execute(redisScript, ImmutableList.of(lockKey), requestId);

        if (RELEASE_SUCCESS.equals(result)) {
            return true;
        }
        return false;

    }

}
