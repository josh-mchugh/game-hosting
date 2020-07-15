package com.example.demo.email.templates;

import com.example.demo.email.entity.EmailTemplate;

public interface IEmailTemplate {

    String fromAddress();

    String path();

    String subject();

    EmailTemplate template();
}
