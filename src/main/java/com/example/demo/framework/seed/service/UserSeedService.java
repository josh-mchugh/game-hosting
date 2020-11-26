package com.example.demo.framework.seed.service;

import com.example.demo.framework.properties.AppConfig;
import com.example.demo.framework.seed.ISeedService;
import com.example.demo.user.aggregate.command.UserCreateAdminCommand;
import com.example.demo.user.projection.IUserProjector;
import com.google.common.collect.ImmutableList;
import lombok.RequiredArgsConstructor;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.stereotype.Component;

import java.util.UUID;
import java.util.stream.Stream;

@Component
@RequiredArgsConstructor
public class UserSeedService implements ISeedService<Object> {

    private final AppConfig appConfig;
    private final IUserProjector userProjector;
    private final CommandGateway commandGateway;

    @Override
    public boolean dataNotExists() {

        return !userProjector.existsByEmail(appConfig.getAdminUser().getUsername());
    }

    @Override
    public ImmutableList<Object> initializeData() {

        return Stream.of(createAdminUser())
                .map(commandGateway::sendAndWait)
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

    private Object createAdminUser() {

        return UserCreateAdminCommand.builder()
                .id(UUID.randomUUID())
                .email(appConfig.getAdminUser().getUsername())
                .password(appConfig.getAdminUser().getPassword())
                .build();
    }
}
