package com.ruoyi.mp.car.domain;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class IllegaResult implements Serializable {
		private String lsprefix;//": "陕",
        private String lsnum;//": "A1X36Z",
        private String carorg;//": "shannxi",
        private String usercarid;//": 49505307,
        private String count;//": 1,
        private String totalprice;//": 50,
        private String totalscore;//": 0,
        private List<IllegaDetail> list;//": 0,
}
