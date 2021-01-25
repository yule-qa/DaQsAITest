package com.dongao.DaQsAiTest;

import com.dongao.DaQsAiTest.FileDto.CaseYamlFileDto;
import com.dongao.DaQsAiTest.FileDto.CaseYamlStepDto;
import com.dongao.DaQsAiTest.FileDto.JsonFileDto;
import com.dongao.DaQsAiTest.Util.FileUtils;
import com.dongao.DaQsAiTest.Util.JsonToYamlUtils;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import org.junit.jupiter.params.provider.Arguments;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.yaml.snakeyaml.Yaml;

/**
 * @Author: yule
 * @Description:创建测试用例文件
 * @Date: create in 2021/1/19 3:30 下午
 */
public class CreateTestCaseTest {
    private static final Logger logger = LoggerFactory.getLogger(CreateTestCaseTest.class);

    @Test
    //从json文件中生成测试用例yaml文件
    public void createTestcaseYaml() {
        String caseSourceFileDir="src/main/resources/com.dongao.DaQsAiTest/data";
        List testcaseSourceJsonFileList=load(caseSourceFileDir);
        //遍历测试用例列表，根据每个用例json文件地址，生成对应的测试用例
        for (Object testcaseSourceJsonFile : testcaseSourceJsonFileList) {
            FileUtils.actionPerformed(caseSourceFileDir);
            JsonToYamlUtils.createTestcaseYaml(testcaseSourceJsonFile.toString());
        }
    }



    //从/data文件夹下，逐个加载测试用例json文件到List里，并返回
    public List load(String testcaseSourceJsonDir){
        List<String> testcases=new ArrayList<>();

        Arrays.stream(new File(testcaseSourceJsonDir).list())
                .forEach(name-> {
                    String testcaseSourceJsonFile = testcaseSourceJsonDir + "/" + name;
                    testcases.add(testcaseSourceJsonFile);
                        }
                );
        return  testcases;
    }
}
