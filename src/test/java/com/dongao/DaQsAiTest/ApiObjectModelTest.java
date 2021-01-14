package com.dongao.DaQsAiTest;

import com.dongao.DaQsAiTest.Model.ApiObjectModel;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

import java.io.IOException;

class ApiObjectModelTest {
    static ApiObjectModel api;
    @BeforeAll
    static void beforeAll() throws IOException {
        api=ApiObjectModel.load("src/main/resources/com.dongao.DaQsAiTest/api/acess_api.yaml");

    }
    @Test
    void load() {
        assertThat(api.name,equalTo("qs_study"));

    }

//    @Test
//    void run() {
//        Response respnse=api.actions.get("systemStatus").run(api);
//        respnse.then().statusCode(200);
//    }
}