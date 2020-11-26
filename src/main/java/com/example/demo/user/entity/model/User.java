package com.example.demo.user.entity.model;

import com.example.demo.user.entity.UserState;
import com.example.demo.user.entity.UserType;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Value;

import java.time.LocalDateTime;

@Value
@Builder(builderClassName = "Builder")
public class User {

    String id;
    String email;
    @JsonIgnore
    String password;
    UserState state;
    UserType type;
    Long invalidLoginAttempts;
    LocalDateTime lastLoginDate;
    RecoveryToken recoveryToken;
    Verification verification;
}
