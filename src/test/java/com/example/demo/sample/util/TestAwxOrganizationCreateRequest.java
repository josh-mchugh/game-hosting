package com.example.demo.sample.util;

import com.example.demo.awx.organization.service.model.AwxOrganizationCreateRequest;

public class TestAwxOrganizationCreateRequest {

    public enum Type {
        DEFAULT
    }

    public static Builder builder() {

        return new Builder(Type.DEFAULT);
    }

    public static Builder builder(Type type) {

        return new Builder(type);
    }

    public static AwxOrganizationCreateRequest createDefault() {

        return builder().build();
    }

    public static class Builder {

        private final AwxOrganizationCreateRequest.Builder builder;

        public Builder(Type type) {

            this.builder = getByType(type);
        }

        public Builder organizationId(Long organizationId) {

            builder.organizationId(organizationId);

            return this;
        }

        public Builder name(String name) {

            builder.name(name);

            return this;
        }

        public Builder description(String name) {

            builder.description(name);

            return this;
        }

        public AwxOrganizationCreateRequest build() {

            return builder.build();
        }
    }

    private static AwxOrganizationCreateRequest.Builder getByType(Type type) {

        switch (type) {
            default:
                return buildDefault();
        }
    }

    private static AwxOrganizationCreateRequest.Builder buildDefault() {

        return AwxOrganizationCreateRequest.builder()
                .organizationId(1L)
                .name("Game Hosting Service")
                .description("organization description");
    }
}
