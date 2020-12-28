package com.example.demo.web.password.reset.command.service;

import com.example.demo.web.password.reset.command.service.model.PasswordResetRequest;

public interface IResetPasswordService {

    void handlePasswordReset(PasswordResetRequest request);
}
