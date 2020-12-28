package com.example.demo.web.password.forgot.command.service;

import com.example.demo.user.aggregate.command.UserRecoveryTokenCreateCommand;
import com.example.demo.user.projection.IUserProjector;
import com.example.demo.user.projection.model.FetchUserIdByEmailProjection;
import com.example.demo.user.projection.model.FetchUserIdByEmailQuery;
import lombok.RequiredArgsConstructor;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class ForgotPasswordCommandService implements IForgotPasswordCommandService {

    private final IUserProjector userProjector;
    private final CommandGateway commandGateway;

    @Override
    public void handleForgotPassword(String emailAddress) {

        if(userProjector.existsByEmail(emailAddress)) {

            FetchUserIdByEmailQuery query = new FetchUserIdByEmailQuery(emailAddress);
            FetchUserIdByEmailProjection projection = userProjector.fetchUserIdByEmail(query);

            commandGateway.send(new UserRecoveryTokenCreateCommand(UUID.fromString(projection.getId())));
        }
    }
}
