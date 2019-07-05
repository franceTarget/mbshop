package com.ren.mbshop.controller;

import com.alibaba.fastjson.JSONObject;
import com.ren.mbshop.common.response.Response;
import com.ren.mbshop.common.response.RestHeader;
import com.ren.mbshop.common.utils.AesCbcUtil;
import com.ren.mbshop.common.utils.FileUtil;
import com.ren.mbshop.common.utils.RestTemplateUtil;
import com.ren.mbshop.common.utils.StringUtil;
import com.ren.mbshop.pojo.model.SysConfig;
import com.ren.mbshop.pojo.request.DecryptCodeReq;
import com.ren.mbshop.pojo.request.UserInfoReq;
import com.ren.mbshop.pojo.resp.DecryptCodeResp;
import com.ren.mbshop.pojo.resp.WecatLoginResp;
import com.ren.mbshop.pojo.vo.LoginConfigVO;
import com.ren.mbshop.service.SysConfigService;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/login")
public class LoginController {

    @Value("${weixin.login.file}")
    private String path;

    @Autowired
    private SysConfigService sysConfigService;

    @ApiOperation("小程序首次登陆")
    @PostMapping("/first/setCode")
    public Response<WecatLoginResp> firstLogin(@RequestBody UserInfoReq req) {

        String url = "https://api.weixin.qq.com/sns/jscode2session";

        SysConfig sysConfig = sysConfigService.findById("1521301811");
        LoginConfigVO loginConfigVO = JSONObject.parseObject(sysConfig.getJsonData(), LoginConfigVO.class);

        Map<String, String> params = new HashMap<>();
        params.put("appid", loginConfigVO.getAppid());
        params.put("secret", loginConfigVO.getSecret());
        params.put("js_code", req.getCode());
        params.put("grant_type", "authorization_code");

        url += req.getCode();
        RestHeader restHeader = new RestHeader();
        restHeader.setMediaType(MediaType.APPLICATION_JSON);
        String s = RestTemplateUtil.doGet(params, restHeader, url);
        WecatLoginResp wecatLoginResp = JSONObject.parseObject(s, WecatLoginResp.class);

        return Response.ok("", wecatLoginResp);
    }

    @ApiOperation("获取用户信息")
    @PostMapping("/query/user/info")
    public Response<DecryptCodeResp> getLoginUserInfo(@RequestBody DecryptCodeReq req) {

        if (StringUtil.isEmpty(req.getEncryptedData())) {
            return null;
        }

        SysConfig sysConfig = sysConfigService.findById("1521301811");

        LoginConfigVO loginConfigVO = JSONObject.parseObject(sysConfig.getJsonData(), LoginConfigVO.class);

        //小程序唯一标识   (在微信小程序管理后台获取)
        String wxspAppid = loginConfigVO.getAppid();
        //小程序的 app secret (在微信小程序管理后台获取)
        String wxspSecret = loginConfigVO.getSecret();
        //授权（必填）
        String grant_type = "authorization_code";

        //////////////// 2、对encryptedData加密数据进行AES解密 ////////////////
        String userInfo = AesCbcUtil.getUserInfo(req.getEncryptedData(), req.getSession_key(), req.getIv());

        JSONObject jsonObject = JSONObject.parseObject(userInfo);

        log.info("=========返回用户信息[{}]==========", userInfo);

        DecryptCodeResp resp = new DecryptCodeResp();

        resp.setUid(jsonObject.getString("openId"));

        return Response.ok("", resp);

    }
}
