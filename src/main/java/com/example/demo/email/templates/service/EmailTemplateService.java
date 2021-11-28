package com.example.demo.email.templates.service;

import com.example.demo.user.aggregate.event.UserPasswordChangedEmailEvent;
import com.example.demo.user.aggregate.event.UserRecoveryTokenEmailEvent;
import com.example.demo.user.aggregate.event.UserVerificationEmailEvent;
import com.example.demo.user.aggregate.event.UserVerifyResetEmailEvent;
import com.example.demo.user.aggregate.event.UserWelcomeEmailEvent;

public interface EmailTemplateService {

    void handleUserWelcomeEmail(UserWelcomeEmailEvent event);

    void handleUserVerificationEmail(UserVerificationEmailEvent event);

    void handleUserVerifyResetEmail(UserVerifyResetEmailEvent event);

    void handleUserChangedPasswordEmail(UserPasswordChangedEmailEvent event);

    void handleUserRecoveryTokenEmail(UserRecoveryTokenEmailEvent event);
}
