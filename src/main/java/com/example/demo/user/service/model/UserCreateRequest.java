package com.example.demo.user.service.model;

import com.example.demo.user.entity.UserState;
import com.example.demo.user.entity.UserType;
import lombok.Builder;
import lombok.Value;

@Value
@Builder(builderClassName = "Builder")
public class UserCreateRequest {

    String email;
    String password;
    UserState state;
    UserType type;
}
