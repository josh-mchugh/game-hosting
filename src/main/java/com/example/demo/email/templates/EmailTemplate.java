package com.example.demo.email.templates;

import com.example.demo.email.entity.EmailTemplateType;

public interface EmailTemplate {

    String fromAddress();

    String path();

    String subject();

    EmailTemplateType type();
}
