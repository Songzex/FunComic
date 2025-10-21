package com.scy.business.comic.service;

import com.scy.dto.comic.AddComic;
import com.scy.dto.comic.ResponseComic;
import com.scy.dto.recommend.ComicBook;
import com.scy.dto.search.SearchDRO;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface AppComicService {
    String addComic(AddComic comic);

    ResponseComic getChapter(String comicId);

    List<String> comicCateGory();

    List<ComicBook> getComicByes(SearchDRO from);

}
