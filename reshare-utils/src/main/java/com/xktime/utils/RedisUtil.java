package com.xktime.utils;

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
     * @param value
     * @return
     */
    public <V extends Serializable> boolean set(final String key, V value) {
        boolean result = false;
        try {
            ValueOperations<String, V> operations = redisTemplate.opsForValue();
            operations.set(key, value);
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 写入缓存设置时效时间
     *
     * @param key
     * @param value
     * @return
     */
    public <V extends Serializable> boolean set(final String key, V value, Long expireTime, TimeUnit timeUnit) {
        boolean result = false;
        try {
            ValueOperations<String, V> operations = redisTemplate.opsForValue();
            operations.set(key, value);
            redisTemplate.expire(key, expireTime, timeUnit);
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 批量删除对应的value
     *
     * @param keys
     */
    public void remove(final String... keys) {
        for (String key : keys) {
            remove(key);
        }
    }

    /**
     * 批量删除key
     *
     * @param pattern
     */
    public void removePattern(final String pattern) {
        Set<Serializable> keys = redisTemplate.keys(pattern);
        if (keys.size() > 0) {
            redisTemplate.delete(keys);
        }
    }

    /**
     * 删除对应的value
     *
     * @param key
     */
    public void remove(final String key) {
        if (exists(key)) {
            redisTemplate.delete(key);
        }
    }

    /**
     * 判断缓存中是否有对应的value
     *
     * @param key
     * @return
     */
    public boolean exists(final String key) {
        return redisTemplate.hasKey(key);
    }

    /**
     * 读取缓存
     *
     * @param key
     * @return
     */
    public <V extends Serializable> V get(final String key) {
        ValueOperations<String, V> operations = redisTemplate.opsForValue();
        return operations.get(key);
    }

    /**
     * 哈希 添加
     *
     * @param key
     * @param hashKey
     * @param value
     */
    public <K extends Serializable, V extends Serializable> void hmSet(String key, K hashKey, V value) {
        HashOperations<String, K, V> hash = redisTemplate.opsForHash();
        hash.put(key, hashKey, value);
    }

    /**
     * 哈希获取数据
     *
     * @param key
     * @param hashKey
     * @return
     */
    public <K extends Serializable, V extends Serializable> V hmGet(String key, K hashKey) {
        HashOperations<String, K, V> hash = redisTemplate.opsForHash();
        return hash.get(key, hashKey);
    }

    /**
     * 列表添加
     *
     * @param k
     * @param v
     */
    public <V extends Serializable>void listAddTail(String k, V v) {
        ListOperations<String, V> list = redisTemplate.opsForList();
        list.rightPush(k, v);
    }

    /**
     * 列表添加
     *
     * @param k
     * @param v
     */
    public <V extends Serializable>void listAddHead(String k, V v) {
        ListOperations<String, V> list = redisTemplate.opsForList();
        list.leftPush(k, v);
    }

    /**
     * 列表获取
     *
     * @param k
     * @param star
     * @param end
     * @return
     */
    public <V extends Serializable> List<V> listRange(String k, long star, long end) {
        ListOperations<String, V> list = redisTemplate.opsForList();
        return list.range(k, star, end);
    }

    /**
     * 集合添加
     *
     * @param key
     * @param value
     */
    public <V extends Serializable> void setAdd(String key, V value) {
        SetOperations<String, V> set = redisTemplate.opsForSet();
        set.add(key, value);
    }

    /**
     * 集合获取
     *
     * @param key
     * @return
     */
    public <V extends Serializable> Set<V> setMembers(String key) {
        SetOperations<String, V> set = redisTemplate.opsForSet();
        return set.members(key);
    }

    /**
     * 有序集合添加
     *
     * @param key
     * @param value
     * @param scoure
     */
    public <V extends Serializable> void zAdd(String key, V value, double scoure) {
        ZSetOperations<String, V> zset = redisTemplate.opsForZSet();
        zset.add(key, value, scoure);
    }

    /**
     * 有序集合获取
     *
     * @param key
     * @param start
     * @param end
     * @return
     */
    public <V extends Serializable> Set<V> zRange(String key, double start, double end) {
        ZSetOperations<String, V> zset = redisTemplate.opsForZSet();
        return zset.rangeByScore(key, start, end);
    }
}
