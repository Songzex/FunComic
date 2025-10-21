package com.example.funcomicadmin.comic;


import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/public")
@CrossOrigin
@Slf4j
public class adminComicController {
//  @Autowired
//  private ComicService comicService;

//
//   /**
//    * 导入漫画单个手导入
//    * @param comic
//    * @return
//    */
//  @PostMapping("/addComic")
//  public ResponseResult<User> addComic(@RequestBody Comic comic ) {
//      comicService.addComic(comic);
//      log.info(comic.getAuthor());
//   return  ResponseResult.success();
//  }
//
//   /**
//    * 导入章节数据
//    * @param from
//    * @return
//    */
//   @PostMapping("/addAdapter")
//   public ResponseResult<User> addAdapter(@RequestParam("file") MultipartFile file ) throws IOException {
//        comicService.addAdapter(1,file);
//      return  ResponseResult.success();
//   }
//
//   /**
//    * 修改漫画的状态
//    * @param from
//    * @return
//    */
//   @PostMapping("/updateComic")
//   public ResponseResult<User> updateComic(@RequestBody LoginFrom from ) {
//      return  ResponseResult.success();
//   }
//
//   /**
//    * 删除漫画
//    * @param from
//    * @return
//    */
//   @PostMapping("/deleteComic")
//   public ResponseResult<User> deleteComic(@RequestBody LoginFrom from ) {
//      return  ResponseResult.success();
//   }
//
//   /**
//    * 修改章节
//    * @param from
//    * @return
//    */
//   @PostMapping("/updateChapter")
//   public ResponseResult<User> updateChapter(@RequestBody LoginFrom from ) {
//      return  ResponseResult.success();
//   }
//
//   /**
//    * 设置推荐栏的漫画操作 4个
//    * @param from
//    * @return
//    */
//   @PostMapping("/recommendComic")
//   public ResponseResult<User> recommendComic(@RequestBody LoginFrom from ) {
//
//      return  ResponseResult.success();
//   }
//
//    /**
//     * 漫画标签查询
//     */
//    @PostMapping("/getComicTag")
//    public ResponseResult<Set<String>> getTypeComicTag( ) {
//        Set<String> list = comicService.findcomicTag();
//        return  ResponseResult.success(list);
//    }
//
//    /**
//     *模糊查询 漫画
//     * @param ridoPages
//     * @return
//     */
//    @PostMapping("/selectlike")
//    // @Cacheable(value = "comic", key = "'recommendComic'", unless = "#result == null")
//    public ResponseResult<List<Comic> > selectlike(@RequestBody RidoPages ridoPages) {
//        List<Comic> pagelikeby = comicService.pagelikeby(ridoPages);
//        return  ResponseResult.success(pagelikeby);
//    }
}

