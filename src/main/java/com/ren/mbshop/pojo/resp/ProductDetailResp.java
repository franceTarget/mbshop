package com.ren.mbshop.pojo.resp;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Author: target
 * @Date: $ $
 * @Description:
 */
@Data
public class ProductDetailResp {

    private String similarity;

    private String productAttr;

    private String productValue;

    private ReplyResp reply;

    @ApiModelProperty("评价数量")
    private String replyCount;

    private StoreInfoResp storeInfo;
}
