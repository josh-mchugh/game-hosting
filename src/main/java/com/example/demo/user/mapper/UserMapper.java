package com.example.demo.user.mapper;

import com.example.demo.recovery.mapper.RecoveryTokenMapper;
import com.example.demo.user.entity.UserEntity;
import com.example.demo.user.model.User;

public class UserMapper {

    public static User map(UserEntity entity) {

        if (entity == null) {

            return null;
        }

        return User.builder()
                .id(entity.getId())
                .email(entity.getEmail())
                .password(entity.getPassword())
                .state(entity.getState())
                .type(entity.getType())
                .invalidLoginAttempts(entity.getInvalidLoginAttempts())
                .lastLoginDate(entity.getLastLoginDate())
                .recoveryToken(RecoveryTokenMapper.map(entity.getRecoveryTokenEntity()))
                .build();
    }
}
