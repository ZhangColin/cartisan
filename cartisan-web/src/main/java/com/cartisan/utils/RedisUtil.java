package com.cartisan.utils;

import com.cartisan.constants.KeyPrefix;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.DataType;
import org.springframework.data.redis.core.*;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toSet;

/**
 * @author colin
 */
@Component
public class RedisUtil {
    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Autowired
    private ValueOperations<String, String> valueOperator;
    @Autowired
    private HashOperations<String, String, Object> hashOperator;
    @Autowired
    private ListOperations<String, Object> listOperator;
    @Autowired
    private SetOperations<String, Object> setOperator;
    private SetOperations<String, String> setOperatorStr;
    @Autowired
    private ZSetOperations<String, Object> zSetOperator;

    @Autowired
    private ObjectMapper objectMapper = new ObjectMapper();

    /**
     * 将数据存入缓存
     *
     * @param key
     * @param value
     */
    public void saveString(String key, String value) {
        valueOperator.set(key, value);
    }

    public void saveString(String key, String value, long seconds) {
        valueOperator.set(key, value, seconds, TimeUnit.SECONDS);
    }

    /**
     * 将数据存入 Set 中
     *
     * @param key
     * @param value
     */
    public void saveToSet(String key, String value) {
        setOperatorStr.add(key, value);
    }

    /**
     * 从 Set 中获取数据
     *
     * @param key
     * @return
     */
    public String getFromSet(String key) {
        return setOperatorStr.pop(key);
    }

    /**
     * 保存复杂类型数据到缓存
     *
     * @param key
     * @param value
     */
    public void saveObject(String key, Object value) {
        redisTemplate.opsForValue().set(key, value);
    }

    public void saveObject(String key, Object value, long seconds) {
        if (seconds > 0) {
            redisTemplate.opsForValue().set(key, value, seconds, TimeUnit.SECONDS);
        } else {
            this.saveObject(key, value);
        }
    }

    public void saveToQueue(String key, String value) {
        listOperator.leftPush(key, value);
    }

    public void saveToQueue(String key, String value, long size) {
        if (size > 0 && listOperator.size(key) >= size) {
            listOperator.rightPop(key);
        }
        listOperator.leftPush(key, value);
    }

    public Object popQueue(String key) {
        return listOperator.rightPop(key);
    }

    public List<Object> getFromQueue(String key) {
        return getFromQueue(key, 0);
    }

    public List<Object> getFromQueue(String key, long size) {
        boolean flag = redisTemplate.execute((RedisCallback<Boolean>) connection -> connection.exists(key.getBytes()));
        if (flag) {
            return new ArrayList<>();
        }
        if (size > 0) {
            return listOperator.range(key,0, size - 1);
        }
        else{
            return listOperator.range(key, 0, listOperator.size(key) - 1);
        }
    }

    public void hashSet(String name, String key, Object value) {
        hashOperator.put(name, key, value);
    }

    public void hashSet(String name, Map<String, Object> hashMap) {
        hashOperator.putAll(name, hashMap);
    }

    public Map<String, Object> hashGetAll(String key) {
        return hashOperator.entries(key);
    }

    public <T> void hashSetByMapper(String name, String key, T t) throws JsonProcessingException {
        hashSet(name, key, objectMapper.writeValueAsString(t));
    }

    public void hsetnx(String name, String key, String value) {
        hashOperator.putIfAbsent(name, key, value);
    }

    public <T> void hsetnx(String name, String key, T t) throws JsonProcessingException {
        hsetnx(name, key, objectMapper.writeValueAsString(t));
    }

    public void hdel(String name, String key) {
        hashOperator.delete(name, key);
    }

    public Object getObject(String key) {
        return key == null ? null : redisTemplate.opsForValue().get(key);
    }

    public String get(String key) {
        return valueOperator.get(key);
    }

    /**
     * 将 key的值保存为 value ，当且仅当 key 不存在。 若给定的 key 已经存在，则 SETNX 不做任何动作。 SETNX 是『SET
     * if Not eXists』(如果不存在，则 SET)的简写。 <br>
     * 保存成功，返回 true <br>
     * 保存失败，返回 false
     */
    public boolean saveNX(String key, String value) {
        return valueOperator.setIfAbsent(key, value);
    }

    /**
     * 将 key的值保存为 value ，当且仅当 key 不存在。 若给定的 key 已经存在，则 SETNX 不做任何动作。 SETNX 是『SET
     * if Not eXists』(如果不存在，则 SET)的简写。 <br>
     * 保存成功，返回 true <br>
     * 保存失败，返回 false
     *
     * @param key
     * @param val
     * @param expire 单位：秒
     *               超时时间
     * @return 保存成功，返回 true 否则返回 false
     */
    public boolean saveNX(String key, String val, int expire) {
        boolean result = saveNX(key, val);
        if (result) {
            redisTemplate.expire(key, expire, TimeUnit.SECONDS);
        }
        return result;
    }

    /**
     * 将自增变量存入缓存
     */
    public void saveSeq(String key, long seqNo) {
        redisTemplate.delete(key);
        valueOperator.increment(key, seqNo);
    }

    public Long getSeqNext(String key) {
        return redisTemplate.execute((RedisCallback<Long>) connection -> connection.incr(key.getBytes()));
    }

    /**
     * 取得序列值的下一个，增加 value
     *
     * @param key
     * @param value
     * @return
     */
    public Long getSeqNext(String key, long value) {
        return redisTemplate.execute((RedisCallback<Long>) connection -> connection.incrBy(key.getBytes(), value));
    }

    public Long getSeqBack(String key) {
        return redisTemplate.execute((RedisCallback<Long>) connection -> connection.decr(key.getBytes()));
    }

    /**
     * 将递增浮点数存入缓存
     */
    public void saveSeq(String key, float data) {
        redisTemplate.delete(key);
        valueOperator.increment(key, data);
    }

    /**
     * 默认过期时长，单位：秒
     */
    public final static long DEFAULT_EXPIRE = 60 * 60 * 24;

    /**
     * 不设置过期时长
     */
    public final static long NOT_EXPIRE = -1;

    /**
     * 获取Key的全路径
     */
    public String getFullKey(String key) {
        return ":" + key;
    }


    /**
     * 判断key是否存在
     */
    public boolean existsKey(String key) {
        return redisTemplate.hasKey(getFullKey(key));
    }

    /**
     * 判断key存储的值类型
     *
     * @return DataType[string、list、set、zSet、hash]
     */
    public DataType typeKey(String key) {
        return redisTemplate.type(getFullKey(key));
    }

    /**
     * 重命名key. 如果newKey已经存在，则newKey的原值被覆盖
     */
    public void renameKey(String oldKey, String newKey) {
        redisTemplate.rename(getFullKey(oldKey), getFullKey(newKey));
    }

    /**
     * newKey不存在时才重命名
     *
     * @param oldKey oldKey
     * @param newKey newKey
     * @return 修改成功返回true
     */
    public boolean renameKeyNx(String oldKey, String newKey) {
        return redisTemplate.renameIfAbsent(getFullKey(oldKey), getFullKey(newKey));
    }

    /**
     * 删除key
     */
    public void deleteKey(String key) {
        redisTemplate.delete(key);
    }

    /**
     * 删除key
     */
    public void deleteKey(String... keys) {
        Set<String> ks = Stream.of(keys).map(k -> getFullKey(k)).collect(toSet());
        redisTemplate.delete(ks);
    }

    /**
     * 删除key
     */
    public void deleteKey(Collection<String> keys) {
        Set<String> ks = keys.stream().map(k -> getFullKey(k)).collect(toSet());
        redisTemplate.delete(ks);
    }

    /**
     * 设置key的生命周期，单位秒
     *
     * @param key      key
     * @param time     时间数
     * @param timeUnit TimeUnit 时间单位
     */
    public void expireKey(String key, long time, TimeUnit timeUnit) {
        redisTemplate.expire(key, time, timeUnit);
    }

    /**
     * 设置key在指定的日期过期
     */
    public void expireKeyAt(String key, Date date) {
        redisTemplate.expireAt(key, date);
    }

    /**
     * 查询key的生命周期
     *
     * @param key      key
     * @param timeUnit TimeUnit 时间单位
     * @return 指定时间单位的时间数
     */
    public long getKeyExpire(String key, TimeUnit timeUnit) {
        return redisTemplate.getExpire(key, timeUnit);
    }

    /**
     * 将key设置为永久有效
     */
    public void persistKey(String key) {
        redisTemplate.persist(key);
    }

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

    public Double incrFloat(String key, double incrBy) {
        return redisTemplate.execute((RedisCallback<Double>) connection -> {
            return connection.incrBy(key.getBytes(), incrBy);
        });
    }

    /**
     * 从hash集合里取得
     *
     * @param hName
     * @param key
     * @return
     */
    public Object hashGet(String hName, String key) {
        return hashOperator.get(hName, key);
    }

    public <T> T hashGet(String hName, String key, Class<T> clazz) throws JsonParseException, JsonMappingException, IOException {
        return objectMapper.readValue((String) hashGet(hName, key), clazz);
    }

    /**
     * 判断是否缓存了数据
     *
     * @param key
     *            数据KEY
     * @return 判断是否缓存了
     */
    public boolean exists(String key) {
        return redisTemplate.execute((RedisCallback<Boolean>) connection -> connection.exists(key.getBytes()));
    }

    /**
     * 判断hash集合中是否缓存了数据, 有问题
     *
     * @param hName
     * @param key
     * @return 判断是否缓存了
     */
    public boolean hashExists(String hName, String key) {
        return hashOperator.hasKey(hName, key);
    }

    /**
     * 判断是否缓存在指定的集合中
     *
     * @param key
     * @param val
     *
     * @return 判断是否缓存了
     */
    public boolean isMember(String key, String val) {
        return setOperator.isMember(key, val);
    }

    /**
     * 从缓存中删除数据
     *
     * @return
     */
    public void delKey(String key) {
        redisTemplate.delete(key);
//        redisTemplate.execute((RedisCallback<Long>) connection -> connection.del(key.getBytes()));
    }

    /**
     * 设置超时时间
     *
     * @param key
     * @param seconds
     */
    public void expire(String key, int seconds) {
        redisTemplate.expire(key, seconds, TimeUnit.SECONDS);
    }

    /**
     * 列出set中所有成员
     *
     * @param setName
     * @return
     */
    public Set<Object> listSet(String setName) {
        return setOperator.members(setName);
    }

    /**
     * 向set中追加一个值
     *
     * @param setName
     * @param value
     */
    public void appendSet(String setName, String value) {
        setOperator.add(setName, value);
    }

    /**
     * 向sorted set中追加一个值
     *
     * @param key
     * @param score
     * @param member
     */
    public void saveToSortedset(String key, String member, Double score) {
        zSetOperator.add(key, member, score);
    }

    /**
     * 根据成员名取得sorted sort分数
     *
     * @param key
     *            set名
     * @param member
     *            成员名
     * @return 分数
     */
    public Double getMemberScore(String key, String member) {
        return zSetOperator.score(key, member);
    }

    /**
     * 从sorted set删除一个值
     *
     * @param key
     * @param member
     */
    public  void delFromSortedset(String key, String member) {
        zSetOperator.remove(key, member);
    }

    /**
     * 逆序列出sorted set包括分数的set列表
     *
     * @param key
     *            set名
     * @param start
     *            开始位置
     * @param end
     *            结束位置
     * @return 列表
     */
    public Set<ZSetOperations.TypedTuple<Object>> listSortedsetRev(String key, int start, int end) {
        return zSetOperator.reverseRangeWithScores(key, start, end);
    }

    /**
     * 逆序取得sorted sort排名
     *
     * @param key
     *            set名
     * @param member
     *            成员名
     * @return 排名
     */
    public Long getReverseRank(String key, String member) {
        return zSetOperator.reverseRank(key, member);
    }

    /**
     * 从hashmap中删除一个值
     *
     * @param key
     * @param field
     */
    public void delFromMap(String key, String field) {
        hashOperator.delete(key, field);
    }

    /**
     * 将所有指定的值插入到存于 key 的列表的头部。如果 key 不存在，那么在进行 push 操作前会创建一个空列表
     *
     * @param <T>
     *
     * @param key
     * @param value
     * @return
     * @throws JsonProcessingException
     */
    public <T> Long lpush(String key, T value) throws JsonProcessingException {
        return listOperator.leftPush(key, objectMapper.writeValueAsString(value));
    }

    /**
     * 只有当 key 已经存在并且存着一个 list 的时候，在这个 key 下面的 list 的头部插入 value。 与 LPUSH 相反，当
     * key 不存在的时候不会进行任何操作
     *
     * @param key
     * @param value
     * @return
     * @throws JsonProcessingException
     */
    public <T> Long lpushx(String key, T value) throws JsonProcessingException {
        return listOperator.leftPushIfPresent(key, objectMapper.writeValueAsString(value));
    }

    /**
     * 返回存储在 key 里的list的长度。 如果 key 不存在，那么就被看作是空list，并且返回长度为 0
     *
     * @param key
     * @return
     */
    public Long llen(String key) {
        return listOperator.size(key);
    }

    /**
     * 返回存储在 key 的列表里指定范围内的元素。 start 和 end
     * 偏移量都是基于0的下标，即list的第一个元素下标是0（list的表头），第二个元素下标是1，以此类推
     *
     * @param key
     * @return
     */
    public List<Object> lrange(String key, long start, long end) {
        return listOperator.range(key, start, end);
    }

    /**
     * 移除并且返回 key 对应的 list 的第一个元素
     *
     * @param key
     * @return
     */
    public Object lpop(String key) {
        return listOperator.leftPop(key);
    }
}
