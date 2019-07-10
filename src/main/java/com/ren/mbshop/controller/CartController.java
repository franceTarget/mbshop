package com.ren.mbshop.controller;

import com.ren.mbshop.common.response.Response;
import com.ren.mbshop.pojo.resp.product.ProductDetailResp;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cart")
public class CartController {

    @ApiOperation("查询购物车数量")
    @GetMapping("/query/count")
    public Response<Integer> getDetails(@RequestParam("uid") String uid) {
        return Response.ok("",2);
    }
}
