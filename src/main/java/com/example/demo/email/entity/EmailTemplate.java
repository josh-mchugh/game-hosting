package com.example.demo.email.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum EmailTemplate {

    TEST("email/test", "Test Subject"),
    WELCOME("email/welcome", "Welcome To Game Hosting"),
    EMAIL_VERIFICATION("email/email-verification", "Email Verification"),
    PASSWORD_RECOVERY("email/password-reset", "Password Reset Email"),
    PASSWORD_RESET("email/password-reset-complete", "Password Reset Complete")
    ;

    private final String template;
    private final String subject;
}
