package com.dongao.DaQsAiTest.FileDto;

import java.util.List;

/**
 * @Author: yule
 * @Description:
 * @Date: create in 2021/1/19 4:01 下午
 */
public class JsonFileDto {
    public String method;
    public String host;
    public String path;
    public String query;
    public String remoteAddress;

    public RequestDto request;
    public ResponseDto response;



    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    public String getRemoteAddress() {
        return remoteAddress;
    }

    public void setRemoteAddress(String remoteAddress) {
        this.remoteAddress = remoteAddress;
    }

    public RequestDto getRequest() {
        return request;
    }

    public void setRequest(RequestDto request) {
        this.request = request;
    }

    public ResponseDto getResponse() {
        return response;
    }

    public void setResponse(ResponseDto response) {
        this.response = response;
    }


}
