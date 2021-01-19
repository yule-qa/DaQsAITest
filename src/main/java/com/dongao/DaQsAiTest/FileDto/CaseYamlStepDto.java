package com.dongao.DaQsAiTest.FileDto;

import java.util.HashMap;
import java.util.List;

/**
 * @Author: yule
 * @Description:
 * @Date: create in 2021/1/19 4:40 下午
 */
public class CaseYamlStepDto {
    public String api;
    public String action;
    public HashMap<String,String> params;
    public HashMap<String,String> assertparams;

    public String getApi() {
        return api;
    }

    public void setApi(String api) {
        this.api = api;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public HashMap<String, String> getParams() {
        return params;
    }

    public void setParams(HashMap<String, String> params) {
        this.params = params;
    }

    public HashMap<String, String> getAssertparams() {
        return assertparams;
    }

    public void setAssertparams(HashMap<String, String> assertparams) {
        this.assertparams = assertparams;
    }




}
