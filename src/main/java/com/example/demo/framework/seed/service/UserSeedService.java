package com.example.demo.framework.seed.service;

import com.example.demo.framework.properties.AppConfig;
import com.example.demo.framework.seed.ISeedService;
import com.example.demo.user.entity.UserState;
import com.example.demo.user.entity.UserType;
import com.example.demo.user.model.User;
import com.example.demo.user.service.IUserService;
import com.example.demo.user.service.model.UserCreateRequest;
import com.google.common.collect.ImmutableList;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.stream.Stream;

@Component
@RequiredArgsConstructor
public class UserSeedService implements ISeedService<User> {

    private final AppConfig appConfig;
    private final IUserService userService;

    @Override
    public boolean dataNotExists() {

        return !userService.existsUserByEmail(appConfig.getAdminUser().getUsername());
    }

    @Override
    public ImmutableList<User> initializeData() {

        return Stream.of(createAdminUser())
                .map(userService::handleCreateUser)
                .collect(ImmutableList.toImmutableList());
    }

    @Override
    public String type() {

        return "User";
    }

    @Override
    public Integer order() {

        return 1;
    }

    private UserCreateRequest createAdminUser() {

        return UserCreateRequest.builder()
                .email(appConfig.getAdminUser().getUsername())
                .password(appConfig.getAdminUser().getPassword())
                .state(UserState.ACTIVE)
                .type(UserType.ADMIN)
                .build();
    }
}
