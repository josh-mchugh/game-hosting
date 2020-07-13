package com.example.demo.web.registration.model;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Data
public class RegistrationForm {

    @Email
    @NotBlank
    private String email;

    private String password;
    private String confirmPassword;
}