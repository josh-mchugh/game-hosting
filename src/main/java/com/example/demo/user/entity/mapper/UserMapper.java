package com.example.demo.user.entity.mapper;

import com.example.demo.user.entity.UserEntity;
import com.example.demo.user.entity.model.User;
import com.google.common.collect.ImmutableList;
import org.apache.commons.collections4.CollectionUtils;

import java.util.Collection;

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
                .verification(VerificationMapper.map(entity.getVerificationEntity()))
                .build();
    }

    public static ImmutableList<User> map(Collection<UserEntity> entities) {

        if(CollectionUtils.isEmpty(entities)) {

            return ImmutableList.of();
        }

        return entities.stream()
                .map(UserMapper::map)
                .collect(ImmutableList.toImmutableList());
    }
}
