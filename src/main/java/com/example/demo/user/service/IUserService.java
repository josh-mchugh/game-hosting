package com.example.demo.user.service;

import com.example.demo.user.model.User;
import com.example.demo.user.service.model.UserCreateRequest;
import com.example.demo.user.service.model.UserPasswordResetRequest;

public interface IUserService {

    User handleCreateUser(UserCreateRequest request);

    boolean existsUserByEmail(String email);

    boolean existsByVerificationToken(String token);

    User getUserByEmail(String email);

    User handleAuthenticationFailure(String email);

    User handleAuthenticationSuccess(String email);

    User handlePasswordReset(UserPasswordResetRequest request);

    User handleEmailVerification(String token);

    User handleResetEmailVerification(String id);
}
