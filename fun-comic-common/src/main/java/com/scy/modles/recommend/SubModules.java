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
public class SubModules {
    @ApiModelProperty(value = "二级类别名字 保留字段暂不使用")
    private String name;
    @ApiModelProperty(value = "二级类别名字")
    private String type;
    @ApiModelProperty(value = "二级类别下的漫画")
    private List<ComicBook> books;
}
