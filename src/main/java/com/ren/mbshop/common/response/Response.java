package com.ren.mbshop.common.response;

import lombok.Data;
import lombok.ToString;

@ToString
@Data
public class Response<T> {
    public static final Integer OK = 200;
    public static final Integer Fail = -1;
    private int code;
    private String msg;
    private T data;

    public static Response ok(String msg) {
        return new Response(OK, msg);
    }

    public static <T> Response ok(String msg, T data) {
        return new Response(OK, msg, data);
    }

    public static <T> Response ok(T data) {
        return new Response(OK, "操作成功", data);
    }

    public static Response failed(String message) {
        return new Response(Fail, message);
    }

    public Response(int code, String message) {
        this(code, message, (T) null);
    }

    public Response(int code, String message, T data) {
        this.code = code;
        this.msg = message;
        this.data = data;
    }
}
