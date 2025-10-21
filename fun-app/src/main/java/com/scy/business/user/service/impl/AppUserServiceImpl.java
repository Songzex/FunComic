package com.scy.business.user.service.impl;

import com.scy.business.user.service.AppUserService;
import com.scy.dto.user.EditUserInfo;
import com.scy.dto.user.UserInfo;
import com.scy.pojo.User;
import com.scy.service.UserService;
import org.springframework.stereotype.Service;

/**
 * @author 24022
 */
@Service("AppUserService")
public class AppUserServiceImpl implements AppUserService {

    private  final UserService userService;

    public AppUserServiceImpl(UserService userService) {
        this.userService = userService;
    }

    @Override
    public UserInfo createUser(String deviceId) {
        return userService.createUser(deviceId);
    }

    @Override
    public UserInfo updateUser(User user, EditUserInfo deviceId) {
        return userService.updateUser(user,deviceId);
    }

    @Override
    public String bindEmail(User user) {
        return  userService.bindEmail(user);
    }
}
