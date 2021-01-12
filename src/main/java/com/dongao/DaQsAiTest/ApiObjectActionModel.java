package com.dongao.DaQsAiTest;

import io.restassured.response.Response;

import java.util.HashMap;

import static io.restassured.RestAssured.given;

/**
 * @Author: yule
 * @Description: 方法模型 ：
 * yaml文件action 节点下内容，解析成ApiObjectActionModel对象
 * 对象属性为yaml-actions下节点
 * 代表了一个单一的http接口
 * @Date: create in 2021/1/11 1:45 下午
 */
public class ApiObjectActionModel {

    public HashMap<String, Object> query;
    public String save;
    public HashMap<String, Object> json;
    public String post;
    public String get;

    public Response run() {
        if (post != null) {
            return given().log().all().queryParams(query).post(post) //发送请求
                    .then().log().all() // 打印日志
                    .extract().response(); //截取响应
        }
        if (get != null) {
            return given().log().all().queryParams(query).get(get)  //发送请求
                    .then().log().all() // 打印日志
                    .extract().response(); //截取响应
        }
        return null;
    }
}
