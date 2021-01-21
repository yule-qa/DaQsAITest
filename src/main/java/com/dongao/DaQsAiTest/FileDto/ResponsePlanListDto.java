package com.dongao.DaQsAiTest.FileDto;

/**
 * @Author: yule
 * @Description:
 * @Date: create in 2021/1/21 11:01 上午
 */
public class ResponsePlanListDto {
    public String planId;
    public String type;
    public String title;
    public String explanation;
    public String state;
    public String action;
    public String duration;

    public String getPlanId() {
        return planId;
    }

    public void setPlanId(String planId) {
        this.planId = planId;
    }
}
