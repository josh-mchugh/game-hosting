package com.example.demo.framework.security.authentication;

import com.example.demo.framework.security.authentication.service.model.ExistsUserByEmailQuery;
import com.example.demo.framework.security.authentication.service.model.ExistsUserByEmailResponse;
import com.example.demo.framework.security.authentication.service.model.FetchAuthFailureByEmailQuery;
import com.example.demo.framework.security.authentication.service.model.FetchAuthFailureByEmailResponse;
import com.example.demo.user.aggregate.command.UserAuthFailCommand;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.context.event.EventListener;
import org.springframework.security.authentication.event.AuthenticationFailureBadCredentialsEvent;
import org.springframework.stereotype.Component;

import java.util.UUID;
import java.util.concurrent.ExecutionException;

@Slf4j
@Component
@RequiredArgsConstructor
public class AuthenticationFailureHandlerImpl {

    private final QueryGateway queryGateway;
    private final CommandGateway commandGateway;

    @EventListener
    public void handleAuthenticationFailed(AuthenticationFailureBadCredentialsEvent event) throws ExecutionException, InterruptedException {

        if (existsByEmail(event.getAuthentication().getName())) {

            UUID userId = fetchUserIdByEmail(event.getAuthentication().getName());
            commandGateway.send(new UserAuthFailCommand(userId));
        }
    }

    private boolean existsByEmail(String email) throws ExecutionException, InterruptedException {

        ExistsUserByEmailQuery query = new ExistsUserByEmailQuery(email);
        ExistsUserByEmailResponse response = queryGateway.query(query, ExistsUserByEmailResponse.class).get();

        return response.exists();
    }

    private UUID fetchUserIdByEmail(String email) throws ExecutionException, InterruptedException {

        FetchAuthFailureByEmailQuery query = new FetchAuthFailureByEmailQuery(email);
        FetchAuthFailureByEmailResponse response = queryGateway.query(query, FetchAuthFailureByEmailResponse.class).get();

        return response.getId();
    }
}
