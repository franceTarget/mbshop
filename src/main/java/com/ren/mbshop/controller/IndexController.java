package com.ren.mbshop.controller;

import com.ren.mbshop.common.response.Response;
import com.ren.mbshop.pojo.resp.IndexResp;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/index")
public class IndexController {

    @GetMapping("/query/all")
    public Response<IndexResp> queryAll() {
        IndexResp indexResp = new IndexResp();
        return Response.ok("", indexResp);
    }

    @GetMapping("/query/likes")
    public Response<IndexResp> getLikes() {
        IndexResp indexResp = new IndexResp();
        return Response.ok("", indexResp);
    }

}
