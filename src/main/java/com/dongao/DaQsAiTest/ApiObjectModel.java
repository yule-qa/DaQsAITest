package com.dongao.DaQsAiTest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

/**
 * @Author: yule
 * @Description: yaml文件模型：
 * 将yaml文件解析成ApiObject对象
 * 对象属性为yaml 节点
 * @Date: create in 2021/1/11 1:42 下午
 */
public class ApiObjectModel {
    public String name;
    public HashMap<String,ApiObjectActionModel> actions;

    /**
     * 加载yaml文件，返回ApiObjectModel解析对象
      * @return
     */
    public static ApiObjectModel load(String path) throws IOException {
        ObjectMapper objectMapper=new ObjectMapper(new YAMLFactory());

        return objectMapper.readValue(new File(path),ApiObjectModel.class);


    }

    /**
     * 运行这个apiobject中的某个封装的方法
     */
    public void run(ApiObjectActionModel action){
        action.run();
    }


}
