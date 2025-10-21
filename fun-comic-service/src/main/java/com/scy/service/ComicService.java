package com.scy.service;

import com.scy.dto.comic.AddComic;
import com.scy.dto.comic.ResponseComic;
import com.scy.dto.recommend.ComicBook;
import com.scy.dto.search.SearchDRO;
import com.scy.pojo.Comic;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
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
