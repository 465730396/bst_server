package com.ruoyi.mp.car.domain;

import lombok.Data;

@Data
public class IllegaResponse {
    private Integer status;
    private String msg;
    private IllegaResult result;
}
