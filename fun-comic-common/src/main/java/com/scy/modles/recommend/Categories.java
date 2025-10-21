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
public class Categories {
    @ApiModelProperty(value = "一级类别名字")
    private String name;
    @ApiModelProperty(value = "类别id")
    private Integer id;
    @ApiModelProperty(value = "二级模块集合")
    private List<SubModules> subModules;


}
