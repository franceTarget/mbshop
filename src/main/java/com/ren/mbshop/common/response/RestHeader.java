package com.ren.mbshop.common.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.http.MediaType;

import java.util.Map;

@ApiModel("RestTemplate请求header实体")
@Data
public class RestHeader {

    @ApiModelProperty("content-type")
    private MediaType mediaType;

    @ApiModelProperty("额外的请求报文头部参数：例如token等")
    private Map<String,String> headers;
}
