package com.ren.mbshop.pojo.resp.product;

import com.ren.mbshop.pojo.resp.ReplyResp;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @Author: target
 * @Date: $ $
 * @Description:
 */
@Data
public class ProductDetailResp {

    private String similarity;

    @ApiModelProperty("产品属性")
    private List<ProductAttrResp> productAttr;

    private String productValue;

    private ReplyResp reply;

    @ApiModelProperty("评价数量")
    private String replyCount;

    private StoreInfoResp storeInfo;
}
