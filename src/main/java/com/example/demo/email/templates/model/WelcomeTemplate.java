package com.example.demo.email.templates.model;

import com.example.demo.email.entity.EmailTemplate;
import com.example.demo.email.templates.IEmailTemplate;
import com.example.demo.framework.properties.AppConfig;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class WelcomeTemplate implements IEmailTemplate {

    private final AppConfig appConfig;

    @Override
    public String path() {

        return "email/welcome";
    }

    @Override
    public String fromAddress() {

        return appConfig.getEmail().getNoReplyAddress();
    }

    @Override
    public String subject() {

        return "email.welcome.subject";
    }

    @Override
    public EmailTemplate template() {

        return EmailTemplate.WELCOME;
    }
}
