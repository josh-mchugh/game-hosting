package com.example.demo.web.password.reset.service;

import com.example.demo.web.password.reset.service.model.PasswordResetRequest;

public interface IResetPasswordService {

    void handlePasswordReset(PasswordResetRequest request);
}
