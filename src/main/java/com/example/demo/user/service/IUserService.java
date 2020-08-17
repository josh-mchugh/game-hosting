package com.example.demo.user.service;

import com.example.demo.user.model.User;
import com.example.demo.user.service.model.UserCreateRequest;
import com.example.demo.user.service.model.UserPasswordResetRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

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

    User handleCreateRecoveryToken(String email);

    User handleDeleteRecoveryTokenById(String id);

    boolean existsByRecoveryToken(String token);

    Page<User> getByRecoveryTokensExpired(Pageable pageable);
}
