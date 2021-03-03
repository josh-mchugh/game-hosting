package com.example.demo.framework.security.user.projection.projection;

import com.example.demo.user.entity.UserType;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Value;

@Value
public class UserDetailsProjection {

    String email;
    String password;
    String type;

    @QueryProjection
    public UserDetailsProjection(String email, String password, UserType type) {

        this.email = email;
        this.password = password;
        this.type = type != null ? type.name() : null;
    }
}
