package com.dongao.DaQsAiTest.Util;

import com.dongao.DaQsAiTest.Model.HeadersModel;
import io.restassured.RestAssured;

import java.io.IOException;
import java.util.HashMap;

/**
 * @Author: yule
 * @Description:
 * @Date: create in 2021/1/14 4:42 下午
 */
public class HeadersUtil {
    private static HeadersModel headersModel;
    public static void preforHeaders(HashMap apiquery){
        RestAssured.filters((req, res, ctx)->{
            try {
                headersModel= HeadersModel.load("src/main/resources/com.dongao.DaQsAiTest/common/headers.yaml");
            } catch (IOException e) {
                e.printStackTrace();
            }
            HashMap headers=headersModel.getHeaders();
            HashMap finalmap=new HashMap();

            headers.put("timeStamp",FakerUtils.getTimeStamp());
            finalmap.putAll(apiquery);
            finalmap.putAll(headers);
            headers.put("sign", GetSign.sign(finalmap,"9538b01d8d3e4c1ab3ba450adb3bea6a"));

            req.headers(headers);
            return ctx.next(req, res);
        });
    }
}
