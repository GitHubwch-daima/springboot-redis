package com.azure.mapper;

import com.azure.cache.MyRedisCache;
import com.azure.entity.Redis;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.CacheNamespace;
import org.springframework.stereotype.Repository;

/**
 * @author wangch
 * @description 针对表【redis】的数据库操作Mapper
 * @createDate 2022-03-20 10:35:39
 * @Entity com.azure.entity.Redis
 */
@Repository
@CacheNamespace(implementation = MyRedisCache.class, eviction = MyRedisCache.class)
public interface RedisMapper extends BaseMapper<Redis> {

}




