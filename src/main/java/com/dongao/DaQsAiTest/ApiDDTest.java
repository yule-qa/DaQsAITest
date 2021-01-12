package com.dongao.DaQsAiTest;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.runners.Parameterized;

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
    @ParameterizedTest(name = "{index} {1}") //标明这个标明是参数化的测试用例  {index} {1} index标志执行测试用例的索引，1标志下方的参数，name
    @MethodSource //同@Test,是一个测试用例，与上面@ParameterizedTest同时使用，如果没加参数（方法名），则默认找测试用例名称相同的方法名，并引用，作为数据的提供来源
    void apiTest(ApiTestCaseModel apiTestCaseModel,String name){
        apiTestCaseModel.run(baseApi);
        //加载测试用例


    }
    //测试用例数据来源
    static List<Arguments> apiTest(){
        //加载所有的api object
        baseApi =new BaseApi();
        baseApi.load("src/main/resources/com.dongao.DaQsAiTest/api");

        //用来传递给参数化用例
        List<Arguments> testcases=new ArrayList<>();

        //读取所有的测试用例
        String testCaseDir="src/main/resources/com.dongao.DaQsAiTest/case";
        Arrays.stream(new File(testCaseDir).list())
                .forEach(name ->{
                    String path=testCaseDir +"/"+ name;
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
