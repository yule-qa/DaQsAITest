package com.dongao.DaQsAiTest;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class ApiTestCaseModelTest {
    private static BaseApi baseApi;
    private static ApiTestCaseModel apiTestCase;

    @BeforeAll
    static void beforeAll() throws IOException {
        baseApi=new BaseApi();
        baseApi.load("src/main/resources/com.dongao.DaQsAiTest/api");
        apiTestCase=ApiTestCaseModel.load("src/main/resources/com.dongao.DaQsAiTest/case/test_index.yaml");
    }

    @Test
    void load()  {
        assertThat(apiTestCase.name,equalTo("qs_study_index"));

    }


    @Test
    void run() {
        apiTestCase.run(baseApi);
    }
}