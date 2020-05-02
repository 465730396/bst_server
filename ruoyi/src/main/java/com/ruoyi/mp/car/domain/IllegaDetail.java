package com.ruoyi.mp.car.domain;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class IllegaDetail {
            private String lsprefix;
            private String lsnum;
            private String createTime;
            private String updateTime;
            private Integer status ;//默认是1 新增数据
            private BigDecimal processfree;//办理手续费
            private int canprocess;//能否办理

			private String time;//": "2020-03-21 15:00:00",
            private String address;//": "壶口景区专线0000公里400米",
            private String content;//": "机动车乘坐人未使用安全带",
            private String legalnum;//": "30191",
            private String price;//": 50,
            private String score;//": 0,
            private String number;//": "6106300000344207",
            private String agency;//": "",
            private String handlefee;//": "41",
            private String illegalid;//": 23360009,
            private String province;//": "陕西",
            private String city;//": "延安",
            private String town;//": "宜川县",
            private String lat;//": "0.0000000000",
            private String lng;//": "0.0000000000",
            private String canhandle;//": 1
}
