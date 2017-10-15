package com.eden.agent.common.web;

import java.io.Serializable;

/**
 * Created by liyuanhang on 2017/4/22.
 */
public class BaseResponse<T> implements Serializable {
    private int code;
    private String msg;
    private T data;

    public BaseResponse(T data) {
        this.data = data;
    }

    public BaseResponse(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public static BaseResponse success() {
        return new BaseResponse(0, "成功");
    }

    public static BaseResponse fail(int code, String msg) {
        return new BaseResponse(code, msg);
    }

    public static BaseResponse fail(int code) {
        return new BaseResponse(code, "失败");
    }
}
