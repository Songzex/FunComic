package com.scy.business.comic.service.impl;

import com.scy.business.comic.service.AppComicService;
import com.scy.dto.comic.AddComic;
import com.scy.dto.comic.ResponseComic;
import com.scy.dto.recommend.ComicBook;
import com.scy.dto.search.SearchDRO;
import com.scy.service.ComicService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

/**
 * @author 24022
 */
@Service("AppComicService")
public class AppComicServiceimpl implements AppComicService {

private  final ComicService comicService;

    public AppComicServiceimpl(ComicService comicService) {
        this.comicService = comicService;
    }

    @Override
    public String addComic(AddComic comic) {
        return comicService.addComic(comic);
    }

    @Override
    public ResponseComic getChapter(String comicId) {
        return  comicService.getChapter(comicId);

    }

    @Override
    public List<String> comicCateGory() {
        return comicService.comicCateGory();
    }

    @Override
    public List<ComicBook> getComicByes(SearchDRO from) {
        return comicService.getComicByes( from);
    }


}
