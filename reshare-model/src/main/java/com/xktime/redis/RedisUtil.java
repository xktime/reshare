package com.xktime.redis;

import org.springframework.data.redis.core.*;
import org.springframework.lang.NonNull;

import java.io.Serializable;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public class RedisUtil {

    private RedisTemplate redisTemplate;


    public RedisUtil(@NonNull RedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    /**
     * 写入缓存
     *
     * @param key
     * @param uniqueId
     * @param value
     * @return
     */
    public <V extends Serializable> boolean set(final RedisCommonKey key, long uniqueId, V value) {
        boolean result = false;
        try {
            ValueOperations<String, V> operations = redisTemplate.opsForValue();
            operations.set(RedisKeyUtil.getUniqueKey(key, uniqueId), value);
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 写入缓存设置时效时间
     * @param key
     * @param uniqueId
     * @param value
     * @param expireTime
     * @param timeUnit
     * @return
     */
    public <V extends Serializable> boolean set(final RedisCommonKey key, long uniqueId, V value, Long expireTime, TimeUnit timeUnit) {
        boolean result = false;
        try {
            String uniqueKey = RedisKeyUtil.getUniqueKey(key, uniqueId);
            ValueOperations<String, V> operations = redisTemplate.opsForValue();
            operations.set(uniqueKey, value);
            redisTemplate.expire(uniqueKey, expireTime, timeUnit);
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 判断缓存中是否有对应的value
     *
     * @param key
     * @return
     */
    public boolean exists(final RedisCommonKey key) {
        return redisTemplate.hasKey(key.name());
    }

    /**
     * 判断缓存中是否有对应的value
     *
     * @param key
     * @param uniqueId
     * @return
     */
    public boolean exists(final RedisCommonKey key, long uniqueId) {
        Boolean result = redisTemplate.hasKey(RedisKeyUtil.getUniqueKey(key, uniqueId));
        return result == null ? false : result;
    }

    /**
     * 读取缓存
     *
     * @param key
     * @return
     */
    public <V extends Serializable> V get(final RedisCommonKey key) {
        ValueOperations<String, V> operations = redisTemplate.opsForValue();
        return operations.get(key);
    }

    /**
     * 读取缓存
     *
     * @param key
     * @param uniqueId
     * @return
     */
    public <V extends Serializable> V get(final RedisCommonKey key, long uniqueId) {
        ValueOperations<String, V> operations = redisTemplate.opsForValue();
        return operations.get(RedisKeyUtil.getUniqueKey(key, uniqueId));
    }

    /**
     * 哈希 添加
     *
     * @param key
     * @param hashKey
     * @param value
     */
    public <K extends Serializable, V extends Serializable> void mapSet(final RedisCommonKey key, K hashKey, V value) {
        HashOperations<String, K, V> hash = redisTemplate.opsForHash();
        hash.put(key.name(), hashKey, value);
    }

    /**
     * 哈希获取数据
     *
     * @param key
     * @param hashKey
     * @return
     */
    public <K extends Serializable, V extends Serializable> V mapGet(final RedisCommonKey key, K hashKey) {
        HashOperations<String, K, V> hash = redisTemplate.opsForHash();
        return hash.get(key.name(), hashKey);
    }

    /**
     * 哈希是否存在
     *
     * @param key
     * @param hashKey
     * @return
     */
    public <K extends Serializable> boolean mapExists(final RedisCommonKey key, K hashKey) {
        HashOperations<String, K, Object> hash = redisTemplate.opsForHash();
        return hash.hasKey(key.name(),hashKey);
    }

    /**
     * 列表从右侧添加
     *
     * @param key
     * @param value
     */
    public <V extends Serializable> void listAddTail(final RedisCommonKey key, V value) {
        ListOperations<String, V> list = redisTemplate.opsForList();
        list.rightPush(key.name(), value);
    }

    /**
     * 列表从左侧添加
     *
     * @param key
     * @param value
     */
    public <V extends Serializable> void listAddHead(final RedisCommonKey key, V value) {
        ListOperations<String, V> list = redisTemplate.opsForList();
        list.leftPush(key.name(), value);
    }

    /**
     * 列表获取
     *
     * @param key
     * @param start
     * @param end
     * @return
     */
    public <V extends Serializable> List<V> listRange(final RedisCommonKey key, long start, long end) {
        ListOperations<String, V> list = redisTemplate.opsForList();
        return list.range(key.name(), start, end);
    }

    /**
     * 集合添加
     *
     * @param key
     * @param value
     */
    public <V extends Serializable> void sAdd(final RedisCommonKey key, V value) {
        SetOperations<String, V> set = redisTemplate.opsForSet();
        set.add(key.name(), value);
    }

    /**
     * 集合获取
     *
     * @param key
     * @return
     */
    public <V extends Serializable> Set<V> sMembers(final RedisCommonKey key) {
        SetOperations<String, V> set = redisTemplate.opsForSet();
        return set.members(key.name());
    }

    /**
     * 有序集合添加
     *
     * @param key
     * @param value
     * @param score
     */
    public <V extends Serializable> void zAdd(final RedisCommonKey key, V value, double score) {
        ZSetOperations<String, V> zset = redisTemplate.opsForZSet();
        zset.add(key.name(), value, score);
    }

    /**
     * 有序集合获取
     *
     * @param key
     * @param start
     * @param end
     * @return
     */
    public <V extends Serializable> Set<V> zRange(final RedisCommonKey key, double start, double end) {
        ZSetOperations<String, V> zset = redisTemplate.opsForZSet();
        return zset.rangeByScore(key.name(), start, end);
    }
}
