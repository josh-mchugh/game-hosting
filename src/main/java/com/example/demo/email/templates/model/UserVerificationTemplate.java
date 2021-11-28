package com.example.demo.email.templates.model;

import com.example.demo.email.entity.EmailTemplateType;
import com.example.demo.email.templates.EmailTemplate;
import com.example.demo.framework.properties.AppConfig;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserVerificationTemplate implements EmailTemplate {

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
    public EmailTemplateType type() {

        return EmailTemplateType.USER_VERIFICATION;
    }
}
