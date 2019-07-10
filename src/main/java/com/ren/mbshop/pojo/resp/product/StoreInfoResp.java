package com.ren.mbshop.pojo.resp.product;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @Author: target
 * @Date: $ $
 * @Description:
 */
@Data
public class StoreInfoResp {

    @ApiModelProperty("关键字说明")
    private String keyword;

    @ApiModelProperty("描述")
    private String description;

    private String userCollect;

    private String image;

    @ApiModelProperty("滑动图片")
    private List<String> sliderImage;

    @ApiModelProperty("库存数量")
    private Integer stock;

    @ApiModelProperty("库存单位")
    private String unitName;

    @ApiModelProperty("虚构售出数量")
    private Integer ficti;

    @ApiModelProperty("实际卖出数量")
    private Integer sales;

    @ApiModelProperty("商品名称")
    private String storeName;

    @ApiModelProperty("商品售价")
    private Double price;

    @ApiModelProperty("商品原价")
    private Double otPrice;
}
