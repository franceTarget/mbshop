package com.ren.mbshop.pojo.resp;

import com.ren.mbshop.pojo.resp.product.ProductResp;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@ApiModel("首页数据")
@Data
public class IndexResp {

    @ApiModelProperty("横幅")
    private List<BannerResp> banners;

    @ApiModelProperty("精品推荐")
    private List<ProductResp> bests;

    @ApiModelProperty("首发新品")
    private List<ProductResp> news;

    @ApiModelProperty("热卖单品")
    private List<ProductResp> hots;

    @ApiModelProperty("打折促销")
    private List<ProductResp> benefits;

    @ApiModelProperty("广告位")
    private List lovelys;

    @ApiModelProperty("菜单栏")
    private List<MenuResp> menus;

    @ApiModelProperty("猜你喜欢")
    private List<ProductResp> likes;
}
