package com.scy.service.impl;

import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.scy.mapper.PermissionsMapper;
import com.scy.pojo.Permissions;
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




