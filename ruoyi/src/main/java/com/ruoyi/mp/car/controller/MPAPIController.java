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
@RequestMapping("/mp/illega")
public class MPAPIController {

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

    /**
     * 付款失败 混滚数据
     * @param carInfo
     * @return
     */
    @PostMapping("/unProcess")
    public CommonResponse unProcess(@RequestBody CarInfo carInfo) {
        return illegaService.unProcessIllegas(carInfo.getIllegaNumbers());
    }

}
