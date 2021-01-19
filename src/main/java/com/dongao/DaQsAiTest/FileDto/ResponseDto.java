package com.dongao.DaQsAiTest.FileDto;

import java.util.List;

/**
 * @Author: yule
 * @Description:
 * @Date: create in 2021/1/19 4:06 下午
 */
public class ResponseDto {
    public String status;
    public ResponseSizeDto sizes;
    public ResponseBodyDto body;



    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    public ResponseSizeDto getSizes() {
        return sizes;
    }

    public void setSizes(ResponseSizeDto sizes) {
        this.sizes = sizes;
    }

    public ResponseBodyDto getBody() {
        return body;
    }

    public void setBody(ResponseBodyDto body) {
        this.body = body;
    }

}
