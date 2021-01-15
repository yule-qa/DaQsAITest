package com.dongao.DaQsAiTest.Model;

import com.dongao.DaQsAiTest.BaseApi;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import io.restassured.builder.ResponseBuilder;
import io.restassured.response.Response;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

/**
 * @Author: yule
 * @Description: 测试用例模型
 * 代表测试用例，读取测试用例yaml文件，并执行
 * @Date: create in 2021/1/11 3:53 下午
 */
public class ApiTestCaseModel {
    public String name="";
    public String description="";
    public List<HashMap<String,Object>> steps;

    /**
     * 加载一个yaml文件，并转成测试用例的模型类
     * @param path
     * @return
     * @throws IOException
     */
    public static ApiTestCaseModel load(String path) throws IOException {
        ObjectMapper objectMapper=new ObjectMapper(new YAMLFactory());
        ApiTestCaseModel apiTestCaseModel=objectMapper.readValue(new File(path),ApiTestCaseModel.class);
        return apiTestCaseModel;
    }

    /**
     * 借助于baseApi,去运行对应的测试用例，主要是运行其中的测试步骤steps.将来可能会有断言
     * @param baseApi
     */
    public void run(BaseApi baseApi){ steps.stream().forEach(step ->{
            Response response=baseApi.run(step.get("api").toString(),step.get("action").toString(), (HashMap) step.get("params"));
            if (step.get("assertparams")!=null){
                assertAll(()->{
                    HashMap pararmmap=(HashMap)step.get("assertparams");
                    if(pararmmap.get("matcher").equals("equalTo")) {
                        String assertparam= (String) pararmmap.get("assertparam");
                        ResponseBuilder responseBuilder=new ResponseBuilder().clone(response);

                        System.out.println( responseBuilder.build());
//                        assertThat(,equalTo(step.get("expect")));
                    }
                });
            }
        });
    }
}
