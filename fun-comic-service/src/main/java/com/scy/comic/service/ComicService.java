package com.scy.comic.service;

import com.scy.comic.pojo.Comic;
import com.scy.modles.comic.AddComic;
import com.scy.modles.comic.ResponseComic;
import com.scy.modles.recommend.ComicBook;
import com.scy.modles.search.SearchDRO;

import java.util.List;

/**
 * @author 24022
 */
public interface ComicService {
    ResponseComic getChapter(String comicId);

    String addComic(AddComic comic);



    Comic addComicToId(AddComic comic);

    List<String> comicCateGory();

    List<ComicBook> getComicByes(SearchDRO from);


   Comic getComicByComicTitle(String name);

}
