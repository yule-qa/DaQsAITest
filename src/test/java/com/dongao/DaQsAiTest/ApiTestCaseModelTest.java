package com.dongao.DaQsAiTest;

import com.dongao.DaQsAiTest.Model.ApiTestCaseModel;
import com.dongao.DaQsAiTest.Util.HeadersUtil;
import io.restassured.RestAssured;
import io.restassured.builder.ResponseBuilder;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.HashMap;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

class ApiTestCaseModelTest {
    private static BaseApi baseApi;
    private static ApiTestCaseModel apiTestCase;

    @BeforeAll
    static void beforeAll() throws IOException {
        // 发送请求前准备请求参数 计算sign
        RestAssured.filters((req, res, ctx)->{
            HashMap headers= HeadersUtil.preforHeaders(req);
            req.headers(headers);
            return ctx.next(req, res);
        });
        //发送请求后，对响应消息进行格式化
        RestAssured.filters((req, res, ctx)->{
            Response originalResponse=ctx.next(req,res);
            ResponseBuilder responseBuilder=new ResponseBuilder().clone(originalResponse);
            responseBuilder.setContentType("application/json;charset=utf-8");
            return responseBuilder.build();
        });
        baseApi=new BaseApi();
        baseApi.load("src/main/resources/com.dongao.DaQsAiTest/api/V1/study");
        apiTestCase=ApiTestCaseModel.load("src/main/resources/com.dongao.DaQsAiTest/case/V1/study/qs_V1_study_getTodayLecture.yaml");
    }

    @Test
    void run() {
        apiTestCase.run(baseApi);
    }
}