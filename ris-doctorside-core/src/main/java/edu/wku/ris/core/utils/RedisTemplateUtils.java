package edu.wku.ris.core.utils;

import org.springframework.context.ApplicationContext;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * @author Aska
 * @description Redis Template Utils for set, get, delete, or get and +1
 * @create 2022/4/19 21:15
 */
@Component
public class RedisTemplateUtils {

    private static RedisTemplate<String, Object> redisTemplate;

    private static StringRedisTemplate stringRedisTemplate;

    private static ApplicationContext applicationContext;

    private RedisTemplateUtils(ApplicationContext applicationContext, RedisTemplate redisTemplate, StringRedisTemplate stringRedisTemplate){
        RedisTemplateUtils.applicationContext = applicationContext;
        RedisTemplateUtils.redisTemplate = redisTemplate;
        RedisTemplateUtils.stringRedisTemplate = stringRedisTemplate;
    }

    public static <V> void storeObjectInCache(String key, V value, Long expireTime){
        if (expireTime == -1l){
            redisTemplate.opsForValue().set(key, value);
            return;
        }
        redisTemplate.opsForValue().set(key, value, expireTime, TimeUnit.MINUTES);
    }

    public static <V> void removeObjectInCache(String key){
        redisTemplate.opsForValue().getOperations().delete(key);
    }

    public static <V> V getObjectInCache(String key){
        return (V) redisTemplate.opsForValue().get(key);
    }

    public static void expireObjectInCache(String key, Long expireTime){
        redisTemplate.opsForValue().getOperations().expire(key, expireTime, TimeUnit.MINUTES);
    }

    public static boolean containsKeyInCache(String key){
        return redisTemplate.opsForValue().getOperations().hasKey(key);
    }

    /**
     * Get Expire time from Redis;
     * No set Expire time: Return -1;
     * key does not exist: -2;
     * @param key
     * @return
     */
    public static Long getExpireTime(String key) {
        return redisTemplate.opsForValue().getOperations().getExpire(key);
    }

    public static void storeStringInCache(String key, String value, Long expireTime){
        stringRedisTemplate.opsForValue().set(key, value, expireTime);
    }

    public static void removeStringInCache(String key){
        stringRedisTemplate.opsForValue().getOperations().delete(key);
    }

    /**
     * This is the atomic method, firstly plus 1 to original value and return the result
     * It is used for ID Increment because of single thread of Redis (Thread Safety)
     * @param key
     * @return key
     */
    public static Long incrementAndGet(String key){
        return stringRedisTemplate.opsForValue().increment(key);
    }



}
