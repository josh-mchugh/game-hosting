package com.example.demo.framework.security.session;

import com.example.demo.framework.security.session.projection.model.FetchUserIdByEmailQuery;
import com.example.demo.framework.security.session.projection.model.FetchUserIdByEmailResponse;
import com.example.demo.user.entity.UserType;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.switchuser.SwitchUserFilter;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Slf4j
@Component(value = "sessionUtil")
@RequiredArgsConstructor
public class SessionUtil implements ISessionUtil {

    private final QueryGateway queryGateway;

    @Override
    public boolean isAuthenticated() {

        return !"anonymousUser".equals(SecurityContextHolder.getContext().getAuthentication().getPrincipal());
    }

    @Override
    public boolean isAdmin() {

        String role = "ROLE_".concat(UserType.ADMIN.name());

        return SecurityContextHolder.getContext().getAuthentication().getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .anyMatch(role::equals);
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
    public UUID getCurrentUserId() {

        try {

            String email = SecurityContextHolder.getContext().getAuthentication().getName();

            FetchUserIdByEmailQuery query = new FetchUserIdByEmailQuery(email);
            FetchUserIdByEmailResponse response = queryGateway.query(query, FetchUserIdByEmailResponse.class).get();

            return response.getId();

        } catch (Exception e) {

            log.info("Unable to retrieve current user id", e);
        }

        return null;
    }
}
