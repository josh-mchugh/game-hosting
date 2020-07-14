package com.example.demo.user.mapper;

import com.example.demo.user.entity.VerificationEntity;
import com.example.demo.user.model.Verification;

public class VerificationMapper {

    public static Verification map(VerificationEntity entity) {

        if(entity == null) {

            return null;
        }

        return Verification.builder()
                .id(entity.getId())
                .status(entity.getStatus())
                .token(entity.getToken())
                .sentDate(entity.getSentDate())
                .verificationDate(entity.getVerificationDate())
                .build();
    }
}
