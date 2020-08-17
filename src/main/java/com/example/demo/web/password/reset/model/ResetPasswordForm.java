package com.example.demo.web.password.reset.model;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class ResetPasswordForm {

    private String password;
    private String confirmPassword;
}
