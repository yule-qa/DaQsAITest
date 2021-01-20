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
    public ResponseHeaderDto header;

    public String mimeType;
    public String charset;
    public String contentEncoding;

    public String getContentEncoding() {
        return contentEncoding;
    }

    public void setContentEncoding(String contentEncoding) {
        this.contentEncoding = contentEncoding;
    }

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

    public ResponseHeaderDto getHeader() {
        return header;
    }

    public void setHeader(ResponseHeaderDto header) {
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
}
