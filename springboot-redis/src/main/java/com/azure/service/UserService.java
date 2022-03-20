package com.azure.service;

import com.azure.dao.UserDao;
import com.azure.pojo.User;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class UserService {

    @Resource
    private UserDao userDao;

    public User getUserService(Integer id) {
        User user = userDao.selectById(id);
        return user;
    }
}
