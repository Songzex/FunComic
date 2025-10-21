package com.scy.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.scy.pojo.Comic;
import org.apache.ibatis.annotations.Mapper;

/**
* @author 24022
* @description 针对表【comic】的数据库操作Mapper
* @createDate 2024-07-05 23:58:56
* @Entity com.example.pojo.Comic
*/
@Mapper
public interface ComicMapper extends BaseMapper<Comic> {

}




