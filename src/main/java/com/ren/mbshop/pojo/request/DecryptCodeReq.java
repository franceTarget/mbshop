package com.ren.mbshop.pojo.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class DecryptCodeReq {

    @ApiModelProperty("加密信息")
    private String encryptedData;

    @ApiModelProperty("错误信息")
    private String errMsg;

    @ApiModelProperty("初始值")
    private String iv;

    @ApiModelProperty("session")
    private String session_key;

    @ApiModelProperty("签名")
    private String signature;

    @ApiModelProperty("用户信息")
    private UserInfoReq userInfo;

    @ApiModelProperty("推广人二维码id")
    private String spid;

    @ApiModelProperty("推广人二维码id")
    private String spreadid;
}
