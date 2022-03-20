package com.azure.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.azure.entity.TUser;
import com.azure.service.TUserService;
import com.azure.mapper.TUserMapper;
import org.springframework.stereotype.Service;

/**
* @author wangch
* @description 针对表【t_user】的数据库操作Service实现
* @createDate 2022-03-20 11:25:40
*/
@Service
public class TUserServiceImpl extends ServiceImpl<TUserMapper, TUser>
    implements TUserService{

}




