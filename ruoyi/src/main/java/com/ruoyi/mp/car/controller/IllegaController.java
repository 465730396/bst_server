package com.ruoyi.mp.car.controller;

import com.ruoyi.mp.car.entity.CarInfo;
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
    public CommonResponse hello(@RequestBody CarInfo carInfo) {
        return illegaService.queryCarIllega(carInfo);
    }
}
