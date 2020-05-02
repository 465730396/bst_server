package com.ruoyi.mp.car.service.impl;

import com.alibaba.fastjson.JSON;
import com.ruoyi.mp.car.constans.Constans;
import com.ruoyi.mp.car.domain.CarInfo;
import com.ruoyi.mp.car.domain.IllegaDetail;
import com.ruoyi.mp.car.domain.IllegaResponse;
import com.ruoyi.mp.car.domain.IllegaResult;
import com.ruoyi.mp.car.mapper.IllegalMapper;
import com.ruoyi.mp.car.service.IllegaService;
import com.ruoyi.mp.car.util.IllegalUtil;
import com.ruoyi.mp.car.vo.CommonResponse;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.util.Strings;
import org.apache.poi.ss.usermodel.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Slf4j
@Transactional(readOnly = false)
class IllegaServiceImpl implements IllegaService {

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Autowired
    private IllegalMapper illegalMapper;

    @Override
    public CommonResponse queryCarIllega(CarInfo carInfo) {
        CommonResponse response = null;
        CarInfo car = illegalMapper.getCarInfo(carInfo);
        if(car == null){
//            String str = redisTemplate.opsForValue().get(carInfo.getLsprefix()+carInfo.getLsnum());
//            if(Strings.isNotEmpty(str)){
//                return JSON.parseObject(str,CommonResponse.class);
//            }

            response = queryCarIllegaByHttp(carInfo);
        }else{
            log.error(car.getLastQueryTime().toString());
            log.error(JSON.toJSONString(car.getLastQueryTime()));
            log.error(Long.toString(car.getLastQueryTime().getTime()));
            log.error(Long.toString(System.currentTimeMillis()));
            long hourRange = (System.currentTimeMillis() - car.getLastQueryTime().getTime()) / (1000L * 60 * 60);
            log.error(Long.toString(hourRange));
            if(hourRange > 6){
                //超过6个小时 就查询http 否则查询DB
                response = queryCarIllegaByHttp(car);
            }else{
                response = queryCarIllegaByDB(carInfo);
            }
        }

        return response;
    }


    @Override
    public CommonResponse queryCarIllegaByHttp(CarInfo carInfo) {
        CommonResponse response = null;
        IllegaResponse illegaResponse = IllegalUtil.getIllegaData(carInfo.getLsprefix(),carInfo.getLsnum(),carInfo.getEngineno(),carInfo.getFrameno());

        if(illegaResponse.getStatus() == 0){
            //成功请求
            response = CommonResponse.createBySuccess(illegaResponse.getMsg(),illegaResponse.getResult());
            log.error(JSON.toJSONString(response));
            illegalMapper.insertCarInfo(carInfo);
            illegalMapper.insertIllegaDetailList(illegaResponse.getResult().getList(),carInfo.getLsprefix(),carInfo.getLsnum());
            //redisTemplate.opsForHash().put(carInfo.getLsprefix()+carInfo.getLsnum(),JSON.toJSONString(response),1000*60*30);
        }else{
            response = CommonResponse.createByErrorCodeMessage(illegaResponse.getStatus(), Constans.codesMap.get(illegaResponse.getStatus()));
        }
        return response;
    }

    @Override
    public CommonResponse queryCarIllegaByDB(CarInfo car) {
        List<IllegaDetail> illegaDetails = illegalMapper.selectIllegaDetail(car.getLsprefix(),car.getLsnum());
        IllegaResult result = new IllegaResult();
        result.setList(illegaDetails);
        result.setLsprefix(car.getLsprefix());
        result.setLsnum(car.getLsnum());
        illegalMapper.addCarQuery(car);
        return CommonResponse.createBySuccess(null,result);
    }
}
