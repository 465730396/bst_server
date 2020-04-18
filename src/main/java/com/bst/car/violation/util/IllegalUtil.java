package com.bst.car.violation.util;

import org.apache.http.HttpResponse;
import org.apache.logging.log4j.util.Strings;

import java.util.HashMap;
import java.util.Map;

/**
 * https://market.aliyun.com/products/57002002/cmapi011084.html?spm=5176.10695662.1996646101.searchclickresult.4926138fPYaghA#sku=yuncode508400005
 */
public class IllegalUtil {
    private static String host = "https://jisuqgclwz.market.alicloudapi.com";
    private static String path = "/illegal/query";
    private static String method = "GET";
    private static String appcode = "2d5ed842d99c492f94a3b663755c3556";

    public static String getIllegaData(String lsprefix,String lsnum,String engineno,String frameno){
        String result = Strings.EMPTY;
        Map<String, String> headers = new HashMap<String, String>();
        //最后在header中的格式(中间是英文空格)为Authorization:APPCODE 83359fd73fe94948385f570e3c139105
        headers.put("Authorization", "APPCODE " + appcode);
        Map<String, String> querys = new HashMap<String, String>();
        querys.put("engineno", engineno);//必选	发动机号 根据管局需要输入（100为全部输入，0为不输入 123456
        querys.put("frameno", frameno);//必选	车架号 根据管局需要输入（100为全部输入，0为不输入） 229561
        querys.put("lsnum", lsnum);//必选	车牌剩余部分 AH5b57
        querys.put("lsprefix", lsprefix);//必选	车牌前缀 浙

        try {
            HttpResponse response = HttpUtils.doGet(host, path, method, headers, querys);
            result = response.toString();
            System.out.println(response.toString());
            //获取response的body
            //System.out.println(EntityUtils.toString(response.getEntity()));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public static void main(String[] args){
        IllegalUtil.getIllegaData("陕","K188C7","230255","199701");
    }
}
