package com.example.demo.awx.credential.entity.mapper;

import com.example.demo.awx.credential.entity.AwxCredentialEntity;
import com.example.demo.awx.credential.entity.model.AwxCredential;

public class AwxCredentialMapper {

    public static AwxCredential map(AwxCredentialEntity entity) {

        if (entity == null) {

            return null;
        }

        return AwxCredential.builder()
                .id(entity.getId())
                .awxId(entity.getAwxId())
                .name(entity.getName())
                .description(entity.getDescription())
                .privateKey(entity.getPrivateKey())
                .passphrase(entity.getPassphrase())
                .type(entity.getType())
                .build();
    }
}
