package com.example.demo.framework.seed.user;

import com.example.demo.framework.properties.AppConfig;
import com.example.demo.framework.seed.ISeedService;
import com.example.demo.framework.seed.user.projection.model.ExistsUserByEmailQuery;
import com.example.demo.framework.seed.user.projection.model.ExistsUserByEmailResponse;
import com.example.demo.user.aggregate.command.UserCreateAdminCommand;
import com.google.common.collect.ImmutableList;
import lombok.RequiredArgsConstructor;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.stereotype.Component;

import java.util.UUID;
import java.util.concurrent.ExecutionException;
import java.util.stream.Stream;

@Component
@RequiredArgsConstructor
public class UserSeedService implements ISeedService<Object> {

    private final AppConfig appConfig;
    private final QueryGateway queryGateway;
    private final CommandGateway commandGateway;

    @Override
    public boolean dataNotExists() throws ExecutionException, InterruptedException {

        ExistsUserByEmailQuery query = new ExistsUserByEmailQuery(appConfig.getAdminUser().getUsername());
        ExistsUserByEmailResponse response = queryGateway.query(query, ExistsUserByEmailResponse.class).get();

        return !response.exists();
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
