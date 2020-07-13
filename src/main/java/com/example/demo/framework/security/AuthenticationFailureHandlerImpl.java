package com.example.demo.framework.security;

import com.example.demo.user.service.IUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.security.authentication.event.AuthenticationFailureBadCredentialsEvent;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AuthenticationFailureHandlerImpl {

    private final IUserService userService;

    @EventListener
    public void handleAuthenticationFailed(AuthenticationFailureBadCredentialsEvent event) {

        if(userService.existsUserByEmail(event.getAuthentication().getName())) {

            userService.handleAuthenticationFailure(event.getAuthentication().getName());
        }
    }
}
