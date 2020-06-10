package com.ruoyi.mp.car.controller;

import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.mp.car.domain.CarInfo;
import com.ruoyi.mp.car.service.IllegaService;
import com.ruoyi.mp.car.vo.CommonResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/illega")
public class IllegaController {

    @Autowired
    private IllegaService illegaService;

    @PostMapping("/complete")
    @PreAuthorize("@ss.hasPermi('car:illega:index')")
    public CommonResponse complete(@RequestBody CarInfo carInfo) {
        return illegaService.completeCarIllega(carInfo.getIllegaNumbers());
    }

    @PostMapping("/backMony")
    @PreAuthorize("@ss.hasPermi('car:illega:index')")
    public CommonResponse backMony(@RequestBody CarInfo carInfo) {
        return illegaService.backMonyCarIllega(carInfo.getIllegaNumbers());
    }

    @PostMapping("/order")
    @PreAuthorize("@ss.hasPermi('car:illega:index')")
    public CommonResponse queryOrders(@RequestBody Map param) {
        param.put("biz",SecurityUtils.getLoginUser().getUser().getUserId());
        return illegaService.queryIllegaDetailOrder(param);
    }

    @PostMapping("/car")
    @PreAuthorize("@ss.hasPermi('car:illega:index')")
    public CommonResponse queryCars(@RequestBody Map param) {
        param.put("biz",SecurityUtils.getLoginUser().getUser().getUserId());
        return illegaService.queryIllegaCarList(param);
    }

}
