package com.example.demo.framework.security;

import com.example.demo.user.aggregate.command.UserAuthFailCommand;
import com.example.demo.user.projection.IUserProjector;
import com.example.demo.user.projection.model.FetchUserIdByEmailProjection;
import com.example.demo.user.projection.model.FetchUserIdByEmailQuery;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.security.authentication.event.AuthenticationFailureBadCredentialsEvent;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.session.SessionAuthenticationException;

import java.util.UUID;

public class AuthenticationFailureHandlerImplTest {

    @Test
    public void whenAuthenticationFailAndUserExistsThenHandleAuthenticationFailed() {

        CommandGateway commandGateway = Mockito.mock(CommandGateway.class);

        IUserProjector userProjector = Mockito.mock(IUserProjector.class);
        Mockito.when(userProjector.existsByEmail(Mockito.anyString())).thenReturn(true);
        Mockito.when(userProjector.fetchUserIdByEmail(Mockito.any(FetchUserIdByEmailQuery.class))).thenReturn(new FetchUserIdByEmailProjection(UUID.randomUUID().toString()));

        Authentication authentication = Mockito.mock(Authentication.class);
        Mockito.when(authentication.getName()).thenReturn("test");

        AuthenticationFailureBadCredentialsEvent event = new AuthenticationFailureBadCredentialsEvent(authentication, new SessionAuthenticationException(""));

        AuthenticationFailureHandlerImpl handler = new AuthenticationFailureHandlerImpl(userProjector, commandGateway);
        handler.handleAuthenticationFailed(event);

        Mockito.verify(commandGateway, Mockito.times(1)).send(Mockito.any(UserAuthFailCommand.class));
    }

    @Test
    public void whenAuthenticationFailAndUserDoesNotExistsThenHandleAuthenticationFailed() {

        CommandGateway commandGateway = Mockito.mock(CommandGateway.class);

        IUserProjector userProjector = Mockito.mock(IUserProjector.class);
        Mockito.when(userProjector.existsByEmail(Mockito.anyString())).thenReturn(false);

        Authentication authentication = Mockito.mock(Authentication.class);
        Mockito.when(authentication.getName()).thenReturn("test");

        AuthenticationFailureBadCredentialsEvent event = new AuthenticationFailureBadCredentialsEvent(authentication, new SessionAuthenticationException(""));

        AuthenticationFailureHandlerImpl handler = new AuthenticationFailureHandlerImpl(userProjector, commandGateway);
        handler.handleAuthenticationFailed(event);

        Mockito.verify(commandGateway, Mockito.times(0)).send(Mockito.any());
    }
}
