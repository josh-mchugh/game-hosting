package com.example.demo.web.password.reset.command.model;

import lombok.Data;

@Data
public class ResetPasswordForm {

    private String password;
    private String confirmPassword;
}
