package com.example.demo.email.templates;

import com.example.demo.email.entity.EmailTemplate;
import com.example.demo.framework.properties.AppConfig;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PasswordResetTemplate implements IEmailTemplate {

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
    public EmailTemplate template() {

        return EmailTemplate.PASSWORD_RESET;
    }
}
