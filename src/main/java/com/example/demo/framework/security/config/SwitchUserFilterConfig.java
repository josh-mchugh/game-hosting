package com.example.demo.framework.security.config;

import com.example.demo.framework.security.UserDetailsServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.switchuser.SwitchUserFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.util.UrlPathHelper;

@Configuration
@RequiredArgsConstructor
public class SwitchUserFilterConfig {

    private final UserDetailsServiceImpl userDetailsService;

    @Bean
    public SwitchUserFilter switchUserFilter() {

        SwitchUserFilter filter = new SwitchUserFilter();

        filter.setUserDetailsService(userDetailsService);
        filter.setSwitchUserMatcher(new AntPathRequestMatcher("/switch_user", "GET", true, new UrlPathHelper()));
        filter.setUsernameParameter("username");
        filter.setExitUserMatcher(new AntPathRequestMatcher("/switch_user_exit", "GET", true, new UrlPathHelper()));

        filter.setSuccessHandler(authenticationSuccessHandler());

        filter.setSwitchFailureUrl("/admin/users");

        return filter;
    }

    private AuthenticationSuccessHandler authenticationSuccessHandler() {

        return (request, response, authentication) -> {

            boolean isImpersonating = authentication.getAuthorities().stream()
                    .map(GrantedAuthority::getAuthority)
                    .anyMatch(SwitchUserFilter.ROLE_PREVIOUS_ADMINISTRATOR::equals);

            if (isImpersonating) {

                response.sendRedirect("/dashboard");

            } else {

                response.sendRedirect("/admin/users");
            }
        };
    }
}
