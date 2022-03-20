package com.azure;

import org.junit.After;
import org.junit.Before;
import redis.clients.jedis.Jedis;

public class TestKey {

    private Jedis jedis;

    //创建连接
    @Before
    public void before() {
        this.jedis = new Jedis("101.200.190.58", 6379);
    }


    //释放资源
    @After
    public void after() {
        this.jedis.close();
    }
}
