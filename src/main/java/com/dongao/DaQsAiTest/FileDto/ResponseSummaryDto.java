package com.dongao.DaQsAiTest.FileDto;

import java.util.List;

/**
 * @Author: yule
 * @Description:
 * @Date: create in 2021/1/21 10:55 上午
 */
public class ResponseSummaryDto {
    public List<ResponseListDto> list;
    public String hintContent;

    public List<ResponseListDto> getList() {
        return list;
    }

    public void setList(List<ResponseListDto> list) {
        this.list = list;
    }

    public String getHintContent() {
        return hintContent;
    }

    public void setHintContent(String hintContent) {
        this.hintContent = hintContent;
    }
}
