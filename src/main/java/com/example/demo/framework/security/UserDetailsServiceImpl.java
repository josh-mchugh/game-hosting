package com.example.demo.framework.security;

import com.example.demo.user.entity.model.User;
import com.example.demo.user.projection.IUserProjector;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final IUserProjector userProjector;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        if (!userProjector.existsByEmail(email)) {

            throw new UsernameNotFoundException(String.format("Unable to find user with email: %s", email));
        }

        User user = userProjector.getUserByEmail(email);

        return org.springframework.security.core.userdetails.User.builder()
                .username(user.getEmail())
                .password(user.getPassword())
                .roles(user.getType().name())
                .build();
    }
}
