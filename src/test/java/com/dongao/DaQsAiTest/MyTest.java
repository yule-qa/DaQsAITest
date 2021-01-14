package com.dongao.DaQsAiTest;


import com.dongao.DaQsAiTest.Model.HeadersModel;
import com.dongao.DaQsAiTest.Util.HeadersUtil;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import java.util.HashMap;

import static io.restassured.RestAssured.given;

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


    }
    @Test
    void test1(){
        query.put("userExtendId","2079");
        query.put("userId","1879");

        Response response=given().log().all().queryParams(query).request("get","http://qs.api.test.com/studyApi/study/V3/index")
                .then().log().all()
                .extract().response();
        logger.info(response.toString());
    }


}
