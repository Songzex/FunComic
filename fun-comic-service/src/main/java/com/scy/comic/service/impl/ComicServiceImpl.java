package com.scy.comic.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.scy.modles.comic.AddComic;
import com.scy.modles.comic.ChapterDTO;
import com.scy.modles.comic.ResponseComic;
import com.scy.modles.recommend.ComicBook;
import com.scy.modles.search.SearchDRO;
import com.scy.exception.LinfengException;
import com.scy.comic.dao.ComicMapper;
import com.scy.comic.pojo.Comic;

import com.scy.comic.service.ChapterService;
import com.scy.comic.service.ComicService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

/**
* @author 24022
* @description 针对表【comic】的数据库操作Service实现
* @createDate 2025-08-17 20:13:12
*/
@Service
@Slf4j
public class ComicServiceImpl extends ServiceImpl<ComicMapper, Comic>
    implements ComicService {
private final ComicMapper comicMapper;

private  final ChapterService chapterMapper;




    public ComicServiceImpl(ComicMapper comicMapper, ChapterService chapterMapper) {
        this.comicMapper = comicMapper;
        this.chapterMapper = chapterMapper;
    }

    @Override
    public ResponseComic getChapter(String comicId) {
        ResponseComic responseComic = new ResponseComic();

        Comic comic = comicMapper.selectOne(new LambdaQueryWrapper<Comic>()
                .eq(Comic::getComicid, comicId));
       if (comic != null){
           //补充基本信息
        responseComic.setComicId(comic.getComicid());
        responseComic.setTitle(comic.getTitle());
        responseComic.setAuthor(comic.getAuthor());
        responseComic.setDescription(comic.getDescription());
        responseComic.setCoverImage(comic.getCoverimage());
        responseComic.setTags(comic.getTags());
        responseComic.setStatus(comic.getStatus());
        if(comic.getType().equals("付费")){
            responseComic.setSalesType(1);
        }else {
            responseComic.setSalesType(0);
        }
        responseComic.setIsPay(1);
        responseComic.setPrice(comic.getPrice().toString());
        //目录信息
           List<ChapterDTO> chapterList=chapterMapper.selectOneComic(comic.getId(),comic.getComicid());
        responseComic.setChapterList(chapterList);
        return responseComic;
    }else {
           throw new LinfengException("comic is not");
       }
}

    @Override
    public String addComic(AddComic comic) {
        Comic comic1 = new Comic();
        comic1.setAuthor(comic.getAuthor());
        // 随机生成9位id 字符串 唯一
        String string = String.format("%09d", new Random().nextInt(1000000000));
        comic1.setComicid(string);
        comic1.setDescription(comic.getDescription());
        comic1.setCoverimage(comic.getCoverimage());
        comic1.setTags(comic.getTags());
        comic1.setStatus(comic.getStatus());
        comic1.setType(comic.getSalesType());
        comic1.setPrice(comic.getPrice());
        boolean save = this.save(comic1);
        if (save){
            return "ok";
        }
        return "fail";
    }



    @Override
    public Comic addComicToId(AddComic comic) {
        Comic comic1 = new Comic();
        comic1.setAuthor(comic.getAuthor());
        // 随机生成9位id 字符串 唯一
        String string = String.format("%09d", new Random().nextInt(1000000000));
        comic1.setComicid(string);
        comic1.setDescription(comic.getDescription());
        comic1.setCoverimage(comic.getCoverimage());
        comic1.setTags(comic.getTags());
        comic1.setStatus(comic.getStatus());
        comic1.setType(comic.getSalesType());
        comic1.setPrice(comic.getPrice());
        boolean save = this.save(comic1);

        if (save){
            return comic1;
        }
        return null;
    }

    @Override
    public List<String> comicCateGory() {
        LambdaQueryWrapper<Comic> wrapper = new LambdaQueryWrapper<>();
        wrapper.select(Comic::getTags);
        return comicMapper.selectList(wrapper).stream().map(Comic::getTags).collect(Collectors.toList());
    }

    @Override
    public List<ComicBook> getComicByes(SearchDRO from) {
        LambdaQueryWrapper<Comic> wrapper = new LambdaQueryWrapper<>();
        if(from.getTags() != null){
            wrapper.like(Comic::getTags,from.getTags());
        }
        if(from.getIsFree() != null){
            wrapper.like(Comic::getType,from.getIsFree());
        }
        if(from.getStatus() != null){
            wrapper.like(Comic::getStatus,from.getStatus());
        }
        if(from.getLasTest() != null){
            //修改时间在当前24小时之内
            wrapper.ge(Comic::getUpdatetime,System.currentTimeMillis()-24*60*60*1000);
        }
        List<Comic> list = comicMapper.selectList(wrapper);
        return list.stream().map(comic ->
                        new ComicBook(comic.getTitle(),
                                comic.getCoverimage(),
                                comic.getAuthor(),
                                comic.getTags(),
                                comic.getComicid()))
                .collect(Collectors.toList());
    }

    @Override
    public Comic getComicByComicTitle(String name) {
        LambdaQueryWrapper<Comic> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Comic::getTitle,name);
        return this.comicMapper.selectOne(wrapper);
    }
    
}




