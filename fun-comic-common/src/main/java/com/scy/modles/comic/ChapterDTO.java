package com.scy.modles.comic;

import lombok.Data;

import java.util.List;

/**
 * @author 24022
 */
@Data
public class ChapterDTO {
    private Integer chapterId;
    private Integer comicId;
    private String chapterName;
    private String chapterImage;
    private Integer isPay;
//    private String payType;
    private String payPrice;
    private List<String> chapterUrl;
}
