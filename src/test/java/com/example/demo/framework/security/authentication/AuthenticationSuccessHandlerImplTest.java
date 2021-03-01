package com.example.demo.framework.security.authentication;

import com.example.demo.framework.security.authentication.service.model.FetchAuthSuccessByEmailQuery;
import com.example.demo.framework.security.authentication.service.model.FetchAuthSuccessByEmailResponse;
import com.example.demo.framework.security.authentication.service.projection.AuthSuccessProjection;
import com.example.demo.user.aggregate.command.UserAuthSuccessCommand;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.queryhandling.QueryGateway;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class AuthenticationSuccessHandlerImplTest {

    @Test
    public void whenAuthenticationSuccessThenExpectAuthSuccessCommand() throws IOException, ServletException {

        QueryGateway queryGateway = Mockito.mock(QueryGateway.class);
        CommandGateway commandGateway = Mockito.mock(CommandGateway.class);

        AuthSuccessProjection authProjection = new AuthSuccessProjection(UUID.randomUUID().toString(), false);
        Mockito.when(queryGateway.query(new FetchAuthSuccessByEmailQuery("test"), FetchAuthSuccessByEmailResponse.class))
                .thenReturn(CompletableFuture.completedFuture(new FetchAuthSuccessByEmailResponse(authProjection)));

        HttpServletRequest httpServletRequest = Mockito.mock(HttpServletRequest.class);
        HttpServletResponse httpServletResponse = Mockito.mock(HttpServletResponse.class);

        Authentication authentication = new UsernamePasswordAuthenticationToken("test", Collections.EMPTY_LIST);

        AuthenticationSuccessHandlerImpl handler = new AuthenticationSuccessHandlerImpl(queryGateway, commandGateway);
        handler.onAuthenticationSuccess(httpServletRequest, httpServletResponse, authentication);

        Mockito.verify(commandGateway, Mockito.timeout(1)).send(Mockito.any(UserAuthSuccessCommand.class));
    }

    @Test
    public void whenAuthenticationSuccessAndAdminUserThenExpectRedirectToAdminDashboard() throws IOException, ServletException {

        QueryGateway queryGateway = Mockito.mock(QueryGateway.class);
        CommandGateway commandGateway = Mockito.mock(CommandGateway.class);

        AuthSuccessProjection authProjection = new AuthSuccessProjection(UUID.randomUUID().toString(), true);
        Mockito.when(queryGateway.query(new FetchAuthSuccessByEmailQuery("test"), FetchAuthSuccessByEmailResponse.class))
                .thenReturn(CompletableFuture.completedFuture(new FetchAuthSuccessByEmailResponse(authProjection)));

        HttpServletRequest httpServletRequest = Mockito.mock(HttpServletRequest.class);
        HttpServletResponse httpServletResponse = Mockito.mock(HttpServletResponse.class);

        Authentication authentication = new UsernamePasswordAuthenticationToken("test", Collections.EMPTY_LIST);

        AuthenticationSuccessHandlerImpl handler = new AuthenticationSuccessHandlerImpl(queryGateway, commandGateway);
        handler.onAuthenticationSuccess(httpServletRequest, httpServletResponse, authentication);

        Mockito.verify(httpServletResponse, Mockito.times(1)).sendRedirect("/admin/dashboard");
    }

    @Test
    public void whenAuthenticationSuccessAndUserThenExpectRedirectToDashboard() throws IOException, ServletException {

        QueryGateway queryGateway = Mockito.mock(QueryGateway.class);
        CommandGateway commandGateway = Mockito.mock(CommandGateway.class);

        AuthSuccessProjection authProjection = new AuthSuccessProjection(UUID.randomUUID().toString(), false);
        Mockito.when(queryGateway.query(new FetchAuthSuccessByEmailQuery("test"), FetchAuthSuccessByEmailResponse.class))
                .thenReturn(CompletableFuture.completedFuture(new FetchAuthSuccessByEmailResponse(authProjection)));

        HttpServletRequest httpServletRequest = Mockito.mock(HttpServletRequest.class);
        HttpServletResponse httpServletResponse = Mockito.mock(HttpServletResponse.class);

        Authentication authentication = new UsernamePasswordAuthenticationToken("test", Collections.EMPTY_LIST);

        AuthenticationSuccessHandlerImpl handler = new AuthenticationSuccessHandlerImpl(queryGateway, commandGateway);
        handler.onAuthenticationSuccess(httpServletRequest, httpServletResponse, authentication);

        Mockito.verify(httpServletResponse, Mockito.times(1)).sendRedirect("/dashboard");
    }

    @Test
    public void whenAuthenticationThrowsExceptionThenExpectItCaught() throws ExecutionException, InterruptedException {

        QueryGateway queryGateway = Mockito.mock(QueryGateway.class);
        CommandGateway commandGateway = Mockito.mock(CommandGateway.class);
        CompletableFuture completableFuture = Mockito.mock(CompletableFuture.class);

        Mockito.when(queryGateway.query(new FetchAuthSuccessByEmailQuery("test"), FetchAuthSuccessByEmailResponse.class))
                .thenReturn(completableFuture);
        Mockito.when(completableFuture.get()).thenThrow(new ExecutionException(new Exception("boom")));

        HttpServletRequest httpServletRequest = Mockito.mock(HttpServletRequest.class);
        HttpServletResponse httpServletResponse = Mockito.mock(HttpServletResponse.class);

        Authentication authentication = new UsernamePasswordAuthenticationToken("test", Collections.EMPTY_LIST);

        AuthenticationSuccessHandlerImpl handler = new AuthenticationSuccessHandlerImpl(queryGateway, commandGateway);

        Assertions.assertDoesNotThrow(() -> handler.onAuthenticationSuccess(httpServletRequest, httpServletResponse, authentication));
    }
}
