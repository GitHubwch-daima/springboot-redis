package com.azure.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.azure.entity.Redis;
import com.azure.service.RedisService;
import com.azure.mapper.RedisMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author wangch
 * @description 针对表【redis】的数据库操作Service实现
 * @createDate 2022-03-20 10:35:39
 */
@Service
@Transactional
public class RedisServiceImpl extends ServiceImpl<RedisMapper, Redis> implements RedisService {

}




