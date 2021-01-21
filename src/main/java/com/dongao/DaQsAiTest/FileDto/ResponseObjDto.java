package com.dongao.DaQsAiTest.FileDto;

/**
 * @Author: yule
 * @Description:
 * @Date: create in 2021/1/21 10:50 上午
 */
public class ResponseObjDto {
    public ResponseSummaryDto summary;
    public ResponseCurriculumDto curriculum;
    public String startStudy;

    public String getStartStudy() {
        return startStudy;
    }

    public void setStartStudy(String startStudy) {
        this.startStudy = startStudy;
    }

    public ResponseSummaryDto getSummary() {
        return summary;
    }

    public void setSummary(ResponseSummaryDto summary) {
        this.summary = summary;
    }

    public ResponseCurriculumDto getCurriculum() {
        return curriculum;
    }

    public void setCurriculum(ResponseCurriculumDto curriculum) {
        this.curriculum = curriculum;
    }


}
