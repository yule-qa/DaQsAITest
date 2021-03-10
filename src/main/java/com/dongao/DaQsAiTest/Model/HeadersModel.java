package com.dongao.DaQsAiTest.Model;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

/**
 * @Author: yule
 * @Description: 用于解析hearders.yaml的对象模型
 * @Date: create in 2021/1/14 2:19 下午
 */
public class HeadersModel {
    public String name="";
    public String description="";

    public HashMap<String,String> headers;

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setHeaders(HashMap<String, String> headers) {
        this.headers = headers;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public HashMap<String, String> getHeaders() {
        return headers;
    }

    /**
     * 加载一个yaml文件，并转成 headers模型类
     * @param path
     * @return
     * @throws IOException
     */
    public static HeadersModel load(String path) throws IOException {
        ObjectMapper objectMapper=new ObjectMapper(new YAMLFactory());
        return objectMapper.readValue(new File(path),HeadersModel.class);
    }
    public List<HashMap<String,Object>> env;
}
