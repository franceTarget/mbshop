package com.ren.mbshop.common.utils;

import com.alibaba.fastjson.JSONObject;
import com.ren.mbshop.common.config.HttpsClientRequestFactory;
import com.ren.mbshop.common.response.RestHeader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;

/**
 * @author: target
 * @date: 2019/3/29 11:29
 * @description:
 */
public class RestTemplateUtil {

    @Autowired
    private static HttpsClientRequestFactory httpsClientRequestFactory;

    public static String doPost(Object param, RestHeader restHeader, String url) {

        // -------------------------------> 获取Rest客户端实例
        RestTemplate restTemplate = new RestTemplate(new HttpsClientRequestFactory());

        // -------------------------------> 解决(响应数据可能)中文乱码 的问题
        List<HttpMessageConverter<?>> converterList = restTemplate.getMessageConverters();
        converterList.remove(1); // 移除原来的转换器
        // 设置字符编码为utf-8
        HttpMessageConverter<?> converter = new StringHttpMessageConverter(StandardCharsets.UTF_8);
        converterList.add(1, converter); // 添加新的转换器(注:convert顺序错误会导致失败)
        restTemplate.setMessageConverters(converterList);

        // -------------------------------> (选择性设置)请求头信息
        // HttpHeaders实现了MultiValueMap接口
        HttpHeaders httpHeaders = new HttpHeaders();
        if (null != restHeader) {
            httpHeaders.setContentType(restHeader.getMediaType());
            Map<String, String> headers = restHeader.getHeaders();
            if (CollectionUtil.isNotEmpty(headers)) {
                for (Map.Entry<String, String> entry : headers.entrySet()) {
                    httpHeaders.add(entry.getKey(), entry.getValue());
                }
            }
        }
        // ------------------------------->将请求头、请求体数据，放入HttpEntity中
        // 请求体的类型任选即可;只要保证 请求体 的类型与HttpEntity类的泛型保持一致即可
        // 这里手写了一个json串作为请求体 数据 (实际开发时,可使用fastjson、gson等工具将数据转化为json串)

        String s = JSONObject.toJSONString(param);

        HttpEntity<String> httpEntity = new HttpEntity<String>(s, httpHeaders);

        // -------------------------------> URI
        StringBuffer paramsURL = new StringBuffer(url);
        // 字符数据最好encoding一下;这样一来，某些特殊字符才能传过去(如:flag的参数值就是“&”,不encoding的话,传不过去)
        try {
            paramsURL.append("?flag=" + URLEncoder.encode("&", "utf-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        URI uri = URI.create(paramsURL.toString());

        //  -------------------------------> 执行请求并返回结果
        // 此处的泛型  对应 响应体数据   类型;即:这里指定响应体的数据装配为String
        ResponseEntity<String> response =
                restTemplate.exchange(uri, HttpMethod.POST, httpEntity, String.class);
        if (null != response && (response.getStatusCodeValue() == 200 || response.getStatusCodeValue() == 1)) {
            return response.getBody();
        }
        return null;
    }

    public static String doGet(Map<String, String> params, RestHeader restHeader, String url) {
        // -------------------------------> 获取Rest客户端实例
        RestTemplate restTemplate = new RestTemplate(new HttpsClientRequestFactory());

        // -------------------------------> 解决(响应数据可能)中文乱码 的问题
        List<HttpMessageConverter<?>> converterList = restTemplate.getMessageConverters();
        converterList.remove(1); // 移除原来的转换器
        // 设置字符编码为utf-8
        HttpMessageConverter<?> converter = new StringHttpMessageConverter(StandardCharsets.UTF_8);
        converterList.add(1, converter); // 添加新的转换器(注:convert顺序错误会导致失败)
        restTemplate.setMessageConverters(converterList);

        // -------------------------------> (选择性设置)请求头信息
        // HttpHeaders实现了MultiValueMap接口
        HttpHeaders httpHeaders = new HttpHeaders();
        if (null != restHeader) {
            httpHeaders.setContentType(restHeader.getMediaType());
            Map<String, String> headers = restHeader.getHeaders();
            if (CollectionUtil.isNotEmpty(headers)) {
                for (Map.Entry<String, String> entry : headers.entrySet()) {
                    httpHeaders.add(entry.getKey(), entry.getValue());
                }
            }
        }
        // -------------------------------> 注:GET请求 创建HttpEntity时,请求体传入null即可
        // 请求体的类型任选即可;只要保证 请求体 的类型与HttpEntity类的泛型保持一致即可

        HttpEntity<String> httpEntity = new HttpEntity<String>(null, httpHeaders);

        // -------------------------------> URI
        StringBuffer paramsURL = new StringBuffer(url);
        if (CollectionUtil.isNotEmpty(params)) {
            paramsURL.append("?");
            for (Map.Entry<String, String> entry : params.entrySet()) {
                paramsURL.append("&" + entry.getKey() + "=" + entry.getValue());
            }
            // 字符数据最好encoding一下;这样一来，某些特殊字符才能传过去(如:flag的参数值就是“&”,不encoding的话,传不过去)
            try {
                paramsURL.append("&flag=" + URLEncoder.encode("&", "utf-8"));
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }

        URI uri = URI.create(paramsURL.toString());

        //  -------------------------------> 执行请求并返回结果
        // 此处的泛型  对应 响应体数据   类型;即:这里指定响应体的数据装配为String
        ResponseEntity<String> response =
                restTemplate.exchange(uri, HttpMethod.GET, httpEntity, String.class);

        if (null != response && (response.getStatusCodeValue() == 200 || response.getStatusCodeValue() == 1)) {
            return response.getBody();
        }
        return null;
    }


}
