package com.dongao.DaQsAiTest.Util;

import com.dongao.DaQsAiTest.FileDto.CaseYamlFileDto;
import com.dongao.DaQsAiTest.FileDto.CaseYamlStepDto;
import com.dongao.DaQsAiTest.FileDto.JsonFileDto;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.yaml.snakeyaml.Yaml;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @Author: yule
 * @Description: 通过charles导出json文件，自动生成yaml测试用例
 * @Date: create in 2021/1/20 2:37 下午
 */
public class JsonToYamlUtils {
    private static final Logger logger = LoggerFactory.getLogger(JsonToYamlUtils.class);
    private static String apiVersion;
    private static String busniessAction;


    //从json文件中生成yaml的对象
    public static JsonFileDto jsonToobj(String jsonpath) {
        //jsonpath为charles导出文件的路径地址
        File file = new File(jsonpath);
        ObjectMapper objectMapper = new ObjectMapper();
        JsonFileDto jsonFileDto = null;
        try {
            JsonNode jsonNode = objectMapper.readTree(file);
            jsonFileDto = objectMapper.treeToValue(jsonNode, JsonFileDto.class);

        } catch (IOException e) {
            logger.error("解析{}异常,失败信息:{}", file.getName(), e.getMessage());
        }

        return jsonFileDto;
    }

    //创建yaml 对象，
    public static CaseYamlFileDto createCaseYmlDto(JsonFileDto jsonFileDto) {
        CaseYamlFileDto caseYamlFileDto = new CaseYamlFileDto();
        CaseYamlStepDto caseYamlStepDto = new CaseYamlStepDto();


        StringBuffer apiname = new StringBuffer();
        StringBuffer action = new StringBuffer();
        //填充api     /studyApi/study/V3/index => qs_study_index
        String apipath = jsonFileDto.getPath();
        String[] apiarray = apipath.split("/");

        apiname.append("qs_");
        for (int i = 0; i < apiarray.length; i++) {
            if (apiarray[i].contains("V")) {
                apiname = apiname.append(apiarray[i - 1]).append("_").append(apiarray[i + 1]);
                apiVersion=apiarray[i];
                busniessAction=apiarray[i-1];
                action.append(apiarray[i+1]);
            }

        }
        caseYamlStepDto.setApi(apiname.toString());
        //填充aciton
        caseYamlStepDto.setAction(action.toString());
        //填充params
        String query = jsonFileDto.getQuery();
        String[] queryArray = query.split("&");
        HashMap params = new HashMap();
        for(int i=0;i<queryArray.length;i++){
            String[] mapArray=queryArray[i].split("=");
            params.put(mapArray[0],mapArray[1]);
        }
        caseYamlStepDto.setParams(params);

        //填充assertparams
        //todo
        //填充caseYamlFileDto
        List<CaseYamlStepDto> steps=new ArrayList<>();
        steps.add(caseYamlStepDto);
        caseYamlFileDto.setSteps(steps);
        caseYamlFileDto.setName(caseYamlStepDto.getAction());
        caseYamlFileDto.setDescription(apipath+" 接口测试用例");
        System.out.println(caseYamlFileDto);
        return caseYamlFileDto;
    }

    //yaml实例对象转成真正的yaml文件
    public static void objToYaml(CaseYamlFileDto caseYamlFileDto){
        Yaml yaml = new Yaml();
        String yamlPath=null;
        String yamlDir="src/main/resources/com.dongao.DaQsAiTest/case/"+apiVersion+"/"+busniessAction;
        //判断系统版本文件夹是否存在，如果存在则不创建，直接创建文件，如果不存在，需要创建文件夹，再生产testcase文件
        File filedir=new File(yamlDir);
        if(!filedir.exists()){
            filedir.mkdirs();
            logger.info("api版本文件夹不存在，重新创建"+yamlPath);
        }
        //拼接测试用例路径
        yamlPath=yamlDir+"/"+caseYamlFileDto.getSteps().get(0).getApi()+".yaml";
        logger.info("成功创建测试用例路径=====》"+yamlPath);
        try {
            yaml.dump(caseYamlFileDto, new FileWriter(yamlPath));
        } catch (IOException e) {
            logger.error("生成测试用例文件yaml失败", e.getMessage());
        }
    }
    public static void createTestcaseYaml(String jsonpath){
        objToYaml(createCaseYmlDto(jsonToobj(jsonpath))) ;
    }
}
