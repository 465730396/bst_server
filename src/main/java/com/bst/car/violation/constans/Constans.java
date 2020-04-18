package com.bst.car.violation.constans;

import java.util.HashMap;
import java.util.Map;

public class Constans {

    public static Map<Integer,String> codesMap = new HashMap<>();
    static {
        codesMap.put(201,"交管局为空");
        codesMap.put(202,"交管局不存在");
        codesMap.put(203,"车牌前缀为空");
        codesMap.put(204,"车牌前缀有误");
        codesMap.put(205,"车牌号为空");
        codesMap.put(206,"车牌号有误");
        codesMap.put(207,"发动机号为空");
        codesMap.put(208,"发动机号有误");
        codesMap.put(209,"车架号为空");
        codesMap.put(210,"车架号有误");
        codesMap.put(211,"登记证书号为空");
        codesMap.put(212,"登记证书号有误");
        codesMap.put(216,"其他为空");
        codesMap.put(220,"交管局服务器错误");
        codesMap.put(230,"没有信息");
        codesMap.put(217,"有违章，详情请到交管网查询");
    }
}
