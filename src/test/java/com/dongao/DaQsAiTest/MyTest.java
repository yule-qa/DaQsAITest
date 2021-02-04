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
        query.put("userExtendId","2095");
        query.put("questionId","1769");
        query.put("plainContent","你好你好你好");
        query.put("seasonId","15");
        query.put("chapterId","0");
        query.put("examId","1");
        query.put("source","0");
        query.put("userId","1889");
        query.put("subjectId","1");
        HeadersUtil.preforHeaders(query);
//        方法一：
//        String response = given().contentType("application/json").log().all().queryParams(query).request("get", "http://qs.api.test.com/studyApi/study/V3/index")
//                .then().log().all()
//                .extract().asString();
//        HashMap obj=from(response).get("obj");

//        方法二：
        Response response = given().log().all().formParams(query)
                .contentType("application/x-www-form-urlencoded")
                .request("post", "http://qs.api.test.com/solveApi/solve/V1/saveAskQuestion")
                .then().log().all()
                .extract().response();

        HashMap obj=response.path("obj");
        System.out.println(((HashMap)response.path("obj")).size());


    }
    @Test
    public void test3(){
     String a="/classApi/class/V1/classDetai/classReport";
     String[] b=a.split("V1");
     int c=b[1].indexOf("/",1);
     if(c != -1){
         b[1]=b[1].replaceAll("/","_");
         System.out.println(b[1]);
     }

    }


}
