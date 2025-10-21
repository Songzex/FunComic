package com.scy.common.modles;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegisterFrom {
    /**
     * 用户名
     */
    private String username;

    /**
     *
     */
    private String email;

    /**
     *
     */
    private String password;

    /**
     * 头像
     */
    private String personphoto;

    /**
     * 收藏夹
     */
    private String favotercomicids;

    /**
     * 性别
     */
    private String sex;

    /**
     * Age
     */
    private Integer age;



    private  String code;


}
