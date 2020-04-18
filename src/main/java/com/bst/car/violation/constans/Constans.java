package com.bst.car.violation.constans;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Constans {

    public static Map<Integer,String> codesMap = new HashMap<>();
    public static Set<Integer> daijiaoCodes  = new HashSet<>();

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



        //支持代缴的code 第二位是扣得分
        daijiaoCodes.add(7020);
        daijiaoCodes.add(1039);
        daijiaoCodes.add(1091);
        daijiaoCodes.add(1043);
        daijiaoCodes.add(1019);
        daijiaoCodes.add(1044);
        daijiaoCodes.add(1087);
        daijiaoCodes.add(1090);
        daijiaoCodes.add(6023);
        daijiaoCodes.add(1018);
        daijiaoCodes.add(10399);//
        daijiaoCodes.add(10789);
    }
}
