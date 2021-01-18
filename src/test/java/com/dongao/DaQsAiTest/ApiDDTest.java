package com.dongao.DaQsAiTest;

import com.dongao.DaQsAiTest.Model.ApiTestCaseModel;
import io.restassured.RestAssured;
import io.restassured.builder.ResponseBuilder;
import io.restassured.http.Headers;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;


import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.params.provider.Arguments.arguments;

/**
 * @Author: yule
 * @Description:
 * @Date: create in 2021/1/11 5:23 下午
 */
public class ApiDDTest {
    private static BaseApi baseApi;
    @BeforeAll
    void com_filter(){
        //todo
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

        //api 为api目录地址，将api作为参数传递，通过java执行命令执行jar包时，通过命令加参形式传入
        // java执行命令：java -jar -Dapi=src/main/resources/com.dongao.DaQsAiTest/api target/DaQsAITest-1.0-SNAPSHOT-jar-with-dependencies.jar

        if(System.getProperty("api")!=null){
            baseApi.load(System.getProperty("api"));
        }else{
//            写日志



        }

        //用来传递给参数化用例
        List<Arguments> testcases=new ArrayList<>();

        //读取所有的测试用例
        //="src/main/resources/com.dongao.DaQsAiTest/case"
        String testCaseDir=null;
        if(System.getProperty("case")!=null){
            //通过环境变量获取case的路径 ，这个是在外部执行java -jar -Dcase=case路径添加的
            testCaseDir=System.getProperty("case");
        }
        String finalTestCaseDir = testCaseDir;
        Arrays.stream(new File(testCaseDir).list())
                .forEach(name ->{
                    String path= finalTestCaseDir +"/"+ name;
                    try {
                        ApiTestCaseModel apiTestCase=ApiTestCaseModel.load(path);
                        testcases.add(arguments(apiTestCase,apiTestCase.name));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
        });
        return testcases;
    }
}
