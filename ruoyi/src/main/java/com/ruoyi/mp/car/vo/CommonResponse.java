package com.ruoyi.mp.car.vo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * Created by zzw on 2020/2/10
 * <p>
 * 统一响应的数据格式。包含成功和错误。可默认或自定义code、msg、data
 */
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
//保证序列化json的时候,如果是null的对象,key也会消失
@NoArgsConstructor
public class CommonResponse<T> implements Serializable {

    private int status;
    private String msg;
    private T data;


    private CommonResponse(int status) {
        this.status = status;
    }

    private CommonResponse(int status, T data) {
        this.status = status;
        this.data = data;
    }

    private CommonResponse(int status, String msg, T data) {
        this.status = status;
        this.msg = msg;
        this.data = data;
    }

    private CommonResponse(int status, String msg) {
        this.status = status;
        this.msg = msg;
    }

    @JsonIgnore
    //使之不在json序列化结果当中
    public boolean isSuccess() {
        return this.status == ResponseCode.SUCCESS.getCode();
    }

    public int getStatus() {
        return status;
    }

    public T getData() {
        return data;
    }

    public String getMsg() {
        return msg;
    }

    // 创建一条成功的响应，只返回状态码
    public static <T> CommonResponse<T> createBySuccess() {
        return new CommonResponse<T>(ResponseCode.SUCCESS.getCode());
    }

    // 创建一条成功的响应，返回状态码，消息
    public static <T> CommonResponse<T> createBySuccessMessage(String msg) {
        return new CommonResponse<T>(ResponseCode.SUCCESS.getCode(), msg);
    }

    // 创建一条成功的响应，返回状态码，数据
    public static <T> CommonResponse<T> createBySuccess(T data) {
        return new CommonResponse<T>(ResponseCode.SUCCESS.getCode(), data);
    }

    // 创建一条成功的响应，返回状态码，消息，数据
    public static <T> CommonResponse<T> createBySuccess(String msg, T data) {
        return new CommonResponse<T>(ResponseCode.SUCCESS.getCode(), msg, data);
    }

    // 创建一条错误的响应，返回 默认 错误状态码，默认 错误提示消息。
    public static <T> CommonResponse<T> createByError() {
        return new CommonResponse<T>(ResponseCode.ERROR.getCode(), ResponseCode.ERROR.getDesc());
    }

    // 创建一条错误的响应，返回 默认 错误状态码，自定义 错误提示消息。
    public static <T> CommonResponse<T> createByErrorMessage(String errorMessage) {
        return new CommonResponse<T>(ResponseCode.ERROR.getCode(), errorMessage);
    }

    // 创建一条错误的响应，返回 自定义 错误状态码，自定义 错误提示消息。
    public static <T> CommonResponse<T> createByErrorCodeMessage(int errorCode, String errorMessage) {
        return new CommonResponse<T>(errorCode, errorMessage);
    }

}
