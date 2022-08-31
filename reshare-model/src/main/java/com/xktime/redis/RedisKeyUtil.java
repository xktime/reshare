package com.xktime.redis;

public class RedisKeyUtil
{
    public static String getUniqueKey(RedisCommonKey key, long id)
    {
        return id + "_" + key.name();
    }

    public static String getUniqueKey(RedisCommonKey key, String id)
    {
        return id + "_" + key.name();
    }

    public static String getUniqueKey(String key, long id)
    {
        return id + "_" + key;
    }

}
