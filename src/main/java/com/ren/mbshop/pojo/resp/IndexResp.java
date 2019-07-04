package com.ren.mbshop.pojo.resp;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class IndexResp {

    private List banners = new ArrayList<>();

    private List bests= new ArrayList<>();

    private List news= new ArrayList<>();

    private List hots= new ArrayList<>();

    private List benefits= new ArrayList<>();

    private List lovelys= new ArrayList<>();

    private List menus= new ArrayList<>();

    private List likes= new ArrayList<>();
}
