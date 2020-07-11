package com.example.demo.web.password.reset.service;

import com.example.demo.user.model.User;
import com.example.demo.web.password.reset.service.model.PasswordResetRequest;

public interface IResetPasswordService {

    User handlePasswordReset(PasswordResetRequest request);
}
