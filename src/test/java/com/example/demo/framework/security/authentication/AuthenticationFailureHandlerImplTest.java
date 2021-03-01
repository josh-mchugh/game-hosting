package com.example.demo.framework.security.authentication;

import com.example.demo.framework.security.authentication.service.model.ExistsUserByEmailQuery;
import com.example.demo.framework.security.authentication.service.model.ExistsUserByEmailResponse;
import com.example.demo.framework.security.authentication.service.model.FetchAuthFailureByEmailQuery;
import com.example.demo.framework.security.authentication.service.model.FetchAuthFailureByEmailResponse;
import com.example.demo.user.aggregate.command.UserAuthFailCommand;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.queryhandling.QueryGateway;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.security.authentication.event.AuthenticationFailureBadCredentialsEvent;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.session.SessionAuthenticationException;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class AuthenticationFailureHandlerImplTest {

    @Test
    public void whenAuthenticationFailAndUserExistsThenHandleAuthenticationFailed() throws ExecutionException, InterruptedException {

        QueryGateway queryGateway = Mockito.mock(QueryGateway.class);
        CommandGateway commandGateway = Mockito.mock(CommandGateway.class);

        Mockito.when(queryGateway.query(new ExistsUserByEmailQuery("test"), ExistsUserByEmailResponse.class))
                .thenReturn(CompletableFuture.completedFuture(new ExistsUserByEmailResponse(true)));
        Mockito.when(queryGateway.query(new FetchAuthFailureByEmailQuery("test"), FetchAuthFailureByEmailResponse.class))
                .thenReturn(CompletableFuture.completedFuture(new FetchAuthFailureByEmailResponse(UUID.randomUUID())));

        Authentication authentication = Mockito.mock(Authentication.class);
        Mockito.when(authentication.getName()).thenReturn("test");

        AuthenticationFailureBadCredentialsEvent event = new AuthenticationFailureBadCredentialsEvent(authentication, new SessionAuthenticationException(""));

        AuthenticationFailureHandlerImpl handler = new AuthenticationFailureHandlerImpl(queryGateway, commandGateway);
        handler.handleAuthenticationFailed(event);

        Mockito.verify(commandGateway, Mockito.times(1)).send(Mockito.any(UserAuthFailCommand.class));
    }

    @Test
    public void whenAuthenticationFailAndUserDoesNotExistsThenHandleAuthenticationFailed() throws ExecutionException, InterruptedException {

        QueryGateway queryGateway = Mockito.mock(QueryGateway.class);
        CommandGateway commandGateway = Mockito.mock(CommandGateway.class);

        Mockito.when(queryGateway.query(new ExistsUserByEmailQuery("test"), ExistsUserByEmailResponse.class))
                .thenReturn(CompletableFuture.completedFuture(new ExistsUserByEmailResponse(false)));

        Authentication authentication = Mockito.mock(Authentication.class);
        Mockito.when(authentication.getName()).thenReturn("test");

        AuthenticationFailureBadCredentialsEvent event = new AuthenticationFailureBadCredentialsEvent(authentication, new SessionAuthenticationException(""));

        AuthenticationFailureHandlerImpl handler = new AuthenticationFailureHandlerImpl(queryGateway, commandGateway);
        handler.handleAuthenticationFailed(event);

        Mockito.verify(commandGateway, Mockito.times(0)).send(Mockito.any());
    }
}
