package com.dongao.DaQsAiTest;

import com.dongao.DaQsAiTest.Model.ApiTestCaseModel;
import io.restassured.RestAssured;
import io.restassured.builder.ResponseBuilder;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

class ApiTestCaseModelTest {
    private static BaseApi baseApi;
    private static ApiTestCaseModel apiTestCase;

    @BeforeAll
    static void beforeAll() throws IOException {
        RestAssured.filters((req, res, ctx)->{
            Response originalResponse=ctx.next(req,res);
            ResponseBuilder responseBuilder=new ResponseBuilder().clone(originalResponse);
            responseBuilder.setContentType("application/json; charset=UTF-8");
            return responseBuilder.build();
        });

        baseApi=new BaseApi();
        baseApi.load("src/main/resources/com.dongao.DaQsAiTest/api/V1/course");
        apiTestCase=ApiTestCaseModel.load("src/main/resources/com.dongao.DaQsAiTest/case/V1/course/qs_course_syncCourseRecord.yaml");


    }

    @Test
    void run() {
        apiTestCase.run(baseApi);
    }
}