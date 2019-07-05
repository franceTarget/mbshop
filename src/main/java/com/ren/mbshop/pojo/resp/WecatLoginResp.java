package com.ren.mbshop.pojo.resp;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class WecatLoginResp {

    @ApiModelProperty("用户唯一标识")
    private String openid;

    @ApiModelProperty("会话密钥")
    private String session_key;

    /*
    在满足 UnionID 下发条件的情况下会返回，详见 UnionID 机制说明。
     */
    @ApiModelProperty("用户在开放平台的唯一标识符")
    private String unionid;

    @ApiModelProperty("错误码")
    private Integer errcode;

    @ApiModelProperty("错误信息")
    private String errmsg;

}
