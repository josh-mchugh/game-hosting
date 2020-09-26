package com.example.demo.sample.util;

import com.example.demo.awx.notification.service.model.AwxNotificationCreateRequest;
import com.example.demo.awx.organization.model.AwxOrganization;

public class TestAwxNotificationCreateRequest {

    public enum Type {
        PROJECT
    }

    public static Builder builder() {

        return new Builder(Type.PROJECT);
    }

    public static Builder builder(Type type) {

        return new Builder(type);
    }

    public static AwxNotificationCreateRequest createDefault() {

        return builder().build();
    }

    public static class Builder {

        private final AwxNotificationCreateRequest.Builder builder;

        public Builder(Type type) {

            builder = getByType(type);
        }

        public Builder awxOrganization(AwxOrganization awxOrganization) {

            builder.organizationId(awxOrganization.getOrganizationId());

            return this;
        }

        public Builder awxOrganizationId(Long id) {

            builder.organizationId(id);

            return this;
        }

        public Builder notificationId(Long id) {

            builder.notificationId(id);

            return this;
        }

        public Builder name(String name) {

            builder.name(name);

            return this;
        }

        public Builder description(String description) {

            builder.description(description);

            return this;
        }

        public Builder notificationType(String notificationType) {

            builder.notificationType(notificationType);

            return this;
        }

        public Builder webhookCallbackUrl(String url) {

            builder.webhookCallbackUrl(url);

            return this;
        }

        public AwxNotificationCreateRequest build() {

            return builder.build();
        }
    }

    private static AwxNotificationCreateRequest.Builder getByType(Type type) {

        switch (type) {
            default:
                return buildProjectNotification();
        }
    }

    private static AwxNotificationCreateRequest.Builder buildProjectNotification() {

        return AwxNotificationCreateRequest.builder()
                .notificationId(1L)
                .name("name")
                .description("description")
                .organizationId(1L)
                .notificationType("notification type")
                .webhookCallbackUrl("callback url");
    }
}
