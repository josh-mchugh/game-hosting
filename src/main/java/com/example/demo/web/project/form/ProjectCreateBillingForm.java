package com.example.demo.web.project.form;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class ProjectCreateBillingForm {

    @NotBlank
    private String cardNumber;
}
