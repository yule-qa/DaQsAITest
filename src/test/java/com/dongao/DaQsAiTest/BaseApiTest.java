package com.dongao.DaQsAiTest;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;

class BaseApiTest {
    private static BaseApi baseApi;

    @BeforeAll
    static void beforeAll(){
        baseApi=new BaseApi();
        baseApi.load("src/main/resources/com.dongao.DaQsAiTest");
    }

    @Test
    void load() {
        assertThat(baseApi.apis.size(),greaterThan(1));  //比1大，证明里面apis里面已经有内容了
    }

    @Test
    void run() {
        baseApi.run("qs_study_index","index");
        baseApi.run("qs_study","systemStatus");
    }
}