package com.dongao.DaQsAiTest.FileDto;

/**
 * @Author: yule
 * @Description:
 * @Date: create in 2021/1/19 4:30 下午
 */
public class ResponseTextDto {
    public String msg;
    public String code;
    public ResponseObjDto obj;

    public ResponseObjDto getObj() {
        return obj;
    }

    public void setObj(ResponseObjDto obj) {
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
