package com.example.demo.web.password.forgot.service;

import com.example.demo.web.password.forgot.service.model.ForgotPasswordResponse;

public interface IForgotPasswordService {

    ForgotPasswordResponse handleForgotPassword(String emailAddress);
}
