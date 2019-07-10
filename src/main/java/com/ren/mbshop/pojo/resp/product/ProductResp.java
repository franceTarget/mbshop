package com.ren.mbshop.pojo.resp.product;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class ProductResp {

    @ApiModelProperty("商品id")
    private String id;

    @ApiModelProperty("商品名称")
    private String storeName;

    @ApiModelProperty("商品图片")
    private String image;

    @ApiModelProperty("商品现价")
    private Double price;

    @ApiModelProperty("商品原价")
    private Double otPrice;

    @ApiModelProperty("商品剩余数量")
    private Integer stock;

}
