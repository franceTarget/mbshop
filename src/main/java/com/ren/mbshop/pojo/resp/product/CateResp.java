package com.ren.mbshop.pojo.resp.product;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class CateResp {

    @ApiModelProperty("父id")
    private String pid;

    @ApiModelProperty("分类id")
    private String id;

    @ApiModelProperty("分类名称")
    private String cateName;
}
