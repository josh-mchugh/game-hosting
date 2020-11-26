package com.example.demo.email.templates.model;

import com.example.demo.email.entity.EmailTemplate;
import com.example.demo.email.templates.IEmailTemplate;
import com.example.demo.framework.properties.AppConfig;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserVerificationTemplate implements IEmailTemplate {

    private final AppConfig appConfig;

    @Override
    public String fromAddress() {

        return appConfig.getEmail().getNoReplyAddress();
    }

    @Override
    public String path() {

        return "email/user-verification";
    }

    @Override
    public String subject() {

        return "email.user.verification.subject";
    }

    @Override
    public EmailTemplate template() {

        return EmailTemplate.USER_VERIFICATION;
    }
}
