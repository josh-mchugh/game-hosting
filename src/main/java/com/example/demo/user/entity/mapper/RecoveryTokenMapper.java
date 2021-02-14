package com.example.demo.user.entity.mapper;

import com.example.demo.user.entity.RecoveryTokenEntity;
import com.example.demo.user.entity.model.RecoveryToken;

public class RecoveryTokenMapper {

    public static RecoveryToken map(RecoveryTokenEntity entity) {

        if(entity == null) {

            return null;
        }

        return RecoveryToken.builder()
                .id(entity.getUUID())
                .token(entity.getToken())
                .sentDate(entity.getSentDate())
                .expirationDate(entity.getExpirationDate())
                .build();
    }
}
