package com.example.demo.recovery.mapper;

import com.example.demo.recovery.entity.RecoveryTokenEntity;
import com.example.demo.recovery.model.RecoveryToken;

public class RecoveryTokenMapper {

    public static RecoveryToken map(RecoveryTokenEntity entity) {

        if(entity == null) {

            return null;
        }

        return RecoveryToken.builder()
                .id(entity.getId())
                .token(entity.getToken())
                .sentDate(entity.getSentDate())
                .expirationDate(entity.getExpirationDate())
                .build();
    }
}
