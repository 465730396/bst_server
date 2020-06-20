package com.ruoyi.mp.car.service;

import com.ruoyi.mp.car.domain.CarInfo;
import com.ruoyi.mp.car.domain.IllegaDetail;
import com.ruoyi.mp.car.vo.CommonResponse;

import java.util.List;
import java.util.Map;

public interface  IllegaService {
    public CommonResponse queryCarIllega(CarInfo carInfo);
    public CommonResponse queryCarIllegaByHttp(CarInfo carInfo);
    public CommonResponse queryCarIllegaByDB(CarInfo carInfo);
    public CommonResponse processCarIllega(String[] illegaNumbers);
    public CommonResponse unProcessIllegas(String[] illegaNumbers);
    public CommonResponse completeCarIllega(String[] illegaNumbers);
    public CommonResponse backMonyCarIllega(String[] illegaNumbers);
    public List<IllegaDetail> queryIllegaDetailOrder(Map param);
    public List<CarInfo> queryIllegaCarList(Map param);
}
