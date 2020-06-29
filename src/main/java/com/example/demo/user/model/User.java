package com.example.demo.user.model;

import com.example.demo.user.entity.UserState;
import com.example.demo.user.entity.UserType;
import lombok.Builder;
import lombok.Value;

import java.time.LocalDateTime;

@Value
@Builder(builderClassName = "Builder")
public class User {

    String id;
    String email;
    UserState state;
    UserType type;
    Long invalidLoginAttempts;
    LocalDateTime lastLoginDate;
}
