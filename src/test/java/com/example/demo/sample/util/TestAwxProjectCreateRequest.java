package com.example.demo.sample.util;

import com.example.demo.awx.project.service.model.AwxProjectCreateRequest;

public class TestAwxProjectCreateRequest {

    public enum Type {
        GITLAB
    }

    public static Builder builder() {

        return new Builder(Type.GITLAB);
    }

    public static Builder builder(Type type) {

        return new Builder(type);
    }

    public static AwxProjectCreateRequest createDefault() {

        return builder().build();
    }

    public static class Builder {

        private final AwxProjectCreateRequest.Builder builder;

        public Builder(Type type) {

            builder = getByType(type);
        }

        public Builder awxOrganizationId(Long id) {

            builder.organizationId(id);

            return this;
        }

        public Builder awxCredentialId(Long id) {

            builder.credentialId(id);

            return this;
        }

        public Builder projectId(Long id) {

            builder.projectId(id);

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

        public Builder scmType(String scmType) {

            builder.scmType(scmType);

            return this;
        }

        public Builder scmUrl(String scmUrl) {

            builder.scmUrl(scmUrl);

            return this;
        }

        public Builder scmBranch(String scmBranch) {

            builder.scmBranch(scmBranch);

            return this;
        }

        public AwxProjectCreateRequest build() {

            return builder.build();
        }
    }

    private static AwxProjectCreateRequest.Builder getByType(Type type) {

        switch (type) {
            default:
                return buildDefaultType();
        }
    }

    private static AwxProjectCreateRequest.Builder buildDefaultType() {

        return AwxProjectCreateRequest.builder()
                .projectId(1L)
                .organizationId(1L)
                .credentialId(1L)
                .name("Game Hosting Project")
                .description("Game Hosting Project")
                .scmType("git")
                .scmBranch("master")
                .scmUrl("url");
    }
}
