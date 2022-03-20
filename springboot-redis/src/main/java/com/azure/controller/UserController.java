package com.azure.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.azure.pojo.User;
import com.azure.service.UserService;
import com.azure.tool.RedisUtil;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class UserController {

    @Resource
    private UserService userService;

    @Resource
    private RedisUtil redisUtil;

    @GetMapping("getUser")
    public Map<String, Object> getUserById(Integer id) {
        Map<String, Object> map = new HashMap<>();
        Object object = redisUtil.get("user");
        List<Object> list = new ArrayList<>();
        if (object != null) {
            System.out.println(object.toString());
            list.add(object);
            map.put("code", "redis");
            map.put("data", list);
            return map;
        }
        User userService = this.userService.getUserService(id);
        redisUtil.set("user", userService);
        list.add(userService);
        map.put("code", "mysql");
        map.put("data", list);
        return map;
    }
}
