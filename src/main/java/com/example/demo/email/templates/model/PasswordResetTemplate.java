package com.example.demo.email.templates.model;

import com.example.demo.email.entity.EmailTemplateType;
import com.example.demo.email.templates.EmailTemplate;
import com.example.demo.framework.properties.AppConfig;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PasswordResetTemplate implements EmailTemplate {

    private final AppConfig appConfig;

    @Override
    public String fromAddress() {

        return appConfig.getEmail().getSupportAddress();
    }

    @Override
    public String path() {

        return "email/password-reset-complete";
    }

    @Override
    public String subject() {

        return "email.password.reset.subject";
    }

    @Override
    public EmailTemplateType type() {

        return EmailTemplateType.PASSWORD_RESET;
    }
}
