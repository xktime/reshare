package com.xktime.utils.common;

public class RedisKeyUtil
{
    public static String getUniqueKeyById(long id, RedisCommonKey key)
    {
        return id + "_" + key.name();
    }

    public static String getUniqueKeyById(long id, String key)
    {
        return id + "_" + key;
    }

}
