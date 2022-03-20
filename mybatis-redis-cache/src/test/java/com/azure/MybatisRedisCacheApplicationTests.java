package com.azure;

import com.azure.entity.Redis;
import com.azure.entity.TUser;
import com.azure.service.RedisService;
import com.azure.service.TUserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;

@SpringBootTest
class MybatisRedisCacheApplicationTests {

    @Autowired
    private RedisService redisService;

    @Autowired
    private TUserService tUserService;

    @Test
    void contextLoads() {
        Redis byId = redisService.getById(1);
        System.out.println(byId);
        Redis byId1 = redisService.getById(1);
        System.out.println("byId1 = " + byId1);
        TUser byId2 = tUserService.getById(3);
        System.out.println(byId2);
    }

    @Test
    void testAdd() {
        Redis redis = new Redis();
        redis.setName("test").setAge(18).setBir(new Date());
        boolean save = redisService.save(redis);
        System.out.println(save);
    }
}
