package com.example.demo.ovh.credential.mapper;

import com.example.demo.ovh.credential.entity.CredentialEntity;
import com.example.demo.ovh.credential.model.Credential;

public class CredentialMapper {

    public static Credential map(CredentialEntity entity) {

            if (entity == null) {

            return null;
        }

        return Credential.builder()
                .id(entity.getId())
                .sshKeyId(entity.getSshKeyId())
                .name(entity.getName())
                .publicKey(entity.getPublicKey())
                .type(entity.getType())
                .build();
    }
}
