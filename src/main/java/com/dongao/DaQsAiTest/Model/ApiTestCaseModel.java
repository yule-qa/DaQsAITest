package com.dongao.DaQsAiTest.Model;

import com.dongao.DaQsAiTest.BaseApi;
import com.dongao.DaQsAiTest.Util.JsonToYamlUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import io.restassured.builder.ResponseBuilder;
import io.restassured.response.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    private static final Logger logger = LoggerFactory.getLogger(ApiTestCaseModel.class);

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
    public void run(BaseApi baseApi){
        steps.stream().forEach(step ->{
            Response response=baseApi.run(step.get("api").toString(),step.get("action").toString(), (HashMap) step.get("params"));

            if (step.get("assertparams")!=null){
                assertAll(()->{
                    HashMap pararmmap=(HashMap)step.get("assertparams");
                    if(pararmmap.get("matcher").equals("equalTo")) {
                        String assertparam = (String) pararmmap.get("assertparam");
                        Object arr = response.path(assertparam);
                        //断言msg
                        assertThat(response.path("msg"),equalTo("成功"));
                        logger.info("测试用例" + step.get("api") + "断言完毕！！\n 返回消息体msg-----" + response.path("msg"));
                        //断言code
                        assertThat(response.path("code"),equalTo(0));
                        logger.info("测试用例" + step.get("api") + "断言完毕！！\n 返回消息体code-----" + response.path("code"));
                        //断言obj
                        if (arr == null || arr.equals("")) {
                            logger.info("返回消息obj体中为空，无需断言");
                        }else if(arr instanceof Map){
                            assertThat(String.valueOf(((HashMap) arr).size()), equalTo(pararmmap.get("expect")));
                            logger.info("测试用例" + step.get("api") + "断言完毕！！\n 期待obj返回消息体" + pararmmap.get("expect") +
                                    "\n 实际obj返回消息体" + String.valueOf(((HashMap) response.path(assertparam)).size()));
                        }
                    }
                });
            }
        });
    }
}
