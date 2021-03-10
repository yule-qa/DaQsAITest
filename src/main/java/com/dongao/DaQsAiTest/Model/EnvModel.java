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
public class EnvModel {

   public String test;
   public String prod;

    public String getTest() {
        return test;
    }

    public void setTest(String test) {
        this.test = test;
    }

    public String getProd() {
        return prod;
    }

    public void setProd(String prod) {
        this.prod = prod;
    }

    /**
     * 加载一个yaml文件，并转成 headers模型类
     * @param path
     * @return
     * @throws IOException
     */
    public static EnvModel load(String path) throws IOException {
        ObjectMapper objectMapper=new ObjectMapper(new YAMLFactory());
        return objectMapper.readValue(new File(path), EnvModel.class);
    }

}
