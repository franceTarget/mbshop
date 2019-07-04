package com.ren.mbshop.controller;

import com.ren.mbshop.common.response.Response;
import com.ren.mbshop.pojo.resp.BannerResp;
import com.ren.mbshop.pojo.resp.IndexResp;
import com.ren.mbshop.pojo.resp.MenuResp;
import com.ren.mbshop.pojo.resp.ProductResp;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/index")
public class IndexController {

    @GetMapping("/query/all")
    public Response<IndexResp> queryAll() {
        IndexResp indexResp = new IndexResp();
        List<MenuResp> menuRespList = new ArrayList<>();
        MenuResp menuResp = new MenuResp();
        menuResp.setName("小清新");
        menuResp.setPic("../../images/sign-bg.jpg");
        menuResp.setShow("1");
        menuResp.setUrl("pages/productSort/productSort");
        menuRespList.add(menuResp);

        BannerResp bannerResp1 = new BannerResp();
        bannerResp1.setPic("../../images/123.jpg");
        bannerResp1.setUrl("pages/productSort/productSort");
        BannerResp bannerResp2 = new BannerResp();
        bannerResp2.setPic("../../images/124.jpg");
        bannerResp2.setUrl("pages/productSort/productSort");
        List<BannerResp> bannerResps = new ArrayList<>();
        bannerResps.add(bannerResp1);
        bannerResps.add(bannerResp2);

        ProductResp productResp = new ProductResp();
        productResp.setId("111");
        productResp.setImage("../../images/sign-bg.jpg");
        productResp.setPrice(2132.98);
        productResp.setStoreName("小可爱");
        List<ProductResp> bests = new ArrayList<>();
        bests.add(productResp);

        ProductResp productResp1 = new ProductResp();
        productResp1.setId("111");
        productResp1.setImage("../../images/sign-bg.jpg");
        productResp1.setPrice(2132.98);
        productResp1.setStoreName("小可爱");
        List<ProductResp> news = new ArrayList<>();
        news.add(productResp1);

        ProductResp productResp2 = new ProductResp();
        productResp2.setId("111");
        productResp2.setImage("../../images/sign-bg.jpg");
        productResp2.setPrice(2132.98);
        productResp2.setStoreName("小可爱");
        List<ProductResp> hots = new ArrayList<>();
        hots.add(productResp2);

        ProductResp productResp3 = new ProductResp();
        productResp3.setId("111");
        productResp3.setImage("../../images/sign-bg.jpg");
        productResp3.setPrice(2132.98);
        productResp3.setOtPrice(3123.88);
        productResp3.setStoreName("小可爱");
        List<ProductResp> benefits = new ArrayList<>();
        benefits.add(productResp3);

        ProductResp productResp4 = new ProductResp();
        productResp4.setId("111");
        productResp4.setImage("../../images/sign-bg.jpg");
        productResp4.setPrice(2132.98);
        productResp4.setStoreName("小可爱");
        List<ProductResp> likes = new ArrayList<>();
        likes.add(productResp4);

        indexResp.setMenus(menuRespList);
        indexResp.setBanners(bannerResps);
        indexResp.setBests(bests);
        indexResp.setNews(news);
        indexResp.setHots(hots);
        indexResp.setBenefits(benefits);
        indexResp.setLikes(likes);
        return Response.ok("", indexResp);
    }

    @GetMapping("/query/likes")
    public Response<IndexResp> getLikes() {
        IndexResp indexResp = new IndexResp();
        return Response.ok("", indexResp);
    }

}
