package com.example.demo.web.password.reset.model;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class ResetPasswordForm {

    @NotBlank(message = "{error.not.blank}")
    private String password;

    @NotBlank(message = "{error.not.blank}")
    private String confirmPassword;
}
