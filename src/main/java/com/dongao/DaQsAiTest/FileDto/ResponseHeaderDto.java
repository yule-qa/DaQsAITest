package com.dongao.DaQsAiTest.FileDto;

import java.util.List;

/**
 * @Author: yule
 * @Description:
 * @Date: create in 2021/1/20 5:10 下午
 */
public class ResponseHeaderDto {
    public String firstLine;
    public List<ResponseFinalHeadersDto> headers;

    public String getFirstLine() {
        return firstLine;
    }

    public void setFirstLine(String firstLine) {
        this.firstLine = firstLine;
    }

    public List<ResponseFinalHeadersDto> getHeaders() {
        return headers;
    }

    public void setHeaders(List<ResponseFinalHeadersDto> headers) {
        this.headers = headers;
    }

}
