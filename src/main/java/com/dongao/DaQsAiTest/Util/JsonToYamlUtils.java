package com.dongao.DaQsAiTest.Util;

import com.dongao.DaQsAiTest.FileDto.CaseYamlFileDto;
import com.dongao.DaQsAiTest.FileDto.CaseYamlStepDto;
import com.dongao.DaQsAiTest.FileDto.JsonFileDto;
import com.dongao.DaQsAiTest.Model.ApiObjectActionModel;
import com.dongao.DaQsAiTest.Model.ApiObjectModel;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.yaml.snakeyaml.Yaml;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
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
    private static String caseYamlPath;
    private static String apiYamlPath;
    private static JsonFileDto jsonFileDto;


    /**
     * 从charles-json文件中生成yaml的对象
     * （charles文件，生成JsonFileDto）
     */

    public static JsonFileDto jsonToobj(String jsonpath) {
        //jsonpath为charles导出文件的路径地址
        File file = new File(jsonpath);
        ObjectMapper objectMapper = new ObjectMapper();

        try {
            JsonNode jsonNode = objectMapper.readTree(file);
            jsonFileDto = objectMapper.treeToValue(jsonNode, JsonFileDto.class);

        } catch (IOException e) {
            logger.error("解析{}异常,失败信息:{}", file.getName(), e.getMessage());
        }

        return jsonFileDto;
    }

    /**
     * 创建测试用例的对象，用于转成测试用例yaml，
     * （从jsonFileDto 提取有用信息到caseYamlFileDto里）
     */

    public static CaseYamlFileDto createCaseYmlDto(JsonFileDto jsonFileDto) {
        CaseYamlFileDto caseYamlFileDto = new CaseYamlFileDto();
        CaseYamlStepDto caseYamlStepDto = new CaseYamlStepDto();


        StringBuffer apiname = new StringBuffer();
        StringBuffer action = new StringBuffer();
        //填充api     /studyApi/study/V3/index => qs_study_index
        if(jsonFileDto !=null) {
            String apipath = jsonFileDto.getPath();
            String[] apiarray = apipath.split("/");

            apiname.append("qs_");
            for (int i = 0; i < apiarray.length; i++) {
                if (apiarray[i].contains("V")) {
                    String[] busniessPath=apipath.split(apiarray[i]);// studyApi/study/V3/index =》[/studyApi/study/ 、 /index]
                    int busniessIndex=busniessPath[1].indexOf("/",1);//查看业务路径是否包含多个/，如果多个证明是多路径
                    if(busniessIndex != -1){
                        busniessPath[1]=busniessPath[1].replaceAll("/","_");
                        apiname=apiname.append(apiarray[i - 1]).append(busniessPath[1]);
                        //为了填充action，又拆分了一次，为了取出路径最后的那个api name
                        String[] actionStr=busniessPath[1].split("_");
                        action.append(actionStr[actionStr.length-1]);
                    }else {
                        apiname = apiname.append(apiarray[i - 1]).append("_").append(apiarray[i + 1]);
                        action.append(apiarray[i + 1]);
                    }
                    apiVersion = apiarray[i];
                    busniessAction = apiarray[i - 1];

                    break;
                }

            }
            caseYamlStepDto.setApi(apiname.toString());
            //填充aciton
            caseYamlStepDto.setAction(action.toString());
            String query = null;
            //填充params
            if ("GET".equals(jsonFileDto.getMethod())) {
                query = jsonFileDto.getQuery();
            } else if ("POST".equals(jsonFileDto.getMethod())) {
                query = jsonFileDto.getRequest().getBody().getText();
            }
            String[] queryArray = query.split("&");
            HashMap params = new HashMap();
            for (int i = 0; i < queryArray.length; i++) {
                String[] mapArray = queryArray[i].split("=");
                if(mapArray.length>1){
                    mapArray[1]=urldecode(mapArray[1]); //将加密的值，变成解密
                    params.put(mapArray[0], mapArray[1]);
                }else {
                    params.put(mapArray[0], "");
                }
            }
            caseYamlStepDto.setParams(params);


            //填充assertparams
            HashMap<String, String> assertparams = new HashMap<String, String>();
            assertparams.put("matcher", "equalTo");
            assertparams.put("assertparam", "obj");
            HashMap<String, Object> obj = jsonFileDto.getResponse().getBody().getText().getObj();
            if (obj != null) {
                //get请求的obj不为null
                assertparams.put("expect", String.valueOf(obj.size()));
            } else {
                //post请求的obj为null
                assertparams.put("expect", "0");

            }
            caseYamlStepDto.setAssertparams(assertparams);

            //填充caseYamlFileDto
            List<CaseYamlStepDto> steps = new ArrayList<>();
            steps.add(caseYamlStepDto);
            caseYamlFileDto.setSteps(steps);
            caseYamlFileDto.setName(caseYamlStepDto.getAction());
            caseYamlFileDto.setDescription(apipath + " 接口测试用例");
            System.out.println(caseYamlFileDto);
            return caseYamlFileDto;
        }
            return  null;
    }

    /**
     * yaml实例对象转成真正的api yaml文件
     */

    public static void objToYaml(CaseYamlFileDto caseYamlFileDto){
        //创建APiObjectModel对象
        ApiObjectModel apiObjectModel = createApiObject(caseYamlFileDto);

        Yaml yaml = new Yaml();
        String caseyamlDir=createDir("case");
        String apiyamlDir=createDir("api");
        //拼接测试用例路径
        caseYamlPath=caseyamlDir+"/"+caseYamlFileDto.getSteps().get(0).getApi()+".yaml";
        apiYamlPath=apiyamlDir+"/"+caseYamlFileDto.getSteps().get(0).getApi()+".yaml";
        logger.info("成功创建测试用例文件路径=====》"+caseYamlPath);
        logger.info("成功创建对象文件路径=====》"+apiYamlPath);
        try {
            yaml.dump(caseYamlFileDto, new FileWriter(caseYamlPath));
            yaml.dump(apiObjectModel, new FileWriter(apiYamlPath));
        } catch (IOException e) {
            logger.error("生成文件yaml失败", e.getMessage());
        }
    }
    public static String createDir(String path){
        String yamlDir="src/main/resources/com.dongao.DaQsAiTest/"+path+"/"+apiVersion+"/"+busniessAction;
        //判断系统版本文件夹是否存在，如果存在则不创建，直接创建文件，如果不存在，需要创建文件夹，再生产testcase文件
        File filedir=new File(yamlDir);
        if(!filedir.exists()){
            filedir.mkdirs();
            logger.info("文件夹不存在，现在创建"+yamlDir);
        }
        return yamlDir;
    }

    //创建apiobj
    public static ApiObjectModel createApiObject(CaseYamlFileDto caseYamlFileDto){
        ApiObjectModel apiObjectModel=new ApiObjectModel();
        ApiObjectActionModel  apiObjectActionModel=new ApiObjectActionModel();
        String method=jsonFileDto.getMethod();
        if (method.equals("GET")){
            apiObjectActionModel.setGet("http://"+jsonFileDto.getHost()+jsonFileDto.getPath());
        }else if(method.equals("POST")){
            apiObjectActionModel.setPost("http://"+jsonFileDto.getHost()+jsonFileDto.getPath());
        }
        apiObjectModel.setName(caseYamlFileDto.getSteps().get(0).getApi());
        HashMap<String,ApiObjectActionModel> actions=new HashMap();
        actions.put(caseYamlFileDto.getName(),apiObjectActionModel);
        apiObjectModel.setActions(actions);

        return apiObjectModel;

    }

    //urldecode,将抓取的query信息参数转码为正常值
    public static String urldecode(String encodestr){
        try {
            String aa = URLDecoder.decode(encodestr,"UTF-8");
            return  aa;
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;
    }
    public static void createTestcaseYaml(String jsonpath){
        objToYaml(createCaseYmlDto(jsonToobj(jsonpath))) ;
    }


}
