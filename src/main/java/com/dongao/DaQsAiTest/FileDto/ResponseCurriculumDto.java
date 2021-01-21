package com.dongao.DaQsAiTest.FileDto;

import java.util.List;

/**
 * @Author: yule
 * @Description:
 * @Date: create in 2021/1/21 10:58 上午
 */
public class ResponseCurriculumDto {
    public String name;
    public List<ResponsePlanListDto> planList;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<ResponsePlanListDto> getPlanList() {
        return planList;
    }

    public void setPlanList(List<ResponsePlanListDto> planList) {
        this.planList = planList;
    }
}
