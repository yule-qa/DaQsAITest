package com.dongao.DaQsAiTest.Model;

import com.dongao.DaQsAiTest.Util.HeadersUtil;
import io.restassured.response.Response;

import java.util.HashMap;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;

/**
 * @Author: yule
 * @Description: 方法模型 ：
 * yaml文件action 节点下内容，解析成ApiObjectActionModel对象
 * 对象属性为yaml-actions下节点
 * 代表了一个单一的http接口
 * @Date: create in 2021/1/11 1:45 下午
 */
public class ApiObjectActionModel {

    public String save;
    public HashMap<String, Object> json;
    public String post;
    public String get;

    public Response run(HashMap query)
    {
        String url="";
        String method="get";
        if (post != null) {
             url=post;
             method="post";
        }
        if (get != null) {
            url=get;
            method="get";
        }
        //读取配置文件，获得域名与ip对应关系，在此替换 ,这里解决多环境问题
//        url=url.replaceAll("domain","ip");
        HeadersUtil.preforHeaders(query);
        return given().log().all().queryParams(query).request(method,url)  //发送请求
                .then().log().all() // 打印日志
                .statusCode(200)
                .extract().response(); //截取响应;
    }
}
