package com.example.demo.framework.security;

import com.example.demo.user.aggregate.command.UserAuthSuccessCommand;
import com.example.demo.user.projection.IUserProjector;
import com.example.demo.user.projection.model.FetchUserIdByEmailProjection;
import com.example.demo.user.projection.model.FetchUserIdByEmailQuery;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;
import java.util.UUID;

public class AuthenticationSuccessHandlerImplTest {

    @Test
    public void whenAuthenticationSuccessThenExpectAuthSuccessCommand() throws IOException, ServletException {

        CommandGateway commandGateway = Mockito.mock(CommandGateway.class);

        FetchUserIdByEmailProjection projection = new FetchUserIdByEmailProjection(UUID.randomUUID().toString());

        IUserProjector userProjector = Mockito.mock(IUserProjector.class);
        Mockito.when(userProjector.fetchUserIdByEmail(Mockito.any(FetchUserIdByEmailQuery.class))).thenReturn(projection);

        HttpServletRequest httpServletRequest = Mockito.mock(HttpServletRequest.class);
        HttpServletResponse httpServletResponse = Mockito.mock(HttpServletResponse.class);

        Authentication authentication = Mockito.mock(Authentication.class);

        AuthenticationSuccessHandlerImpl handler = new AuthenticationSuccessHandlerImpl(userProjector, commandGateway);
        handler.onAuthenticationSuccess(httpServletRequest, httpServletResponse, authentication);

        Mockito.verify(commandGateway, Mockito.timeout(1)).send(Mockito.any(UserAuthSuccessCommand.class));
    }

    @Test
    public void whenAuthenticationSuccessAndAdminUserThenExpectRedirectToAdminDashboard() throws IOException, ServletException {

        CommandGateway commandGateway = Mockito.mock(CommandGateway.class);

        FetchUserIdByEmailProjection projection = new FetchUserIdByEmailProjection(UUID.randomUUID().toString());

        IUserProjector userProjector = Mockito.mock(IUserProjector.class);
        Mockito.when(userProjector.fetchUserIdByEmail(Mockito.any(FetchUserIdByEmailQuery.class))).thenReturn(projection);

        HttpServletRequest httpServletRequest = Mockito.mock(HttpServletRequest.class);
        HttpServletResponse httpServletResponse = Mockito.mock(HttpServletResponse.class);

        Authentication authentication = Mockito.mock(Authentication.class);
        Mockito.when(authentication.getAuthorities()).thenAnswer(invocationOnMock -> Collections.singletonList(new SimpleGrantedAuthority("ROLE_ADMIN")));

        AuthenticationSuccessHandlerImpl handler = new AuthenticationSuccessHandlerImpl(userProjector, commandGateway);
        handler.onAuthenticationSuccess(httpServletRequest, httpServletResponse, authentication);

        Mockito.verify(httpServletResponse, Mockito.times(1)).sendRedirect("/admin/dashboard");
    }

    @Test
    public void whenAuthenticationSuccessAndUserThenExpectRedirectToDashboard() throws IOException, ServletException {

        CommandGateway commandGateway = Mockito.mock(CommandGateway.class);

        FetchUserIdByEmailProjection projection = new FetchUserIdByEmailProjection(UUID.randomUUID().toString());

        IUserProjector userProjector = Mockito.mock(IUserProjector.class);
        Mockito.when(userProjector.fetchUserIdByEmail(Mockito.any(FetchUserIdByEmailQuery.class))).thenReturn(projection);

        HttpServletRequest httpServletRequest = Mockito.mock(HttpServletRequest.class);
        HttpServletResponse httpServletResponse = Mockito.mock(HttpServletResponse.class);

        Authentication authentication = Mockito.mock(Authentication.class);

        AuthenticationSuccessHandlerImpl handler = new AuthenticationSuccessHandlerImpl(userProjector, commandGateway);
        handler.onAuthenticationSuccess(httpServletRequest, httpServletResponse, authentication);

        Mockito.verify(httpServletResponse, Mockito.times(1)).sendRedirect("/dashboard");
    }
}
