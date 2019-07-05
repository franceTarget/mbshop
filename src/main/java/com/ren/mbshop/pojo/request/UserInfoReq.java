package com.ren.mbshop.pojo.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class UserInfoReq {

    @ApiModelProperty("用户头像")
   private String avatarUrl;
    @ApiModelProperty("城市")
   private String city;
    @ApiModelProperty("登陆code")
    private String code;
    @ApiModelProperty("国籍")
    private String country;
    private Integer gender;
    @ApiModelProperty("语言")
    private String language;
    @ApiModelProperty("昵称")
    private String nickName;
    private String province;
    private String spid;
    private String spreadid;
}
