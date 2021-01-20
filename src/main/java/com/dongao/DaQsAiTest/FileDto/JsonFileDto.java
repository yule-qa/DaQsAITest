package com.dongao.DaQsAiTest.FileDto;

import java.util.List;

/**
 * @Author: yule
 * @Description:
 * @Date: create in 2021/1/19 4:01 下午
 */
public class JsonFileDto {

    public String status;
    public String method;
    public String protocolVersion;
    public String scheme;
    public String host;
    public String port;
    public String actualPort;
    public String path;
    public String query;
    public String tunnel;
    public String keptAlive;
    public String webSocket;
    public String remoteAddress;
    public String clientAddress;
    public String clientPort;
    public String totalSize;

    public JsonTimeDto times;
    public JsonDurationsDto durations;
    public JsonSpeedDto speeds;


    public RequestDto request;
    public ResponseDto response;


    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getProtocolVersion() {
        return protocolVersion;
    }

    public void setProtocolVersion(String protocolVersion) {
        this.protocolVersion = protocolVersion;
    }

    public String getScheme() {
        return scheme;
    }

    public void setScheme(String scheme) {
        this.scheme = scheme;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }

    public String getActualPort() {
        return actualPort;
    }

    public void setActualPort(String actualPort) {
        this.actualPort = actualPort;
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

    public String getTunnel() {
        return tunnel;
    }

    public void setTunnel(String tunnel) {
        this.tunnel = tunnel;
    }

    public String getKeptAlive() {
        return keptAlive;
    }

    public void setKeptAlive(String keptAlive) {
        this.keptAlive = keptAlive;
    }

    public String getWebSocket() {
        return webSocket;
    }

    public void setWebSocket(String webSocket) {
        this.webSocket = webSocket;
    }

    public String getRemoteAddress() {
        return remoteAddress;
    }

    public void setRemoteAddress(String remoteAddress) {
        this.remoteAddress = remoteAddress;
    }

    public String getClientAddress() {
        return clientAddress;
    }

    public void setClientAddress(String clientAddress) {
        this.clientAddress = clientAddress;
    }

    public String getClientPort() {
        return clientPort;
    }

    public void setClientPort(String clientPort) {
        this.clientPort = clientPort;
    }

    public String getTotalSize() {
        return totalSize;
    }

    public void setTotalSize(String totalSize) {
        this.totalSize = totalSize;
    }

    public JsonTimeDto getTimes() {
        return times;
    }

    public void setTimes(JsonTimeDto times) {
        this.times = times;
    }

    public JsonDurationsDto getDurations() {
        return durations;
    }

    public void setDurations(JsonDurationsDto durations) {
        this.durations = durations;
    }

    public JsonSpeedDto getSpeeds() {
        return speeds;
    }

    public void setSpeeds(JsonSpeedDto speeds) {
        this.speeds = speeds;
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
