package com.example.demo.ovh.credential.entity.mapper;

import com.example.demo.ovh.credential.entity.CredentialEntity;
import com.example.demo.ovh.credential.entity.model.Credential;

public class CredentialMapper {

    public static Credential map(CredentialEntity entity) {

            if (entity == null) {

            return null;
        }

        return Credential.builder()
                .id(entity.getUUID())
                .sshKeyId(entity.getOvhId())
                .name(entity.getName())
                .publicKey(entity.getPublicKey())
                .type(entity.getType())
                .build();
    }
}
