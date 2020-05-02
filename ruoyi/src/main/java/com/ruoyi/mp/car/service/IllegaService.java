package com.ruoyi.mp.car.service;

import com.ruoyi.mp.car.domain.CarInfo;
import com.ruoyi.mp.car.vo.CommonResponse;

public interface  IllegaService {
    public CommonResponse queryCarIllega(CarInfo carInfo);
    public CommonResponse queryCarIllegaByHttp(CarInfo carInfo);
    public CommonResponse queryCarIllegaByDB(CarInfo carInfo);
    public CommonResponse processCarIllega(String[] illegaNumbers);
}
