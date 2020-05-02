package com.ruoyi.mp.car.controller;

import com.ruoyi.mp.car.domain.CarInfo;
import com.ruoyi.mp.car.service.IllegaService;
import com.ruoyi.mp.car.vo.CommonResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/mp/illega")
public class IllegaController {

    @Autowired
    private IllegaService illegaService;

    @PostMapping("/query")
    public CommonResponse query(@RequestBody CarInfo carInfo) {
        return illegaService.queryCarIllega(carInfo);
    }
    @PostMapping("/process")
    public CommonResponse process(@RequestBody CarInfo carInfo) {
        return illegaService.processCarIllega(carInfo.getIllegaNumbers());
    }

    @PostMapping("/complete")
    public CommonResponse complete(@RequestBody CarInfo carInfo) {
        return illegaService.completeCarIllega(carInfo.getIllegaNumbers());
    }

    @PostMapping("/order")
    public CommonResponse queryOrders(@RequestBody CarInfo carInfo) {
        return illegaService.queryIllegaDetailOrder(carInfo);
    }
}
