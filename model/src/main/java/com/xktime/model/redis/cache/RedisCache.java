package com.xktime.model.redis.cache;

import com.xktime.model.redis.RedisCommonKey;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Author: luoliwen
 * @Description
 * @Date: 2023/2/23 18:22
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface RedisCache {
    RedisCommonKey key();
}
