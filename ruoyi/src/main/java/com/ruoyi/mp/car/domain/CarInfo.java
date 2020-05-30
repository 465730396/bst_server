package com.ruoyi.mp.car.domain;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class CarInfo implements Serializable {
    private String lsprefix;
    private String lsnum;
    private String engineno;
    private String frameno;
    private String mobile;

    private String createTime;
    private String updateTime;
    private Date lastQueryTime;
    private int queryCount;

    private String[] illegaNumbers;
}
