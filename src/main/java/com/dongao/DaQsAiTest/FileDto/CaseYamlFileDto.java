package com.dongao.DaQsAiTest.FileDto;

import java.util.List;

/**
 * @Author: yule
 * @Description:
 * @Date: create in 2021/1/19 4:37 下午
 */
public class CaseYamlFileDto {
    public String name;
    public String description;
    public List<CaseYamlStepDto> steps;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<CaseYamlStepDto> getSteps() {
        return steps;
    }

    public void setSteps(List<CaseYamlStepDto> steps) {
        this.steps = steps;
    }
}
