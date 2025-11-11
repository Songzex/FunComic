package com.scy.modles.comic;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @author 24022
 */
@Data
public class AddComic {
    private String comicid;
    private String author;

    private String description;
    private String coverimage;

    private String tags;

    private String status;

    private String  salesType;

    private BigDecimal price;

}
