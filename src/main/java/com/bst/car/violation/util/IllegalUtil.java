package com.bst.car.violation.util;

import com.alibaba.fastjson.JSON;
import com.bst.car.violation.entity.IllegaResponse;
import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * https://market.aliyun.com/products/57002002/cmapi011084.html?spm=5176.10695662.1996646101.searchclickresult.4926138fPYaghA#sku=yuncode508400005
 */
public class IllegalUtil {
//    private static String host = "https://jisuqgclwz.market.alicloudapi.com";
//    private static String path = "/illegal/query";
//    private static String method = "GET";
//    private static String appcode = "2d5ed842d99c492f94a3b663755c3556";

    private static Map<String, String> aipFree = new HashMap<String, String>() {
        {
            put("host", "https://mys4s.cn");
            put("path", "/v3/violation/web/query");
            put("method", "POST");
        }
    };
    private static Map<String, String> aipPay = new HashMap<String, String>() {
        {
            put("host", "https://jisuqgclwz.market.alicloudapi.com");
            put("path", "/illegal/query");
            put("method", "GET");
            put("appcode", "2d5ed842d99c492f94a3b663755c3556");
        }
    };

    public static IllegaResponse getIllegaData(String lsprefix, String lsnum, String engineno, String frameno){
        try{
            HttpResponse response = getIllegaDataByFree(lsprefix,lsnum,engineno,frameno);
            if (response == null){
                response = getIllegaDataByPay(lsprefix,lsnum,engineno,frameno);
            }
            return JSON.parseObject(EntityUtils.toString(response.getEntity()),IllegaResponse.class);
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public static HttpResponse getIllegaDataByPay(String lsprefix, String lsnum, String engineno, String frameno){
        try {
            Map<String, String> headers = new HashMap<String, String>();
            //最后在header中的格式(中间是英文空格)为Authorization:APPCODE 83359fd73fe94948385f570e3c139105
            headers.put("Authorization", "APPCODE " + aipPay.get("appcode"));
            Map<String, String> querys = new HashMap<String, String>();
            querys.put("engineno", engineno);//必选	发动机号 根据管局需要输入（100为全部输入，0为不输入 123456
            querys.put("frameno", frameno);//必选	车架号 根据管局需要输入（100为全部输入，0为不输入） 229561
            querys.put("lsnum", lsnum);//必选	车牌剩余部分 AH5b57
            querys.put("lsprefix", lsprefix);//必选	车牌前缀 浙
            return HttpUtils.doGet(aipPay.get("host"), aipPay.get("path"), aipPay.get("method"), headers, querys);
            //获取response的body
            //return JSON.parseObject(EntityUtils.toString(response.getEntity()),IllegaResponse.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static HttpResponse getIllegaDataByFree(String lsprefix, String lsnum, String engineno, String frameno){
        try {
            Map<String, String> headers = new HashMap();
//            headers.put("Content-type", "application/json");

            Map<String, String> querys = new HashMap();

            Map<String, String> bodys = new HashMap();
            bodys.put("t", System.currentTimeMillis()+"");
            bodys.put("channel", "baidu_pc");
            bodys.put("access_token","");
            bodys.put("car_no",lsprefix.concat(lsnum));
            bodys.put("vin",frameno);
            bodys.put("engine",engineno);
            bodys.put("car_type","02");
            bodys.put("md5","178b2f03e993caf4d6865f4081e23ayb");
            System.out.println(JSON.toJSON(bodys));
            return HttpUtils.doPost(aipFree.get("host"), aipFree.get("path"), aipFree.get("method"), headers, querys, bodys);
            //获取response的body
            //return JSON.parseObject(EntityUtils.toString(response.getEntity()),IllegaResponse.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args){
//        IllegalUtil.getIllegaData("陕","K188C7","230255","");
//        System.out.println(IllegalUtil.getIllegaData("陕","A1X36Z","638199",""));
        System.out.println(IllegalUtil.getIllegaData("陕","A7MT81","129767","000000"));
    }
    //{"status":0,"msg":"恭喜您，没有违章！","result":{"lsprefix":"陕","lsnum":"K188C7","carorg":"shannxi","usercarid":null,"count":0,"totalprice":0,"totalscore":0,"list":[]}}
    //{"status":"213","msg":"车牌不存在","result":""}
    //{"status":"208","msg":"发动机号有误","result":""}
    //{"status":0,"msg":"","result":{"lsprefix":"陕","lsnum":"A1X36Z","carorg":"shannxi","usercarid":49505307,"count":1,"totalprice":50,"totalscore":0,"list":[{"time":"2020-03-21 15:00:00","address":"壶口景区专线0000公里400米","content":"机动车乘坐人未使用安全带","legalnum":"30191","price":50,"score":0,"number":"6106300000344207","agency":"","handlefee":"41","illegalid":23360009,"province":"陕西","city":"延安","town":"宜川县","lat":"0.0000000000","lng":"0.0000000000","canhandle":1}]}}
}
