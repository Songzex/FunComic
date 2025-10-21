package com.scy.modles.comic;

import lombok.Data;

import java.util.List;

/**
 * @author 24022
 */
@Data
public class ResponseComic {
    private String comicId;
    private String title;
    private String author;
    private String description;
    private String coverImage;
    /**
     * 是否付费 0:没有购 1:已购
     */
    private Integer isPay;
    private String tags;
    /**
     * 状态 0:连载中 1:完结
     */
    private String status;
    /**
     * 销售方式 0:免费 1:付费
     */
    private Integer salesType;
    private String price;
    private List<ChapterDTO> chapterList;
}
