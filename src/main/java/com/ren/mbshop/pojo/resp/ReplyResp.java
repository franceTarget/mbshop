package com.ren.mbshop.pojo.resp;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Author: target
 * @Date: $ $
 * @Description:
 */
@Data
public class ReplyResp {

    @ApiModelProperty("用户头像")
    private String avatar;

    @ApiModelProperty("昵称")
    private String nickname;

    @ApiModelProperty("评论内容")
    private String comment;

    @ApiModelProperty("评论时间")
    private String addTime;

    @ApiModelProperty("回复内容")
    private String merchantReplyContent;

    @ApiModelProperty("回复时间")
    private String merchantReplyTime;
}
