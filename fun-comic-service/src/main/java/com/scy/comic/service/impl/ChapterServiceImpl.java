package com.scy.comic.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.scy.comic.service.ChapterService;
import com.scy.modles.comic.AddChapterDTO;
import com.scy.modles.comic.ChapterDTO;
import com.scy.comic.dao.ChapterMapper;
import com.scy.user.dao.UserPaycomicMapper;
import com.scy.common.mongodb.MongoChapter;
import com.scy.user.pojo.UserPaycomic;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import com.scy.comic.pojo.Chapter;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

/**
* @author 24022
* @description 针对表【chapter】的数据库操作Service实现
* @createDate 2024-07-06 00:02:22
*/
@Service
public class ChapterServiceImpl extends ServiceImpl<ChapterMapper, Chapter>
    implements ChapterService{
  private final  ChapterMapper chapterMapper;

  private final UserPaycomicMapper userPaycomicMapper;


    private final MongoTemplate mongoTemplate;

    public ChapterServiceImpl(ChapterMapper chapterMapper, UserPaycomicMapper userPaycomicMapper, MongoTemplate mongoTemplate) {
        this.chapterMapper = chapterMapper;
        this.userPaycomicMapper = userPaycomicMapper;
        this.mongoTemplate = mongoTemplate;
    }

    @Override
    public List<ChapterDTO> selectOneComic(Integer comicId,String cid) {
        List<ChapterDTO> chapterDTOList = new ArrayList<ChapterDTO>();

        List<Chapter> list = chapterMapper.selectList(new LambdaQueryWrapper<Chapter>()
                .eq(Chapter::getComicid, comicId));



        List<UserPaycomic> list1 = userPaycomicMapper.selectList(new LambdaQueryWrapper<UserPaycomic>()
                .eq(UserPaycomic::getComicid, comicId)
                .and(wrapper -> wrapper.eq(UserPaycomic::getType, "1")));
        //已经购买的id
        List<Integer> list2 = list1.stream().map(UserPaycomic::getChapterid).collect(Collectors.toList());

        return list.stream().map(chapter -> {
            ChapterDTO chapterDTO = new ChapterDTO();
            chapterDTO.setChapterId(chapter.getChapterid());
            chapterDTO.setComicId(chapter.getComicid());
            chapterDTO.setChapterName(chapter.getTitle());
            chapterDTO.setChapterImage(chapter.getImgurl());
           if ( list2.contains(chapter.getId())){
               chapterDTO.setIsPay(1);
           }else {
               chapterDTO.setIsPay(0);
           }

            chapterDTO.setPayPrice(chapter.getPrice().toString());
            Query query = new Query(Criteria.where("comicID").is(cid).and("desc").is(chapter.getTitle()));
            List<String> url = Objects.requireNonNull(mongoTemplate.findOne(query, MongoChapter.class)).getUrl();
            if( url.isEmpty()){
                chapterDTO.setChapterUrl(new ArrayList<>());
            }else {
                chapterDTO.setChapterUrl(url);
            }
            return chapterDTO;
        }).collect(Collectors.toList());
    }

    @Override
    public String addChapter(AddChapterDTO comic) {
        Chapter chapter1 = chapterMapper.selectOne(new LambdaQueryWrapper<Chapter>().eq(Chapter::getDesc, comic.getDesc()));
        if (chapter1 != null) {
            return "该章节已存在";
        }

        Chapter chapter = new Chapter();
        // 使用UUID确保唯一性
        chapter.setChapterid(Math.abs(UUID.randomUUID().hashCode()));
        chapter.setComicid(comic.getIdComic());
        chapter.setTitle(comic.getTitle());
        chapter.setImgurl(null);
        chapter.setIsfree(comic.getIdFree());
        chapter.setDesc(comic.getDesc());
        chapter.setUpdatetime(new Date());
        chapter.setPrice(new BigDecimal(comic.getPrice()));
        chapterMapper.insert(chapter);
        MongoChapter mongoChapter = new MongoChapter();
        mongoChapter.setComicID(comic.getComicId());
        mongoChapter.setDesc(comic.getDesc());
        mongoChapter.setUrl(comic.getChapterImageUrl());
        mongoTemplate.insert(mongoChapter);
        return "ok";
    }

    @Override
    public Boolean addChapters(Chapter chapter) {
        int insert = chapterMapper.insert(chapter);
        if (insert > 0) {
            return true;
        }else {
            return false;
        }
    }


}




