package com.dongao.DaQsAiTest.FileDto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import java.util.List;

/**
 * @Author: yule
 * @Description:
 * @Date: create in 2021/1/19 4:28 下午
 */
public class ResponseBodyDto {
        @JsonDeserialize(using = ResponseTextDtoDeserializer.class)
        public ResponseTextDto text;
        public String charset;
        public String decoded;

        public ResponseTextDto getText() {
                return text;
        }

        public void setText(ResponseTextDto text) {
                this.text = text;
        }

        public String getCharset() {
                return charset;
        }

        public void setCharset(String charset) {
                this.charset = charset;
        }

        public String getDecoded() {
                return decoded;
        }

        public void setDecoded(String decoded) {
                this.decoded = decoded;
        }
}
