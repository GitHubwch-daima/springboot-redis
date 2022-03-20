package com.azure.cache;

import com.azure.utils.ApplicationContextUtils;
import org.apache.ibatis.cache.Cache;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.util.DigestUtils;

import java.nio.charset.StandardCharsets;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 自定义redis缓存
 */
public class MyRedisCache implements Cache {

    private static final Logger log = LoggerFactory.getLogger(MyRedisCache.class);
    // 读写锁
    private final ReadWriteLock readWriteLock = new ReentrantReadWriteLock(true);

    //当前放入缓存mapper的namespace
    private final String id;

    //必须存在构造方法
    public MyRedisCache(final String id) {
        if (id == null) {
            throw new IllegalArgumentException("Cache instances require an ID");
        }
        this.id = id;
    }

    //返回cache的唯一标识
    @Override
    public String getId() {
        return this.id;
    }

    //放入缓存值
    @Override
    public void putObject(Object key, Object value) {
        log.debug("放入缓存值：{}、{}、{}", id, key.toString(), getKeyToMd5(key.toString()));
        getRedisTemplate().opsForHash().put(id, getKeyToMd5(key.toString()), value);
    }

    //获取缓存的数据
    @Override
    public Object getObject(Object key) {
        log.debug("获取缓存的数据：{}、{}", key.toString(), getKeyToMd5(key.toString()));
        return getRedisTemplate().opsForHash().get(id, getKeyToMd5(key.toString()));
    }

    @Override
    public Object removeObject(Object key) {
        return null;
    }

    //清空缓存
    @Override
    public void clear() {
        log.debug("清空缓存：{}", id);
        getRedisTemplate().delete(id);
    }

    //计算缓存数量
    @Override
    public int getSize() {
        //获取hash中key value数量
        return getRedisTemplate().opsForHash().size(id).intValue();
    }

    @Override
    public ReadWriteLock getReadWriteLock() {
        return this.readWriteLock;
    }


    //封装RedisTemplate
    public RedisTemplate getRedisTemplate() {
        //通过Application工具类获取RedisTemplate
        RedisTemplate redisTemplate = (RedisTemplate) ApplicationContextUtils.getBean("redisTemplate");
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setHashKeySerializer(new StringRedisSerializer());
        return redisTemplate;
    }

    //封装一个对key进行加密的方法
    public String getKeyToMd5(String key) {
        return DigestUtils.md5DigestAsHex(key.getBytes(StandardCharsets.UTF_8));
    }
}