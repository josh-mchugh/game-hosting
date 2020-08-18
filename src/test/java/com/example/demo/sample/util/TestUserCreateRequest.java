package com.example.demo.sample.util;

import com.example.demo.user.entity.UserState;
import com.example.demo.user.entity.UserType;
import com.example.demo.user.service.model.UserCreateRequest;

public class TestUserCreateRequest {

    public enum Type {
        REGULAR,
        ADMIN
    }

    public static Builder builder() {

        return new Builder();
    }

    public static Builder builder(Type type) {

        return new Builder(type);
    }

    public static UserCreateRequest createDefault() {

        return buildRegularUser().build();
    }

    public static UserCreateRequest createDefaultAdmin() {

        return buildAdminUser().build();
    }

    public static class Builder {

        private final UserCreateRequest.Builder builder;

        public Builder() {

            this.builder = getByType(Type.REGULAR);
        }

        public Builder(Type type) {

            this.builder = getByType(type);
        }

        public Builder email(String email) {

            builder.email(email);

            return this;
        }

        public Builder password(String password) {

            builder.password(password);

            return this;
        }

        public UserCreateRequest build() {

            return this.builder.build();
        }
    }

    private static UserCreateRequest.Builder getByType(Type type) {

        switch (type) {
            case ADMIN:
                return buildAdminUser();
            default:
                return buildRegularUser();
        }
    }

    private static UserCreateRequest.Builder buildRegularUser() {

        return UserCreateRequest.builder()
                .email("test@test")
                .password("password")
                .type(UserType.REGULAR)
                .state(UserState.ACTIVE);
    }

    private static UserCreateRequest.Builder buildAdminUser() {

        return UserCreateRequest.builder()
                .email("admin@admin")
                .password("password")
                .type(UserType.ADMIN)
                .state(UserState.ACTIVE);
    }
}
