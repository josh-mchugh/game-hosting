package com.example.demo.web.project.form;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class ProjectCreateRegionForm {

    @NotBlank(message = "{error.not.blank}")
    private String selectedRegionId;
}
