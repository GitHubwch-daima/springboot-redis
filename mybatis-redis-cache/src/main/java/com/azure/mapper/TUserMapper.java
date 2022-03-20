package com.azure.mapper;

import com.azure.cache.MyRedisCache;
import com.azure.entity.TUser;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.CacheNamespace;
import org.apache.ibatis.annotations.CacheNamespaceRef;
import org.springframework.stereotype.Repository;

/**
 * @author wangch
 * @description 针对表【t_user】的数据库操作Mapper
 * @createDate 2022-03-20 11:25:40
 * @Entity com.azure.entity.TUser
 */
@Repository
@CacheNamespace(implementation = MyRedisCache.class, eviction = MyRedisCache.class)
@CacheNamespaceRef(RedisMapper.class)
public interface TUserMapper extends BaseMapper<TUser> {

}




