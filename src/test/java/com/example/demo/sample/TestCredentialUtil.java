package com.example.demo.sample;

import com.example.demo.ovh.credential.entity.CredentialType;
import com.example.demo.ovh.credential.service.model.CredentialCreateRequest;

public class TestCredentialUtil {

    public enum Type {
        ANSIBLE
    }

    public static Builder builder() {

        return new Builder();
    }

    public static Builder builder(Type type) {

        return new Builder(type);
    }

    public static CredentialCreateRequest createDefault() {

        return new Builder().build();
    }

    public static class Builder {

        private final CredentialCreateRequest.Builder builder;

        public Builder() {

             builder = getByType(Type.ANSIBLE);
        }

        public Builder(Type type) {

            builder = getByType(type);
        }

        public Builder sshKeyId(String sshKeyId) {

            builder.sshKeyId(sshKeyId);

            return this;
        }

        public Builder name(String name) {

            builder.name(name);

            return this;
        }

        public Builder publicKey(String publicKey) {

            builder.publicKey(publicKey);

            return this;
        }

        public Builder privateKey(String privateKey) {

            builder.privateKey(privateKey);

            return this;
        }

        public Builder type(CredentialType type) {

            builder.type(type);

            return this;
        }

        public CredentialCreateRequest build() {

            return builder.build();
        }
    }

    private static CredentialCreateRequest.Builder getByType(Type type) {

        switch (type) {
            default:
                return buildAnsibleType();
        }
    }

    private static CredentialCreateRequest.Builder buildAnsibleType() {

        return CredentialCreateRequest.builder()
                .sshKeyId("ssh key id")
                .name("credential name")
                .privateKey("private key")
                .publicKey("public key")
                .type(CredentialType.ANSIBLE);
    }
}
