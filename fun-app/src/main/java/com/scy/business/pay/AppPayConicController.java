package com.scy.business.pay;

import com.scy.api.R;
import com.scy.business.comic.service.AppComicService;
import com.scy.modles.comic.AddComic;
import io.swagger.v3.oas.annotations.Operation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author 24022
 */
@RestController
@RequestMapping("app/pay")
@CrossOrigin
@Slf4j
public class AppPayConicController {


    @Autowired
    private AppComicService comicService;
    @PostMapping("/openChapter")
    @Operation(
            summary = "解锁章节"
    )
    public R openChapter(@RequestBody AddComic comic) {
        String msg=comicService.addComic(comic);
        return R.ok(msg);
    }

}
