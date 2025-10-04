package com.furniture.store.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RedisUtils {

    private final ObjectMapper objectMapper;

    public <T> T getValueAs(String key, Class<T> clazz, org.springframework.data.redis.core.RedisTemplate<String, Object> redisTemplate) {
        Object cachedObj = redisTemplate.opsForValue().get(key);
        if (cachedObj == null) {
            return null;
        }
        return objectMapper.convertValue(cachedObj, clazz);
    }
}
