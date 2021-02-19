package com.dongao.DaQsAiTest;

import com.dongao.DaQsAiTest.Model.ApiTestCaseModel;
import com.dongao.DaQsAiTest.Model.HeadersModel;
import com.dongao.DaQsAiTest.Util.FakerUtils;
import com.dongao.DaQsAiTest.Util.FileUtils;
import com.dongao.DaQsAiTest.Util.GetSign;
import com.dongao.DaQsAiTest.Util.HeadersUtil;
import io.restassured.RestAssured;
import io.restassured.builder.ResponseBuilder;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;


import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

import static org.junit.jupiter.params.provider.Arguments.arguments;

/**
 * @Author: yule
 * @Description:
 * @Date: create in 2021/1/11 5:23 下午
 */
public class ApiDDTest {
    private static BaseApi baseApi;
    @BeforeAll
    static void beforeAll(){
        // 发送请求前准备请求参数 计算sign
        RestAssured.filters((req, res, ctx)->{
            HashMap headers=HeadersUtil.preforHeaders(req);
            req.headers(headers);
            return ctx.next(req, res);
        });
        //发送请求后，对响应消息进行格式化
        RestAssured.filters((req, res, ctx)->{
            Response originalResponse=ctx.next(req,res);
            ResponseBuilder responseBuilder=new ResponseBuilder().clone(originalResponse);
            responseBuilder.setContentType("application/json; charset=UTF-8");
            return responseBuilder.build();
        });

    }


    @ParameterizedTest(name = "{index} {1}") //标明这个标明是参数化的测试用例  {index} {1} index标志执行测试用例的索引，1标志下方的参数，name
    @MethodSource //同@Test,是一个测试用例，与上面@ParameterizedTest同时使用，如果没加参数（方法名），则默认找测试用例名称相同的方法名，并引用，作为数据的提供来源
    void apiTest(ApiTestCaseModel apiTestCaseModel, String name){
        apiTestCaseModel.run(baseApi);
        //加载测试用例


    }
    //测试用例数据来源
    static List<Arguments> apiTest(){
        //加载所有的api object
        baseApi =new BaseApi();
        //用来返回传递给参数化用例
        List<Arguments> testcases = new ArrayList<>();

        //api 为api目录地址，将api作为参数传递，通过java执行命令执行jar包时，通过命令加参形式传入
        // java执行命令：java -jar -Dapi=src/main/resources/com.dongao.DaQsAiTest/api target/DaQsAITest-1.0-SNAPSHOT-jar-with-dependencies.jar

//        if(System.getProperty("api")!=null){
//            baseApi.load(System.getProperty("api")); //返回api对象
//        }
        List apiDirList= FileUtils.findDir("src/main/resources/com.dongao.DaQsAiTest/api");
        for (Object businessPath:apiDirList) {
            String apipath = "src/main/resources/com.dongao.DaQsAiTest/api/" + businessPath.toString();
            baseApi.load(apipath); //返回api对象

            //读取所有的测试用例
            //="src/main/resources/com.dongao.DaQsAiTest/case"
            //        if(System.getProperty("case")!=null){
            //            //通过环境变量获取case的路径 ，这个是在外部执行java -jar -Dcase=case路径添加的
            //            testCaseDir=System.getProperty("case");
            //        }
            String testCaseDir = "src/main/resources/com.dongao.DaQsAiTest/case/"+ businessPath.toString();
            String finalTestCaseDir = testCaseDir;
            //todo  这里需要改造，现在case目录下增加了版本号和业务线文件夹
            Arrays.stream(new File(testCaseDir).list())
                    .forEach(name -> {
                        String path = finalTestCaseDir + "/" + name;
                        try {
                            ApiTestCaseModel apiTestCase = ApiTestCaseModel.load(path);
                            Arguments testcase = arguments(apiTestCase, finalTestCaseDir.substring(finalTestCaseDir.indexOf("V"))+"/"+apiTestCase.name); //这个是为了返回对象变成arguments类型，并且不能为空
                            testcases.add(testcase);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    });
        }
            return testcases;

    }
}
