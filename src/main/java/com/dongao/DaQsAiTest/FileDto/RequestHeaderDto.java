package com.dongao.DaQsAiTest.FileDto;

import java.util.List;

/**
 * @Author: yule
 * @Description:
 * @Date: create in 2021/1/19 4:08 下午
 */
public class RequestHeaderDto {
    public String firstLine;



    public List<RequestFinalHeadersDto> headers;
    public String getFirstLine() {
        return firstLine;
    }

    public void setFirstLine(String firstLine) {
        this.firstLine = firstLine;
    }

    public List<RequestFinalHeadersDto> getHeaders() {
        return headers;
    }

    public void setHeaders(List<RequestFinalHeadersDto> headers) {
        this.headers = headers;
    }

}
