package com.ren.mbshop.pojo.resp.product;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class ProductListResp {

    @ApiModelProperty("商品id")
    private String id;

    @ApiModelProperty("商品名称")
    private String storeName;

    @ApiModelProperty("商品图片")
    private String image;

    @ApiModelProperty("商品现价")
    private Double price;

    @ApiModelProperty("虚构售出数量")
    private Integer ficti;

    @ApiModelProperty("实际卖出数量")
    private Integer sales;
}
