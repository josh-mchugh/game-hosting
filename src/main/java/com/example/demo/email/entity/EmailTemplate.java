package com.example.demo.email.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum EmailTemplate {

    TEST("email/test", "Test Subject");

    private final String template;
    private final String subject;
}
