package com.azure;

import com.azure.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.connection.DataType;
import org.springframework.data.redis.core.*;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.util.*;
import java.util.concurrent.TimeUnit;

@SpringBootTest
class SpringBootRedisApplicationTests {

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    //操作字符串String
    @Test
    void testString() {
        stringRedisTemplate.opsForValue().set("name", "tom");
        String name = stringRedisTemplate.opsForValue().get("name");
        System.out.println("name = " + name);
        //设置一个key的超时时间
        stringRedisTemplate.opsForValue().set("code", "1785", 120, TimeUnit.SECONDS);
        //追加字符串
        stringRedisTemplate.opsForValue().append("name", "and");
    }

    //操作key相关的
    @Test
    public void testKey() {
//        stringRedisTemplate.delete("name");
        //判断一个key是否存在
        Boolean hasKey = stringRedisTemplate.hasKey("name");
        System.out.println("hasKey = " + hasKey);
        //判断key所对应值的类型
        DataType dataType = stringRedisTemplate.type("name");
        System.out.println("dataType = " + dataType);
        //获取所有key的值
        Set<String> keys = stringRedisTemplate.keys("*");
        assert keys != null;
        keys.forEach(System.out::println);
        //获取key超时时间-1永远不超时、-2不存在
        Long expire = stringRedisTemplate.getExpire("name");
        System.out.println("expire = " + expire);
        //随机获取一个key
        String randomKey = stringRedisTemplate.randomKey();
        System.out.println("randomKey = " + randomKey);
        //修改key的名字
//        stringRedisTemplate.rename("name","name1");
        //修改key的名字判断key是否存在
//        stringRedisTemplate.renameIfAbsent("name", "name1");
        //移动key到1号库
//        stringRedisTemplate.move("name", 1);
    }

    //操作list类型
    @Test
    public void testList() {
        List<String> list = new ArrayList<>();
        list.add("tom");
        list.add("kiv");
        //创建列表 放入多个元素
        stringRedisTemplate.opsForList().leftPushAll("lists1", list);
        List<String> stringList = stringRedisTemplate.opsForList().range("lists1", 0, -1);
        assert stringList != null;
        stringList.forEach(System.out::println);
        //截取指定区间的list
        stringRedisTemplate.opsForList().trim("lists1", 1, 3);
        List<String> stringList1 = stringRedisTemplate.opsForList().range("lists1", 0, -1);
        assert stringList1 != null;
        System.out.println("=======================================");
        stringList1.forEach(System.out::println);
    }

    //操作set类型
    @Test
    public void testSet() {
        //创建set 并放入多个元素
        stringRedisTemplate.opsForSet().add("sets1", "tom", "jory", "klo", "kdt");
        //查看set中的成员
        Set<String> sets = stringRedisTemplate.opsForSet().members("sets1");
        assert sets != null;
        sets.forEach(System.out::println);
        //获取set集合元素的个数
        Long size = stringRedisTemplate.opsForSet().size("sets1");
        System.out.println("size = " + size);
    }

    //操作Zset类型
    @Test
    public void testZset() {
        //创建并放入元素
        stringRedisTemplate.opsForZSet().add("zsets2", "tom-zsets-50", 50);
        //指定范围查询
        Set<String> zsets = stringRedisTemplate.opsForZSet().range("zsets1", 0, -1);
        assert zsets != null;
        zsets.forEach(System.out::println);
        System.out.println("=========================================");
        //获取指定分数的zset
        Set<ZSetOperations.TypedTuple<String>> zsets1 = stringRedisTemplate.opsForZSet().rangeByScoreWithScores("zsets1", 0, 30);
        assert zsets1 != null;
        zsets1.forEach(typedTuple -> {
            System.out.println(typedTuple.getValue());
            System.out.println(typedTuple.getScore());
        });
    }

    //操作Hash类型
    @Test
    public void testHash() {
        //创建一个hash类型并放入key value
        stringRedisTemplate.opsForHash().put("maps", "name", "lisi");
        Map<String, String> map = new HashMap<>();
        map.put("age", "18");
        map.put("bir", "18-10-01");
        //放入多个key
        stringRedisTemplate.opsForHash().putAll("maps", map);
        //获取多个值
        List<Object> values = stringRedisTemplate.opsForHash().multiGet("maps", Arrays.asList("name", "age"));
        values.forEach(System.out::println);
        //获取其中某个值
        String name = (String) stringRedisTemplate.opsForHash().get("maps", "name");
        System.out.println("name = " + name);
        //获取所有values
        List<Object> maps = stringRedisTemplate.opsForHash().values("maps");
        maps.forEach(System.out::println);
        //获取所有key
        Set<Object> maps1 = stringRedisTemplate.opsForHash().keys("maps");
        maps1.forEach(System.out::println);
    }


    //放入对象类型
    @Test
    public void testRedisTemplate() {

        //修改key序列话方案 String类型
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        //修改hash key序列话方案 String类型
        redisTemplate.setHashKeySerializer(new StringRedisSerializer());
        User user = new User();
        user.setName("tom").setAge(18).setBir(new Date());
        redisTemplate.opsForValue().set("user", user);

        User user1 = (User) redisTemplate.opsForValue().get("user");
        System.out.println(user1);

        redisTemplate.opsForHash().put("user-map", "name", user);
        User name = (User) redisTemplate.opsForHash().get("user-map", "name");
        System.out.println(name);
    }


    @Test
    public void testBound() {
        //修改key序列话方案 String类型
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        //修改hash key序列话方案 String类型
        redisTemplate.setHashKeySerializer(new StringRedisSerializer());
        User user = new User();
        user.setName("tom").setAge(18).setBir(new Date());
        BoundValueOperations user1 = redisTemplate.boundValueOps("user");
        user1.set(user);
        User userRedis = (User) user1.get();
        System.out.println(userRedis);
    }
}
