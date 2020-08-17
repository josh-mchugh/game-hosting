package com.example.demo.framework.security.session;

import com.example.demo.user.model.User;
import com.example.demo.user.service.IUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SessionUtil implements ISessionUtil {

    private final IUserService userService;

    @Override
    public boolean isAuthenticated() {

        return !SecurityContextHolder.getContext().getAuthentication().getPrincipal().equals("anonymousUser");
    }

    @Override
    public String getCurrentUserEmail() {

        return ((UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername();
    }

    public User getCurrentUser() {

        String email = SecurityContextHolder.getContext().getAuthentication().getName();

        return userService.getUserByEmail(email);
    }
}
