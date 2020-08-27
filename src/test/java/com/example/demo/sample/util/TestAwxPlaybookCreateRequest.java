package com.example.demo.sample.util;

import com.example.demo.awx.playbook.service.model.AwxPlaybookCreateRequest;

public class TestAwxPlaybookCreateRequest {

    public enum Type {
        COWSAY
    }

    public static Builder builder() {

        return new Builder(Type.COWSAY);
    }

    public static Builder builder(Type type) {

        return new Builder(type);
    }

    public static AwxPlaybookCreateRequest createDefault() {

        return new Builder(Type.COWSAY).build();
    }

    public static class Builder {

        private final AwxPlaybookCreateRequest.Builder builder;

        public Builder(Type type) {

            this.builder = getByType(type);
        }

        public Builder name(String name) {

            builder.name(name);

            return this;
        }

        public Builder projectId(Long projectID) {

            builder.projectId(projectID);

            return this;
        }

        public AwxPlaybookCreateRequest build() {

            return builder.build();
        }
    }

    private static AwxPlaybookCreateRequest.Builder getByType(Type type) {
        switch (type) {
            default:
                return buildDefault();
        }
    }

    private static AwxPlaybookCreateRequest.Builder buildDefault() {

        return AwxPlaybookCreateRequest.builder()
                .projectId(1L)
                .name("cowsay-playbook.yml");
    }
}
