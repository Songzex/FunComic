package com.scy.user.service.impl;

import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.scy.user.dao.PermissionsMapper;
import com.scy.user.pojo.Permissions;
import org.springframework.stereotype.Service;

/**
* @author 24022
* @description 针对表【permissions】的数据库操作Service实现
* @createDate 2024-08-29 16:01:01
*/
@Service
public class PermissionsServiceImpl extends ServiceImpl<PermissionsMapper, Permissions>
    implements IService<Permissions> {

}




