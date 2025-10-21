package com.scy.business.comic.service.impl;

import com.scy.business.comic.service.AppChapterService;
import com.scy.dto.comic.AddChapterDTO;
import com.scy.dto.comic.AddComic;
import com.scy.dto.comic.ChapterDTO;
import com.scy.pojo.Chapter;
import com.scy.service.ChapterService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 24022
 */
@Service("AppChapterService")
public class AppChapterServiceimpl implements AppChapterService {
 private  final ChapterService chapterService;

    public AppChapterServiceimpl(ChapterService chapterService) {
        this.chapterService = chapterService;
    }

    @Override
    public String addChapter(AddChapterDTO comic){
        if (comic.getChapterImageUrl()== null){
            return "图片不能为空";
        }
        return chapterService.addChapter( comic);
    }


}
