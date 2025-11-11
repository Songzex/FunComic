package com.scy.business.comic.service.impl;

import com.scy.business.comic.service.AppComicService;
import com.scy.modles.comic.AddComic;
import com.scy.modles.comic.ResponseComic;
import com.scy.modles.recommend.ComicBook;
import com.scy.modles.search.SearchDRO;
import com.scy.comic.service.ComicService;
import org.springframework.stereotype.Service;

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
