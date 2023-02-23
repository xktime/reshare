package com.xktime.model.redis.cache;

import com.xktime.model.redis.RedisCommonKey;

/**
 * @Author: luoliwen
 * @Description
 * @Date: 2023/2/23 18:22
 */
public @interface RedisCache {
    RedisCommonKey key();
}
