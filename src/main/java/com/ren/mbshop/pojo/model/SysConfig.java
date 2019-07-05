package com.ren.mbshop.pojo.model;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class SysConfig implements Serializable {

    private static final long serialVersionUID=1L;

    private Integer id;

    private String type;

    private String name;

    private String jsonData;

    private String createBy;

    private Date createTime;

    private String updateBy;

    private Date updateTime;
}
