package com.scy.user.service.impl;



import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.scy.modles.user.EditUserInfo;
import com.scy.modles.user.UserInfo;
import com.scy.exception.LinfengException;
import com.scy.user.dao.UserMapper;
import com.scy.user.pojo.User;
import com.scy.user.service.UserService;
import com.scy.utils.DateUtil;
import com.scy.utils.JwtUtil;
import org.springframework.stereotype.Service;

import java.util.Random;
import java.util.UUID;

/**
* @author 24022
* @description 针对表【user】的数据库操作Service实现
* @createDate 2025-08-17 15:02:45
*/
@Service("UserService")
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService{
    private final JwtUtil jwtUtil;

    private  final UserMapper userMapper;

    public UserServiceImpl(JwtUtil jwtUtil, UserMapper userMapper) {
        this.jwtUtil = jwtUtil;
        this.userMapper = userMapper;
    }

    @Override
    public UserInfo createUser(String deviceId) {
        UserInfo userInfo = new UserInfo();
        User user = userMapper.selectOne(new LambdaQueryWrapper<User>()
                .eq(User::getDeviceid, deviceId));
        if (user == null) {
            user = new User();
            user.setDeviceid(deviceId);
            String string = String.format("%08d", new Random().nextInt(100000000));
            user.setUsername("用户_" +string );
            // 生成用户id 随机唯一的
            user.setId(new Random().nextInt(1000000000));
            //对外唯一id 字符串 唯一
            user.setUserid(UUID.randomUUID().toString());
            user.setSex("boy");
            user.setAge(18);
            user.setIsvip(0);
            user.setExpiretime(null);
            user.setAvatar("https://ts1.tc.mm.bing.net/th/id/OIP-C.UyaBji0AU_6M3VDA2F1RvgAAAA?rs=1&pid=ImgDetMain&o=7&rm=3");
            user.setCreatetime(DateUtil.nowDateTime());
            int insert = userMapper.insert(user);
            UserInfo userInfo1 = new UserInfo();
            userInfo1.setUsername(user.getUsername());
            userInfo1.setAvatar(user.getAvatar());
            String token = jwtUtil.generateToken(user.getUserid());
            userInfo1.setToken(token);
            userInfo1.setEmail(user.getEmail());
            userInfo1.setVip(0);
            userInfo1.setExpireTime(user.getExpiretime());
            userInfo1.setEmail(user.getEmail());
            return userInfo1;

        }
        UserInfo userInfo1 = new UserInfo();
        userInfo1.setUsername(user.getUsername());
        userInfo1.setAvatar(user.getAvatar());
        String token = jwtUtil.generateToken(user.getUserid());
        userInfo1.setToken(token);
        userInfo1.setEmail(user.getEmail());
        userInfo1.setVip(0);
        userInfo1.setExpireTime(user.getExpiretime());
        userInfo1.setEmail(user.getEmail());
        return userInfo1;
    }

    @Override
    public Object getByuid(String object) {
        return userMapper.selectOne(new LambdaQueryWrapper<User>()
                .eq(User::getUserid, object));
    }

    @Override
    public UserInfo updateUser(User user, EditUserInfo deviceId) {
        UserInfo userInfo = new UserInfo();
        User user1 = userMapper.selectOne(new LambdaQueryWrapper<User>().eq(User::getUserid, user.getUserid()));
        if (user1 != null) {
            BeanUtil.copyProperties(user, user1);
            userMapper.update(user1, new LambdaUpdateWrapper<User>().eq(User::getUserid, user.getUserid()));
            userInfo.setUsername(deviceId.getUsername());
            userInfo.setAvatar(deviceId.getAvatar());
            userInfo.setToken(jwtUtil.generateToken(user1.getUserid()));
            userInfo.setEmail(user1.getEmail());
            userInfo.setVip(user1.getIsvip());
            userInfo.setSex(user.getSex());
            userInfo.setExpireTime(user1.getExpiretime());
            return userInfo;
        }else {
            throw new LinfengException("user not register");
        }
    }

    @Override
    public String bindEmail(User user) {
        userMapper.update( user, new LambdaUpdateWrapper<User>().eq(User::getUserid, user.getUserid()));
        return "success";
    }
}




