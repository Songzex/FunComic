package com.scy.business.comic;

import com.scy.api.R;
import com.scy.api.Result;
import com.scy.business.comic.service.AppChapterService;
import com.scy.business.comic.service.AppComicService;
import com.scy.business.comic.service.AppUploadServiceA;
import com.scy.modles.comic.AddChapterDTO;
import com.scy.modles.comic.AddComic;
import com.scy.modles.comic.ResponseComic;
import com.scy.modles.recommend.*;
import com.scy.modles.search.SearchDRO;
import io.swagger.v3.oas.annotations.Operation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

/**
 * 阅读漫画
 * @author 24022
 */
@RestController
@RequestMapping("app/public")
@CrossOrigin
@Slf4j
public class ComicController {

    @Autowired private AppComicService comicService;

    @Autowired private AppChapterService appChapterService;
    @Autowired private AppUploadServiceA  AppUploadService;

    /**
     * description:获取推荐漫画
     */
    @GetMapping("/indexComic")
    @Operation(
            summary = "获取推荐漫画",
            description = "获取首页推荐的漫画列表，包含多个分类和每个分类下的漫画作品。返回数据包括：For You、Romance、Adult、Action等分类，每个分类下包含相应的漫画作品信息。"
    )
    public Result<TypesOfHomePages> recommendComic() {
        ArrayList<Categories> categories1list= new ArrayList<>();
        //制造假数据 一级分类
        Categories categories = new Categories();
        categories.setId(1);
        categories.setName("For you");
        //二级分类
        ArrayList<SubModules> list = new ArrayList<>();
        SubModules modules = new SubModules();
        modules.setType("This week‘s update");
        ComicBook book = new ComicBook();
        ArrayList<ComicBook> books = new ArrayList<>();
        book.setName("The first chapter");
        book.setPostUrl("https://pic4.zhimg.com/v2-fa6e33bd820e828b8cc5a880ecb5a6bb_r.jpg");
        book.setComicId("tf12");
        books.add(book);
        modules.setBooks(books);
        list.add(modules);
        categories.setSubModules(list);
        categories1list.add(categories);


        //制造假数据 一级分类
        Categories categories2 = new Categories();
        categories2.setId(1);
        categories2.setName("Romance");
        ArrayList<SubModules> list2 = new ArrayList<>();
        SubModules modules2 = new SubModules();
        modules2.setType("Top 9 Best Series");
        ComicBook book2 = new ComicBook();
        ArrayList<ComicBook> books2 = new ArrayList<>();
        book2.setName("The first chapter");
        book2.setPostUrl("https://pic4.zhimg.com/v2-fa6e33bd820e828b8cc5a880ecb5a6bb_r.jpg");
        book2.setComicId("tf12");
        books2.add(book);
        modules2.setBooks(books2);
        list2.add(modules2);
        categories2.setSubModules(list2);
        categories1list.add(categories2);





        Categories categories3 = new Categories();
        categories2.setId(1);
        categories2.setName("Adult");
        ArrayList<SubModules> list3 = new ArrayList<>();
        SubModules modules3 = new SubModules();
        modules3.setType("Most Viewed");
        ComicBook book3 = new ComicBook();
        ArrayList<ComicBook> books3 = new ArrayList<>();
        book3.setName("The first chapter");
        book3.setPostUrl("https://pic4.zhimg.com/v2-fa6e33bd820e828b8cc5a880ecb5a6bb_r.jpg");
        book3.setComicId("tf12");
        books3.add(book);
        modules3.setBooks(books3);
        list3.add(modules3);
        categories3.setSubModules(list3);
        categories1list.add(categories3);



        Categories categories4 = new Categories();
        categories4.setId(1);
        categories4.setName("Action");
        ArrayList<SubModules> list4= new ArrayList<>();
        SubModules modules4 = new SubModules();
        modules4.setType("Top 9 Best Series");
        ComicBook book4 = new ComicBook();
        ArrayList<ComicBook> books4= new ArrayList<>();
        book4.setName("The first chapter");
        book4.setPostUrl("https://pic4.zhimg.com/v2-fa6e33bd820e828b8cc5a880ecb5a6bb_r.jpg");
        book4.setComicId("tf12");
        books4.add(book);
        modules4.setBooks(books2);
        list4.add(modules);
        categories4.setSubModules(list4);
        categories1list.add(categories4);


        TypesOfHomePages types = new TypesOfHomePages();
        ArrayList<Swiper> swipes = new ArrayList<>();
        Swiper swiper = new Swiper("https://pic4.zhimg.com/v2-fa6e33bd820e828b8cc5a880ecb5a6bb_r.jpg",
                "The first chapter", "tf12");
        Swiper swiper2 = new Swiper("https://pic4.zhimg.com/v2-fa6e33bd820e828b8cc5a880ecb5a6bb_r.jpg",
                "The first chapter", "tf12");
        Swiper swiper3 = new Swiper("https://pic4.zhimg.com/v2-fa6e33bd820e828b8cc5a880ecb5a6bb_r.jpg",
                "The first chapter", "tf12");
        swipes.add(swiper);
        swipes.add(swiper2);
        swipes.add(swiper3);
        types.setSwiper(swipes);
        types.setCategories(categories1list);
        return new Result<TypesOfHomePages>().ok(types);
    }

    
    /**
     * 添加漫画
     * @return
     */
    @PostMapping("/addComic")
    @Operation(
            summary = "添加漫画"
    )
    public R addComic(@RequestBody AddComic comic) {
     String msg=comicService.addComic(comic);
        return R.ok(msg);
    }



    /**
     * 添加漫画章节
     * @return
     */
    @PostMapping("/addChapter")
    @Operation(
            summary = "添加漫画章节"
    )
    public R addChapter(@RequestBody AddChapterDTO comic) {
        String msg=appChapterService.addChapter(comic);
        return R.ok(msg);
    }

    /**
     * 通过搜索数据的漫画的获取
     * @param from sssssssss
     * @return
     */
    @PostMapping("/getComicByEs")
    @Operation(
            summary = "搜索数据的漫画的获取"
    )
    public Result< List<ComicBook>> getComicByes(@RequestBody SearchDRO from) {
       List<ComicBook>  list =comicService.getComicByes(from);
        return  new Result< List<ComicBook>>().ok(list);
    }


    @PostMapping("/getComicById")
    @Operation(
            summary = "获取漫画详情",
            description = "获取漫画详情"
    )
    public Result<ResponseComic> getComicById(@RequestParam("comicId") String comicId ){
        ResponseComic chapter = comicService.getChapter(comicId);
        return new Result<ResponseComic>().ok(chapter);
    }
    /**
     * 漫画种类接口
     * @return
     */
    @PostMapping("/comicCateGory")
    @Operation(
            summary = "漫画种类接口",
            description = "漫画种类接口"
    )
    public Result<List<String>> comicCateGory() {
        List<String> index = comicService.comicCateGory();
        return new Result<List<String>>().ok( index);
    }


    /**
     * 漫画ZIP包上传接口
     * @param file 漫画ZIP压缩
     * @return 处理后的漫画信息，包含章节和图片URL
     */
    @PostMapping("/comics/upload")
    @Operation(
            summary = "漫画ZIP包上传接口"
    )
    public R uploadComic(
            @RequestParam("file") MultipartFile file ) {

        AppUploadService.uploadComic( file);
        return null;
    }
//    /**
//     * 分页获取搜索的漫画
//     * @param from  sdsdsd
//     * @return
//     */
//    @PostMapping("/getComicWithLimit")
//   // @Cacheable(value = "getComicWithLimit", key = "'getComicWithLimit'", unless = "#result == null")
//    public ResponseResult<User> getComicWithLimit(@RequestBody LoginFrom from ) {
//        return  ResponseResult.success();
//    }

}
