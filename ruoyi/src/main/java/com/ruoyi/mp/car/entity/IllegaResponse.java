package com.ruoyi.mp.car.entity;

import lombok.Data;

@Data
public class IllegaResponse {
    private Integer status;
    private String msg;
    private IllegaResult result;
}
