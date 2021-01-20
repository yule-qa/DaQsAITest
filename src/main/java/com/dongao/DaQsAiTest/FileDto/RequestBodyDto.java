package com.dongao.DaQsAiTest.FileDto;

/**
 * @Author: yule
 * @Description:
 * @Date: create in 2021/1/20 4:56 下午
 */
public class RequestBodyDto {
    public String text;
    public String charset;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getCharset() {
        return charset;
    }

    public void setCharset(String charset) {
        this.charset = charset;
    }
}
