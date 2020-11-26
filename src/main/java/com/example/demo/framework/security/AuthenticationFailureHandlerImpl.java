package com.example.demo.framework.security;

import com.example.demo.user.aggregate.command.UserAuthFailCommand;
import com.example.demo.user.projection.IUserProjector;
import com.example.demo.user.projection.model.FetchUserIdByEmailProjection;
import com.example.demo.user.projection.model.FetchUserIdByEmailQuery;
import lombok.RequiredArgsConstructor;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.context.event.EventListener;
import org.springframework.security.authentication.event.AuthenticationFailureBadCredentialsEvent;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class AuthenticationFailureHandlerImpl {

    private final IUserProjector userProjector;
    private final CommandGateway commandGateway;

    @EventListener
    public void handleAuthenticationFailed(AuthenticationFailureBadCredentialsEvent event) {

        if(userProjector.existsByEmail(event.getAuthentication().getName())) {

            FetchUserIdByEmailQuery query = new FetchUserIdByEmailQuery(event.getAuthentication().getName());
            FetchUserIdByEmailProjection projection = userProjector.fetchUserIdByEmail(query);

            commandGateway.send(new UserAuthFailCommand(UUID.fromString(projection.getId())));
        }
    }
}
