package com.scy.comic.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.scy.comic.pojo.Chapter;
import org.apache.ibatis.annotations.Mapper;

/**
* @author 24022
* @description 针对表【chapter】的数据库操作Mapper
* @createDate 2024-07-06 00:02:22
* @Entity com.example.pojo.Chapter
*/
@Mapper
public interface ChapterMapper extends BaseMapper<Chapter> {


}




