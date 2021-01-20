package com.dongao.DaQsAiTest.FileDto;

import java.util.List;

/**
 * @Author: yule
 * @Description:
 * @Date: create in 2021/1/19 4:05 下午
 */
public class RequestDto {

    public RequestSizeDto sizes;
    public RequestHeaderDto header;

    public String mimeType;
    public String charset;
    public String contentEncoding;

    public RequestBodyDto body;

    public RequestSizeDto getSizes() {
        return sizes;
    }

    public void setSizes(RequestSizeDto sizes) {
        this.sizes = sizes;
    }

    public RequestHeaderDto getHeader() {
        return header;
    }

    public void setHeader(RequestHeaderDto header) {
        this.header = header;
    }

    public String getMimeType() {
        return mimeType;
    }

    public void setMimeType(String mimeType) {
        this.mimeType = mimeType;
    }

    public String getCharset() {
        return charset;
    }

    public void setCharset(String charset) {
        this.charset = charset;
    }

    public String getContentEncoding() {
        return contentEncoding;
    }

    public void setContentEncoding(String contentEncoding) {
        this.contentEncoding = contentEncoding;
    }

    public RequestBodyDto getBody() {
        return body;
    }

    public void setBody(RequestBodyDto body) {
        this.body = body;
    }
}
