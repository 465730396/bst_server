package com.bst.car.violation.vo;

/**
 * Created by zzw on 2020/2/10
 */
public enum ResponseCode {

    SUCCESS(1, "SUCCESS"), // 默认成功
    ERROR(0, "ERROR"); // 默认错误

    private final int code;
    private final String desc;

    ResponseCode(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public int getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }
}
