package com.example.demo.email.templates.service;

import com.example.demo.email.aggregate.command.EmailCreateCommand;
import com.example.demo.email.entity.EmailTemplate;
import com.example.demo.user.aggregate.event.UserPasswordChangedEmailEvent;
import com.example.demo.user.aggregate.event.UserRecoveryTokenEmailEvent;
import com.example.demo.user.aggregate.event.UserVerificationEmailEvent;
import com.example.demo.user.aggregate.event.UserVerifyResetEmailEvent;
import com.example.demo.user.aggregate.event.UserWelcomeEmailEvent;
import com.example.demo.util.AppUrlUtil;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class EmailTemplateService implements IEmailTemplateService {

    private final AppUrlUtil appUrlUtil;
    private final CommandGateway commandGateway;

    @Override
    @EventHandler
    public void handleUserWelcomeEmail(UserWelcomeEmailEvent event) {

        EmailCreateCommand welcomeEmailCommand = EmailCreateCommand.builder()
                .id(UUID.randomUUID())
                .toAddress(event.getEmail())
                .template(EmailTemplate.WELCOME)
                .build();

        commandGateway.send(welcomeEmailCommand);
    }

    @Override
    @EventHandler
    public void handleUserVerificationEmail(UserVerificationEmailEvent event) {

        EmailCreateCommand verificationEmailCommand = EmailCreateCommand.builder()
                .id(UUID.randomUUID())
                .toAddress(event.getEmail())
                .template(EmailTemplate.USER_VERIFICATION)
                .bodyContext("verificationUrl", appUrlUtil.getAppUrl(String.format("/verify/%s", event.getToken())))
                .build();

        commandGateway.send(verificationEmailCommand);
    }

    @Override
    @EventHandler
    public void handleUserChangedPasswordEmail(UserPasswordChangedEmailEvent event) {

        EmailCreateCommand emailCreateCommand = EmailCreateCommand.builder()
                .id(UUID.randomUUID())
                .toAddress(event.getEmail())
                .template(EmailTemplate.PASSWORD_RESET)
                .bodyContext("email", event.getEmail())
                .bodyContext("loginUrl", appUrlUtil.getAppUrl("/login"))
                .bodyContext("forgotPasswordUrl", appUrlUtil.getAppUrl("/forgot-password", new ImmutablePair<>("email", event.getEmail())))
                .build();

        commandGateway.send(emailCreateCommand);
    }

    @Override
    @EventHandler
    public void handleUserRecoveryTokenEmail(UserRecoveryTokenEmailEvent event) {

        EmailCreateCommand emailCreateCommand = EmailCreateCommand.builder()
                .id(UUID.randomUUID())
                .toAddress(event.getEmail())
                .template(EmailTemplate.PASSWORD_RECOVERY)
                .bodyContext("email", event.getEmail())
                .bodyContext("resetPasswordUrl", appUrlUtil.getAppUrl(String.format("%s/%s", "/reset-password/", event.getToken())))
                .build();

        commandGateway.send(emailCreateCommand);
    }

    @Override
    @EventHandler
    public void handleUserVerifyResetEmail(UserVerifyResetEmailEvent event) {

        EmailCreateCommand emailCreateCommand = EmailCreateCommand.builder()
                .id(UUID.randomUUID())
                .toAddress(event.getEmail())
                .template(EmailTemplate.USER_VERIFICATION)
                .bodyContext("verificationUrl", appUrlUtil.getAppUrl(String.format("/verify/%s", event.getToken())))
                .build();

        commandGateway.send(emailCreateCommand);
    }
}
