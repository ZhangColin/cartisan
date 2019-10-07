package com.cartisan.common.utils;

import com.cartisan.common.constants.KeyPrefix;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.*;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * @author colin
 */
@Component
public class RedisUtil {
    @Autowired
    private RedisTemplate redisTemplate;
//    @Autowired
//    private ValueOperations<String, String> valueOperator;
//    @Autowired
//    private HashOperations<String, String, Object> hashOperator;
//    @Autowired
//    private ListOperations<String, Object> listOperator;
//    @Autowired
//    private SetOperations<String, Object> setOperator;
//    @Autowired
//    private ZSetOperations<String, Object> zSetOperator;

//    /**
//     * 默认过期时长，单位：秒
//     */
//    public final static long DEFAULT_EXPIRE = 60 * 60 * 24;
//
//    /**
//     * 不设置过期时长
//     */
//    public final static long NOT_EXPIRE = -1;
//
//    /**
//     * 获取Key的全路径
//     */
//    public String getFullKey(String key) {
//        return ":" + key;
//    }
//
//
//    /**
//     * 判断key是否存在
//     */
//    public boolean existsKey(String key) {
//        return redisTemplate.hasKey(getFullKey(key));
//    }
//
//    /**
//     * 判断key存储的值类型
//     * @return DataType[string、list、set、zSet、hash]
//     */
//    public DataType typeKey(String key) {
//        return redisTemplate.type(getFullKey(key));
//    }
//
//    /**
//     * 重命名key. 如果newKey已经存在，则newKey的原值被覆盖
//     */
//    public void renameKey(String oldKey, String newKey) {
//        redisTemplate.rename(getFullKey(oldKey), getFullKey(newKey));
//    }
//
//    /**
//     * newKey不存在时才重命名
//     * @param oldKey oldKey
//     * @param newKey newKey
//     * @return 修改成功返回true
//     */
//    public boolean renameKeyNx(String oldKey, String newKey) {
//        return redisTemplate.renameIfAbsent(getFullKey(oldKey), getFullKey(newKey));
//    }
//
//    /**
//     * 删除key
//     */
//    public void deleteKey(String key) {
//        redisTemplate.delete(key);
//    }
//
//    /**
//     * 删除key
//     */
//    public void deleteKey(String... keys) {
//        Set<String> ks = Stream.of(keys).map(k -> getFullKey(k)).collect(Collectors.toSet());
//        redisTemplate.delete(ks);
//    }
//
//    /**
//     * 删除key
//     */
//    public void deleteKey(Collection<String> keys) {
//        Set<String> ks = keys.stream().map(k -> getFullKey(k)).collect(Collectors.toSet());
//        redisTemplate.delete(ks);
//    }
//
//    /**
//     * 设置key的生命周期，单位秒
//     * @param key      key
//     * @param time     时间数
//     * @param timeUnit TimeUnit 时间单位
//     */
//    public void expireKey(String key, long time, TimeUnit timeUnit) {
//        redisTemplate.expire(key, time, timeUnit);
//    }
//
//    /**
//     * 设置key在指定的日期过期
//     */
//    public void expireKeyAt(String key, Date date) {
//        redisTemplate.expireAt(key, date);
//    }
//
//    /**
//     * 查询key的生命周期
//     * @param key      key
//     * @param timeUnit TimeUnit 时间单位
//     * @return 指定时间单位的时间数
//     */
//    public long getKeyExpire(String key, TimeUnit timeUnit) {
//        return redisTemplate.getExpire(key, timeUnit);
//    }
//
//    /**
//     * 将key设置为永久有效
//     */
//    public void persistKey(String key) {
//        redisTemplate.persist(key);
//    }

//    public RedisTemplate getRedisTemplate() {
//        return redisTemplate;
//    }
//
//    public ValueOperations<String, String> getValueOperator() {
//        return valueOperator;
//    }
//
//    public HashOperations<String, String, Object> getHashOperator() {
//        return hashOperator;
//    }
//
//    public ListOperations<String, Object> getListOperator() {
//        return listOperator;
//    }
//
//    public SetOperations<String, Object> getSetOperator() {
//        return setOperator;
//    }
//
//    public ZSetOperations<String, Object> getZSetOperator() {
//        return zSetOperator;
//    }

    public <T> void set(KeyPrefix prefix, String key, T value) {
        this.redisTemplate.opsForValue().set(prefix.getFullKey(key), value, prefix.expireMilliseconds(), TimeUnit.MILLISECONDS);
    }

    public <T> T get(KeyPrefix prefix, String key) {
        return (T) this.redisTemplate.opsForValue().get(prefix.getFullKey(key));
    }

    public void delete(KeyPrefix prefix, String key) {
        this.redisTemplate.delete(prefix.getFullKey(key));
    }

    public void renewal(KeyPrefix prefix, String key) {
        redisTemplate.expire(prefix.getFullKey(key), prefix.expireMilliseconds(), TimeUnit.MILLISECONDS);
    }
}
