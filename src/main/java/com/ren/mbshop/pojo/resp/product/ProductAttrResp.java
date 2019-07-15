package com.ren.mbshop.pojo.resp.product;

import lombok.Data;

import java.util.List;

@Data
public class ProductAttrResp {

    private String attrName;

    private List<ProductAttrValueResp> attr_value;
}
