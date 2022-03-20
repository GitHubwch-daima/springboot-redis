package com.azure;

import redis.clients.jedis.Jedis;

import java.util.Set;

public class TestRedis {

    public static void main(String[] args) {
        //创建一个jedis对象
        Jedis jedis = new Jedis("101.200.190.58", 6379);
        jedis.auth("17851182");
        //选择一个库 默认是0号库
        jedis.select(0);

        //获取所有key的信息
        Set<String> keys = jedis.keys("*");
        keys.forEach(System.out::println);

        //释放资源
        jedis.close();

    }
}
