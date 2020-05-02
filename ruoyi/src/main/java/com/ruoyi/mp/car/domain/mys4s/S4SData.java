package com.ruoyi.mp.car.domain.mys4s;

import lombok.Data;

import java.util.List;

@Data
public class S4SData {
    private Integer code;
    private String msg;

    private Integer Total;
    private Integer Degree;
    private Integer Fine;
    private Integer Vin;

    private String Engine;
    private String CarType;
    private String Source;
    private Integer Cost;
    private List<S4SRecord> Records;
}
