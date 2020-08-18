package com.example.demo.sample.util;

import com.example.demo.email.entity.EmailTemplate;
import com.example.demo.email.service.model.EmailCreateRequest;

public class TestEmailCreateRequest {

    public enum Type {
        WELCOME_EMAIL
    }

    public static Builder builder() {

        return new Builder();
    }

    public static Builder builder(Type type) {

        return new Builder(type);
    }

    public static EmailCreateRequest createDefault() {

        return builder().build();
    }

    public static class Builder {

        private final EmailCreateRequest.Builder builder;

        public Builder() {

            this.builder = getByType(Type.WELCOME_EMAIL);
        }

        public Builder(Type type) {

            this.builder = getByType(type);
        }

        public EmailCreateRequest build() {

            return builder.build();
        }
    }

    private static EmailCreateRequest.Builder getByType(Type type) {

        switch (type) {
            default:
                return buildWelcomeEmail();
        }
    }

    private static EmailCreateRequest.Builder buildWelcomeEmail() {

        return EmailCreateRequest.builder()
                .toAddress("test@test")
                .template(EmailTemplate.WELCOME)
                .bodyContext("context", "test context")
                .subjectContext("test context");
    }
}
