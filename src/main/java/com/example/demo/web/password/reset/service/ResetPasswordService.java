package com.example.demo.web.password.reset.service;

import com.example.demo.email.entity.EmailTemplate;
import com.example.demo.email.service.IEmailService;
import com.example.demo.email.service.model.EmailCreateRequest;
import com.example.demo.recovery.service.IRecoveryTokenService;
import com.example.demo.user.model.User;
import com.example.demo.user.service.IUserService;
import com.example.demo.user.service.model.UserPasswordResetRequest;
import com.example.demo.util.AppUrlUtil;
import com.example.demo.web.password.reset.service.model.PasswordResetRequest;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ResetPasswordService implements IResetPasswordService {

    private final AppUrlUtil appUrlUtil;
    private final IUserService userService;
    private final IRecoveryTokenService recoveryTokenService;
    private final IEmailService emailService;

    @Override
    public User handlePasswordReset(PasswordResetRequest request) {

        UserPasswordResetRequest resetRequest = UserPasswordResetRequest.builder()
                .password(request.getPassword())
                .recoveryTokenId(request.getRecoveryTokenId())
                .build();

        User user = userService.handlePasswordReset(resetRequest);

        recoveryTokenService.handleDeleteRecoveryToken(request.getRecoveryTokenId());

        EmailCreateRequest emailRequest = EmailCreateRequest.builder()
                .toAddress(user.getEmail())
                .template(EmailTemplate.PASSWORD_RESET)
                .bodyContext("email", user.getEmail())
                .bodyContext("loginUrl", appUrlUtil.getAppUrl("/login"))
                .bodyContext("forgotPasswordUrl", appUrlUtil.getAppUrl("/forgot-password", new ImmutablePair<>("email", user.getEmail())))
                .build();

        emailService.handleCreateEmail(emailRequest);

        return user;
    }
}
