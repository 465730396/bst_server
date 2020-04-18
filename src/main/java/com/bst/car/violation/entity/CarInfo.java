package com.bst.car.violation.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class CarInfo implements Serializable {
    private String lsprefix;
    private String lsnum;
    private String engineno;
    private String frameno;
}
