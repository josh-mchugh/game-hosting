package com.example.demo.framework.security.session;

import com.example.demo.user.entity.UserType;
import com.example.demo.user.entity.model.User;
import com.example.demo.user.projection.IUserProjector;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.switchuser.SwitchUserFilter;
import org.springframework.stereotype.Component;

@Component(value = "sessionUtil")
@RequiredArgsConstructor
public class SessionUtil implements ISessionUtil {

    private final IUserProjector userProjector;

    @Override
    public boolean isAuthenticated() {

        return !"anonymousUser".equals(SecurityContextHolder.getContext().getAuthentication().getPrincipal());
    }

    @Override
    public boolean isAdmin() {

        return SecurityContextHolder.getContext().getAuthentication().getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .anyMatch("ROLE_".concat(UserType.ADMIN.name())::equals);
    }

    @Override
    public boolean isImpersonating() {

        return SecurityContextHolder.getContext().getAuthentication().getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .anyMatch(SwitchUserFilter.ROLE_PREVIOUS_ADMINISTRATOR::equals);
    }

    @Override
    public String getCurrentUserEmail() {

        return ((UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername();
    }

    @Override
    public User getCurrentUser() {

        String email = SecurityContextHolder.getContext().getAuthentication().getName();

        return userProjector.getUserByEmail(email);
    }
}
