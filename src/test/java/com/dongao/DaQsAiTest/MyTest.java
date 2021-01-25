package com.dongao.DaQsAiTest;


import com.dongao.DaQsAiTest.Model.HeadersModel;
import com.dongao.DaQsAiTest.Util.HeadersUtil;
import io.restassured.RestAssured;
import io.restassured.builder.ResponseBuilder;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import java.io.UnsupportedEncodingException;
import java.util.Base64;
import java.util.HashMap;

import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static io.restassured.path.json.JsonPath.from;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @Author: yule
 * @Description:
 * @Date: create in 2021/1/14 1:40 下午
 */

public class MyTest {

    private static final Logger logger = LoggerFactory.getLogger(MyTest.class);

    private static HashMap query=new HashMap<>();

    @BeforeAll
    static void beforeAll(){
        RestAssured.filters((req, res, ctx)->{
            Response originalResponse=ctx.next(req,res);
            ResponseBuilder responseBuilder=new ResponseBuilder().clone(originalResponse);
            responseBuilder.setContentType("application/json; charset=UTF-8");
            return responseBuilder.build();
        });

    }
    @Test
    void test1(){
        query.put("userExtendId","2079");
        query.put("userId","1879");
        HeadersUtil.preforHeaders(query);
//        方法一：
//        String response = given().contentType("application/json").log().all().queryParams(query).request("get", "http://qs.api.test.com/studyApi/study/V3/index")
//                .then().log().all()
//                .extract().asString();
//        HashMap obj=from(response).get("obj");

//        方法二：
        Response response = given().log().all().formParams(query).request("get", "http://qs.api.test.com/studyApi/study/V3/index")
                .then().log().all()
                .extract().response();

        HashMap obj=response.path("obj");
        System.out.println(((HashMap)response.path("obj")).size());



    }
    @Test
    public void test2() {
        query.put("tdata","%7B%22userExtendId%22%3A%222095%22%2C%22listenList%22%3A%5B%7B%22listenTime%22%3A%224%22%2C%22planId%22%3A%226671%22%2C%22roomId%22%3A%220%22%2C%22currentPlayTime%22%3A%2200%3A00%3A05%22%2C%22planType%22%3A%2243%22%2C%22listenDate%22%3A%222021-01-25%2015%3A32%3A12%22%2C%22lectureId%22%3A%221059%22%2C%22planUserExtendId%22%3A%222095%22%7D%5D%7D");
        query.put("userExtendId","2095");
        query.put("userId","1889");
        HeadersUtil.preforHeaders(query);
//        方法一：
//        String response = given().contentType("application/json").log().all().queryParams(query).request("get", "http://qs.api.test.com/studyApi/study/V3/index")
//                .then().log().all()
//                .extract().asString();
//        HashMap obj=from(response).get("obj");

//        方法二：
        String a="tdata=%7B%22userExtendId%22%3A%222095%22%2C%22listenList%22%3A%5B%7B%22listenTime%22%3A%224%22%2C%22planId%22%3A%226671%22%2C%22roomId%22%3A%220%22%2C%22currentPlayTime%22%3A%2200%3A00%3A05%22%2C%22planType%22%3A%2243%22%2C%22listenDate%22%3A%222021-01-25%2015%3A32%3A12%22%2C%22lectureId%22%3A%221059%22%2C%22planUserExtendId%22%3A%222095%22%7D%5D%7D&userExtendId=2095&userId=1889";
        Response response = given().log().all().formParams(query)
                .contentType("application/x-www-form-urlencoded")
                .request("post", "http://qs.api.test.com/courseApi/course/V1/syncCourseRecord")
                .then().log().all()
                .extract().response();

        HashMap obj=response.path("obj");
        System.out.println(((HashMap)response.path("obj")).size());


    }


}
