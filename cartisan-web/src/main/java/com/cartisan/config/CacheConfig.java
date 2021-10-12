package com.cartisan.config;

import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.time.Duration;

/**
 * @author colin
 */
@Configuration
@EnableCaching
public class CacheConfig extends CachingConfigurerSupport {
    @Bean
    public RedisCacheManager redisCacheManager(
            RedisConnectionFactory redisConnectionFactory, RedisSerializer<?> redisSerializer) {
        return RedisCacheManager.builder(redisConnectionFactory)
                .cacheDefaults(redisCacheConfiguration(redisSerializer))
                .build();
    }

    /**
     * 配置 redisCache 的序列化方式。
     * RedisCacheConfiguration 默认使用的是 JdkSerializationRedisSerializer
     * 这会导致 key 与 value 在 redis 中，都是人不可读。
     *
     * @return RedisCacheConfiguration
     */
    private RedisCacheConfiguration redisCacheConfiguration(RedisSerializer<?> redisSerializer) {
        RedisCacheConfiguration redisCacheConfiguration = RedisCacheConfiguration.defaultCacheConfig();
        // TODO: 确定前缀与缓存过期时间的处理
        redisCacheConfiguration = redisCacheConfiguration
                // 设置缓存前缀
                // .prefixKeysWith("cache:")
                // 禁止缓存 null 值
                .disableCachingNullValues()
                // 设置 key 序列化
                .serializeKeysWith(keyPair())
                // 设置 value 序列化
                .serializeValuesWith(valuePair(redisSerializer))
                // 设置过期时间
                .entryTtl(Duration.ofMinutes(60));

        return redisCacheConfiguration;
    }

    private RedisSerializationContext.SerializationPair<?> valuePair(RedisSerializer<?> redisSerializer) {
        return RedisSerializationContext.SerializationPair.fromSerializer(redisSerializer);
    }

    private RedisSerializationContext.SerializationPair<String> keyPair() {
        return RedisSerializationContext.SerializationPair.fromSerializer(StringRedisSerializer.UTF_8);
    }

    @Override
    public KeyGenerator keyGenerator() {
        // 当没有指定缓存的 key时来根据类名、方法名和方法参数来生成key
        return (target, method, params) -> {
            StringBuilder sb = new StringBuilder();
            sb.append(target.getClass().getName())
                    .append(':')
                    .append(method.getName());
            if (params.length > 0) {
                sb.append('[');
                for (Object obj : params) {
                    if (obj != null) {
                        sb.append(obj.toString());
                    }
                }
                sb.append(']');
            }
            return sb.toString();
        };
    }
}
