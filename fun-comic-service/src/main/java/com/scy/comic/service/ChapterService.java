package com.scy.comic.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.scy.modles.comic.AddChapterDTO;
import com.scy.modles.comic.ChapterDTO;
import com.scy.comic.pojo.Chapter;

import java.util.List;

/**
* @author 24022
* @description 针对表【chapter】的数据库操作Service
* @createDate 2024-07-06 00:02:22
*/
public interface ChapterService extends IService<Chapter> {

    List<ChapterDTO> selectOneComic(Integer comicId,String comicid);

    String addChapter(AddChapterDTO comic);

    Boolean addChapters(Chapter chapter);
}
