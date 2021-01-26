package com.dongao.DaQsAiTest.FileDto;

import java.util.HashMap;

/**
 * @Author: yule
 * @Description:
 * @Date: create in 2021/1/19 4:30 下午
 */
public class ResponseTextDto {
    public String msg;
    public String code;
    public HashMap<String,Object> obj;

    public HashMap<String, Object> getObj() {
        return obj;
    }

    public void setObj(HashMap<String, Object> obj) {
        this.obj = obj;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
