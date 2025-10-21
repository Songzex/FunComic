package com.scy.modles.user;

import lombok.Data;

import java.util.Date;

/**
 * @author 24022
 */
@Data
public class UserInfo {
    private String username;
    private String email;
    private String avatar;
    private String sex;
    private Integer vip;
    private String token;
    private Date expireTime;

}
