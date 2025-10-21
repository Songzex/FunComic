package com.scy.modles.recommend;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author 24022
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TypesOfHomePages {
    @ApiModelProperty(value = "分类列表")
    public List<Categories> categories;

    @ApiModelProperty(value = "轮播图")
    public List<Swiper> swiper;
}
