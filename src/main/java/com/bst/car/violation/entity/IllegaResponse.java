package com.bst.car.violation.entity;

import lombok.Data;

@Data
public class IllegaResponse {
    private Integer status;
    private String msg;
    private IllegaResult result;
}
