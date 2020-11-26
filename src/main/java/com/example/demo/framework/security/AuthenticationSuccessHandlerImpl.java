package com.example.demo.framework.security;

import com.example.demo.user.aggregate.command.UserAuthSuccessCommand;
import com.example.demo.user.projection.IUserProjector;
import com.example.demo.user.projection.model.FetchUserIdByEmailProjection;
import com.example.demo.user.projection.model.FetchUserIdByEmailQuery;
import lombok.RequiredArgsConstructor;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class AuthenticationSuccessHandlerImpl implements AuthenticationSuccessHandler {

    private final IUserProjector userProjector;
    private final CommandGateway commandGateway;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {

        FetchUserIdByEmailQuery query = new FetchUserIdByEmailQuery(authentication.getName());
        FetchUserIdByEmailProjection projection = userProjector.fetchUserIdByEmail(query);

        commandGateway.send(new UserAuthSuccessCommand(UUID.fromString(projection.getId())));

        boolean isAdmin = authentication.getAuthorities().stream()
                .anyMatch(auth -> "ROLE_ADMIN".equals(auth.getAuthority()));

        if(isAdmin) {

            response.sendRedirect("/admin/dashboard");

        } else {

            response.sendRedirect("/dashboard");
        }
    }
}
