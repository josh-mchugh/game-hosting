package com.example.demo.web.password.reset.model;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class ResetPasswordForm {

    @NotBlank
    private String password;

    @NotBlank
    private String confirmPassword;
}
