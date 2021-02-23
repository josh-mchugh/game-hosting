package com.example.demo.web.password.forgot.form;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Data
public class ForgotPasswordForm {

    @Email(message = "{error.valid.email}")
    @NotBlank(message = "{error.not.blank}")
    private String email;
}
