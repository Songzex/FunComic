package com.scy.business.user.service;

import com.scy.modles.user.EditUserInfo;
import com.scy.modles.user.UserInfo;
import com.scy.user.pojo.User;

/**
 * @author 24022
 */
public interface
AppUserService {
    UserInfo createUser(String deviceId);

    UserInfo updateUser(User user, EditUserInfo deviceId);

    String bindEmail(User user);
}
