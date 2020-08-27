package com.example.demo.sample.util;

import com.example.demo.awx.credential.entity.AwxCredentialType;
import com.example.demo.awx.credential.service.model.AwxCredentialCreateRequest;
import com.example.demo.awx.organization.model.AwxOrganization;

public class TestAwxCredentialCreateRequest {

    public enum Type {
        ANSIBLE,
        GITLAB
    }

    public static Builder builder() {

        return new Builder(Type.ANSIBLE);
    }

    public static Builder builder(Type type) {

        return new Builder(type);
    }

    public static AwxCredentialCreateRequest createDefault(){

        return builder().build();
    }

    public static class Builder {

        private final AwxCredentialCreateRequest.Builder builder;

        public Builder(Type type) {

            builder = getByType(type);
        }

        public Builder credentialId(Long id) {

            builder.credentialId(id);

            return this;
        }

        public Builder awxOrganizationId(Long id) {

            builder.organizationId(id);

            return this;
        }

        public Builder awxOrganizationId(AwxOrganization awxOrganization) {

            builder.organizationId(awxOrganization.getOrganizationId());

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

        public Builder privateKey(String privateKey) {

            builder.privateKey(privateKey);

            return this;
        }

        public Builder passphrase(String passphrase) {

            builder.passphrase(passphrase);

            return this;
        }

        public Builder type(AwxCredentialType type) {

            builder.type(type);

            return this;
        }

        public AwxCredentialCreateRequest build() {

            return builder.build();
        }
    }

    private static AwxCredentialCreateRequest.Builder getByType(Type type) {

        switch (type) {
            case GITLAB:
                return buildGitlab();
            default:
                return buildDefaultType();
        }
    }

    private static AwxCredentialCreateRequest.Builder buildDefaultType() {

        return AwxCredentialCreateRequest.builder()
                .credentialId(1L)
                .organizationId(1L)
                .name("Ansible")
                .description("Ansible AWX Credentials")
                .privateKey("private key")
                .passphrase("passphrase")
                .type(AwxCredentialType.MACHINE);
    }

    private static AwxCredentialCreateRequest.Builder buildGitlab() {

        return AwxCredentialCreateRequest.builder()
                .credentialId(1L)
                .organizationId(1L)
                .name("Gitlab SCM")
                .description("Gitlab SCM AWX Credentials")
                .privateKey("private key")
                .passphrase("passphrase")
                .type(AwxCredentialType.SOURCE_CONTROL);
    }
}
