package com.dongao.DaQsAiTest;

import com.dongao.DaQsAiTest.FileDto.CaseYamlFileDto;
import com.dongao.DaQsAiTest.FileDto.CaseYamlStepDto;
import com.dongao.DaQsAiTest.FileDto.JsonFileDto;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;

/**
 * @Author: yule
 * @Description:
 * @Date: create in 2021/1/19 3:30 下午
 */
public class JsonTest {

    @Test
    //从json文件中生成yaml的对象
    public void jsonToobj() {
        String jsonpath = "src/main/resources/com.dongao.DaQsAiTest/data/test.chlsj";
        File file = new File(jsonpath);
        ObjectMapper objectMapper = new ObjectMapper();
        JsonFileDto jsonFileDto = null;
        try {
            JsonNode jsonNode = objectMapper.readTree(file);
            jsonFileDto = objectMapper.treeToValue(jsonNode, JsonFileDto.class);

        } catch (IOException e) {
            e.printStackTrace();
        }

        createCaseYmlDto(jsonFileDto);
    }

    public CaseYamlFileDto createCaseYmlDto(JsonFileDto jsonFileDto) {
        CaseYamlFileDto caseYamlFileDto = new CaseYamlFileDto();
        CaseYamlStepDto caseYamlStepDto = new CaseYamlStepDto();
        //填充api     /studyApi/study/V3/index => qs_study_index
        String apipath = jsonFileDto.getPath();
        String[] apiarray = apipath.split("/");
        StringBuffer apiname = new StringBuffer();
        apiname.append("qs_");
        for (int i = 0; i < apiarray.length; i++) {
            if (apiarray[i].contains("V")) {
                apiname = apiname.append(apiarray[i - 1]).append("_").append(apiarray[i + 1]);
            }

        }
        System.out.println(apiname.toString());
        caseYamlStepDto.setApi(apiname.toString());
        return caseYamlFileDto;
    }

}
