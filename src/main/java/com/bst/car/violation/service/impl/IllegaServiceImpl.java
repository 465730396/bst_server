package com.bst.car.violation.service.impl;

import com.alibaba.fastjson.JSON;
import com.bst.car.violation.constans.Constans;
import com.bst.car.violation.entity.CarInfo;
import com.bst.car.violation.entity.IllegaResponse;
import com.bst.car.violation.service.IllegaService;
import com.bst.car.violation.util.IllegalUtil;
import com.bst.car.violation.vo.CommonResponse;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
@Transactional(readOnly = false)
class IllegaServiceImpl implements IllegaService {

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Override
    public CommonResponse queryCarIllega(CarInfo carInfo) {

        String str = redisTemplate.opsForValue().get(carInfo.getLsprefix()+carInfo.getLsnum());
        if(Strings.isNotEmpty(str)){
            return JSON.parseObject(str,CommonResponse.class);
        }

        IllegaResponse illegaResponse = IllegalUtil.getIllegaData(carInfo.getLsprefix(),carInfo.getLsnum(),carInfo.getEngineno(),carInfo.getFrameno());
        CommonResponse response = null;
        if(illegaResponse.getStatus() == 0){
            //成功请求
            response = CommonResponse.createBySuccess(illegaResponse.getMsg(),illegaResponse.getResult());
            log.error(JSON.toJSONString(response));
//            redisTemplate.opsForHash().put(carInfo.getLsprefix()+carInfo.getLsnum(),JSON.toJSONString(response),1000*60*30);
            return response;
        }else{
            response = CommonResponse.createByErrorCodeMessage(illegaResponse.getStatus(), Constans.codesMap.get(illegaResponse.getStatus()));
        }

        return response;
    }
}
