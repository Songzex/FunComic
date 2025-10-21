package com.scy.business.comic.service;

import com.scy.modles.comic.AddComic;
import com.scy.modles.comic.ResponseComic;
import com.scy.modles.recommend.ComicBook;
import com.scy.modles.search.SearchDRO;

import java.util.List;

public interface AppComicService {
    String addComic(AddComic comic);

    ResponseComic getChapter(String comicId);

    List<String> comicCateGory();

    List<ComicBook> getComicByes(SearchDRO from);

}
