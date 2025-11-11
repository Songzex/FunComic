package com.scy.modles.recommend;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author 24022
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(description = "漫画书籍信息")
public class ComicBook {

    @ApiModelProperty(value = "漫画名称", example = "The first chapter")
    private String name;

    @ApiModelProperty(value = "漫画封面图片URL", example = "https://pic4.zhimg.com/v2-fa6e33bd820e828b8cc5a880ecb5a6bb_r.jpg")
    private String postUrl;

    @ApiModelProperty(value = "漫画作者", example = "Author Name")
    private String author;

    @ApiModelProperty(value = "漫画类型", example = "Romance")
    private String type;

    @ApiModelProperty(value = "漫画ID", example = "tf12")
    private String comicId;
}
