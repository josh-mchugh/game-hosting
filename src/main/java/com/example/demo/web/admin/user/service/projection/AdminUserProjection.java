package com.example.demo.web.admin.user.service.projection;

import com.example.demo.user.entity.UserState;
import com.example.demo.user.entity.UserType;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Value;

@Value
public class AdminUserProjection {

    String email;
    UserState state;
    UserType type;
    Long projectCount;

    @QueryProjection
    public AdminUserProjection(String email, UserState state, UserType type, Long projectCount) {

        this.email = email;
        this.state = state;
        this.type = type;
        this.projectCount = projectCount;
    }

    public boolean isAdmin() {

        return UserType.ADMIN.equals(type);
    }
}
