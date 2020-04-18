package com.bst.car.violation.service;

import com.bst.car.violation.entity.CarInfo;
import com.bst.car.violation.vo.CommonResponse;

public interface  IllegaService {
    public CommonResponse queryCarIllega(CarInfo carInfo);
}
