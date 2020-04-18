package com.bst.car.violation.controller;

import com.bst.car.violation.entity.CarInfo;
import com.bst.car.violation.service.IllegaService;
import com.bst.car.violation.vo.CommonResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/carIllega")
public class IllegaController {

    @Autowired
    private IllegaService illegaService;

    @PostMapping("/query")
    public CommonResponse hello(@RequestBody CarInfo carInfo) {
        return illegaService.queryCarIllega(carInfo);
    }
}
