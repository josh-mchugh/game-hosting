package com.example.demo.user.mapper;

import com.example.demo.user.entity.UserEntity;
import com.example.demo.user.model.User;

public class UserMapper {

    public static User map(UserEntity entity) {

        return User.builder()
                .id(entity.getId())
                .email(entity.getEmail())
                .state(entity.getState())
                .type(entity.getType())
                .invalidLoginAttempts(entity.getInvalidLoginAttempts())
                .lastLoginDate(entity.getLastLoginDate())
                .build();
    }
}
