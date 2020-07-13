package com.example.demo.web.verification.service;

import com.example.demo.email.entity.EmailTemplate;
import com.example.demo.email.service.IEmailService;
import com.example.demo.email.service.model.EmailCreateRequest;
import com.example.demo.user.model.User;
import com.example.demo.user.service.IUserService;
import com.example.demo.util.AppUrlUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class VerifyService implements IVerifyService {

    private final AppUrlUtil appUrlUtil;
    private final IUserService userService;
    private final IEmailService emailService;

    @Override
    public void handleResendVerificationEmail(String userId) {

        User user = userService.handleResetEmailVerification(userId);

        EmailCreateRequest emailCreateRequest = EmailCreateRequest.builder()
                .toAddress(user.getEmail())
                .template(EmailTemplate.EMAIL_VERIFICATION)
                .context("verificationUrl", appUrlUtil.getAppUrl(String.format("/verify/%s", user.getVerification().getToken())))
                .build();

        emailService.handleCreateEmail(emailCreateRequest);
    }
}
