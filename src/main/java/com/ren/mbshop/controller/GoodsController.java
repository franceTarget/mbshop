package com.ren.mbshop.controller;

import com.ren.mbshop.common.response.Response;
import com.ren.mbshop.pojo.resp.ProductDetailResp;
import com.ren.mbshop.pojo.resp.ReplyResp;
import com.ren.mbshop.pojo.resp.StoreInfoResp;
import com.ren.mbshop.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/goods")
public class GoodsController {

    @Autowired
    private GoodsService goodsService;

    @GetMapping("/query/details")
    public Response<ProductDetailResp> getDetails(@RequestParam("id") String id) {
        ProductDetailResp productDetailResp = new ProductDetailResp();

        StoreInfoResp storeInfoResp = new StoreInfoResp();
        storeInfoResp.setDescription("非常好的商品");
        storeInfoResp.setImage("../../images/sign-bg.jpg");
        List<String> list = new ArrayList<>();
        list.add("../../images/124.jpg");
        list.add("../../images/123.jpg");
        storeInfoResp.setSliderImage(list);
        storeInfoResp.setKeyword("绝对好看,你值得拥有");
        storeInfoResp.setPrice(112.00);
        storeInfoResp.setStock(12);
        storeInfoResp.setSales(11);
        storeInfoResp.setFicti(123);
        storeInfoResp.setUnitName("个");
        storeInfoResp.setOtPrice(369.00);
        storeInfoResp.setStoreName("飞机杯");
        storeInfoResp.setUserCollect("user");
        productDetailResp.setStoreInfo(storeInfoResp);

        productDetailResp.setProductAttr("puAtrr");
        productDetailResp.setProductValue("pv12");
        //用户评论
        ReplyResp replyResp = new ReplyResp();
        replyResp.setAvatar("../../images/sign-bg.jpg");
        replyResp.setAddTime("2018-11-11 15:24:36");
        replyResp.setComment("东西真棒，得劲");
        replyResp.setNickname("灰机");
        replyResp.setMerchantReplyContent("谢谢支持");
        replyResp.setMerchantReplyTime("2018-11-12 18:26:30");
        productDetailResp.setReply(replyResp);
        productDetailResp.setReplyCount("2");

        productDetailResp.setSimilarity("simi");


        return Response.ok("", productDetailResp);
    }

}
