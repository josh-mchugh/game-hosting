package com.example.demo.framework.security.authentication;

import com.example.demo.framework.security.authentication.service.model.FetchAuthSuccessByEmailQuery;
import com.example.demo.framework.security.authentication.service.model.FetchAuthSuccessByEmailResponse;
import com.example.demo.framework.security.authentication.service.projection.AuthSuccessProjection;
import com.example.demo.user.aggregate.command.UserAuthSuccessCommand;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.concurrent.ExecutionException;

@Slf4j
@Component
@RequiredArgsConstructor
public class AuthenticationSuccessHandlerImpl implements AuthenticationSuccessHandler {

    private final QueryGateway queryGateway;
    private final CommandGateway commandGateway;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {

        try {

            AuthSuccessProjection user = getUserProjectionByEmail(authentication.getName());

            commandGateway.send(new UserAuthSuccessCommand(user.getId()));

            if (user.isAdmin()) {

                response.sendRedirect("/admin/dashboard");

            } else {

                response.sendRedirect("/dashboard");
            }

        } catch (Exception e) {

            log.error("Unable to successfully authenticate user", e);
        }
    }

    private AuthSuccessProjection getUserProjectionByEmail(String email) throws ExecutionException, InterruptedException {

        FetchAuthSuccessByEmailQuery query = new FetchAuthSuccessByEmailQuery(email);
        FetchAuthSuccessByEmailResponse response = queryGateway.query(query, FetchAuthSuccessByEmailResponse.class).get();

        return response.getUser();
    }
}
