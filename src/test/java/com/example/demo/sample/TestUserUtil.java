package com.example.demo.sample;

import com.example.demo.user.entity.UserState;
import com.example.demo.user.entity.UserType;
import com.example.demo.user.service.model.UserCreateRequest;

public class TestUserUtil {

    public static UserCreateRequest createUser(String email) {

        return UserCreateRequest.builder()
                .email(email)
                .password("Password1!")
                .type(UserType.REGULAR)
                .state(UserState.ACTIVE)
                .build();
    }

    public static UserCreateRequest createAdminUser(String email) {

        return UserCreateRequest.builder()
                .email(email)
                .password("Password1!")
                .type(UserType.ADMIN)
                .state(UserState.ACTIVE)
                .build();
    }
}
