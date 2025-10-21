package com.scy.user.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.scy.modles.user.EditUserInfo;
import com.scy.modles.user.UserInfo;
import com.scy.user.pojo.User;

/**
* @author 24022
* @description 针对表【user】的数据库操作Service
* @createDate 2025-08-17 15:02:45
*/
public interface UserService extends IService<User> {

    UserInfo createUser(String deviceId);

    Object getByuid(String object);

    UserInfo updateUser(User user, EditUserInfo deviceId);

    String bindEmail(User user);
}
