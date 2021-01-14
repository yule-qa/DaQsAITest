package com.dongao.DaQsAiTest;

import com.dongao.DaQsAiTest.Model.ApiTestCaseModel;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

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
    void run() {
        apiTestCase.run(baseApi);
    }
}