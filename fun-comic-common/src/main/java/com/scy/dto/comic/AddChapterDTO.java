package com.scy.dto.comic;

import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * @author 24022
 */
@Data
public class AddChapterDTO {
    private String chapterId;
    private String comicId;
    private Integer idComic;
    /**
     * 第一话
     */
    private String desc;
    private String title;
    private List<String> chapterImageUrl;
    private String price;
    private String idFree;
    private String payPrice;

}
