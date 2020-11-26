package com.example.demo.user.scheduler.service;

import com.example.demo.user.aggregate.command.UserRecoveryTokenDeleteCommand;
import com.example.demo.user.entity.model.User;
import com.example.demo.user.projection.IUserProjector;
import com.google.common.collect.ImmutableList;
import lombok.RequiredArgsConstructor;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class RecoveryTokenSchedulerService implements IRecoveryTokenSchedulerService {

    private final IUserProjector userProjector;
    private final CommandGateway commandGateway;

    @Override
    public ImmutableList<Object> processExpiredRecoveryTokens() {

        List<Object> processedUsers = new ArrayList<>();

        PageRequest pageRequest = PageRequest.of(0, 20);
        Page<User> page = userProjector.getByRecoveryTokensExpired(pageRequest);

        while(page.hasContent()) {

            List<Object> users = page.stream()
                    .map(User::getId)
                    .map(this::recoveryTokenDeleteCommand)
                    .map(commandGateway::sendAndWait)
                    .collect(Collectors.toList());

            processedUsers.addAll(users);

            page = page.hasNext() ? userProjector.getByRecoveryTokensExpired(page.nextPageable()) : Page.empty();
        }

        return ImmutableList.copyOf(processedUsers);
    }

    private UserRecoveryTokenDeleteCommand recoveryTokenDeleteCommand(String id) {

        return new UserRecoveryTokenDeleteCommand(UUID.fromString(id));
    }
}
