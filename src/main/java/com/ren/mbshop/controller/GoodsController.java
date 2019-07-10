package com.ren.mbshop.controller;

import com.ren.mbshop.common.response.Response;
import com.ren.mbshop.pojo.resp.ReplyResp;
import com.ren.mbshop.pojo.resp.product.ProductListResp;
import com.ren.mbshop.pojo.resp.product.StoreInfoResp;
import com.ren.mbshop.pojo.resp.product.ProductAttrResp;
import com.ren.mbshop.pojo.resp.product.ProductDetailResp;
import com.ren.mbshop.service.GoodsService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
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


        //商品属性
        List<ProductAttrResp> productAttrResps = new ArrayList<>();

        ProductAttrResp productAttrResp = new ProductAttrResp();
        List<String> attrs = new ArrayList<>();
        attrs.add("幸福跳动");
        attrs.add("幸福深度");
        productAttrResp.setAttr_values(attrs);
        productAttrResps.add(productAttrResp);

        productDetailResp.setProductAttr(productAttrResps);

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


    @ApiOperation("获取产品分销二维码")
    @GetMapping("/promotion/routine/code")
    public Response<String> getRoutineCode(@RequestParam("uid") String uid) {
        return Response.ok("", "https://daohang.qq.com");
    }


    @ApiOperation("获取产品分类")
    @GetMapping("/pid/cate")
    public Response<List<String>> getPidCate(@RequestParam("uid") String uid) {
        List<String> list = new ArrayList<>();
        list.add("包包");
        list.add("鞋鞋");
        return Response.ok("", list);
    }

    @ApiOperation("获取产品列表")
    @GetMapping("/query/list")
    public Response<List<String>> getProductList(@RequestParam("uid") String uid,
                                                 @RequestParam(value = "sid",required = false,defaultValue = "") String sid,
                                                 @RequestParam(value = "cid",required = false,defaultValue = "") String cid,
                                                 @RequestParam(value = "priceOrder",required = false,defaultValue = "") String priceOrder,
                                                 @RequestParam(value = "salesOrder",required = false,defaultValue = "") String salesOrder,
                                                 @RequestParam(value = "news",required = false,defaultValue = "") String news,
                                                 @RequestParam(value = "first",required = false,defaultValue = "1") Integer first,
                                                 @RequestParam(value = "limit",required = false,defaultValue = "10") Integer limit) {
        List<ProductListResp> list = new ArrayList<>();
        ProductListResp resp = new ProductListResp();
        resp.setId("111");
        resp.setImage("../../images/sign-bg.jpg");
        resp.setFicti(12);
        resp.setSales(22);
        resp.setPrice(1232.12);
        resp.setStoreName("飞机杯");
        return Response.ok("", list);
    }

}
