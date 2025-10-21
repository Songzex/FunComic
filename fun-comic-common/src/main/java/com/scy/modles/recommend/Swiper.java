package com.scy.modles.recommend;

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
public class Swiper {
    @ApiModelProperty(value = "漫画封面图片")
    private String url;
    @ApiModelProperty(value = "漫画名字")
    private String name;
    @ApiModelProperty(value = "漫画id")
    private String id;
}
