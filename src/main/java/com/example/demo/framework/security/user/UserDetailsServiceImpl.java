package com.example.demo.framework.security.user;

import com.example.demo.framework.security.user.projection.model.ExistsUserByEmailQuery;
import com.example.demo.framework.security.user.projection.model.ExistsUserByEmailResponse;
import com.example.demo.framework.security.user.projection.model.FetchUserDetailsByEmailQuery;
import com.example.demo.framework.security.user.projection.model.FetchUserDetailsByEmailResponse;
import com.example.demo.framework.security.user.projection.projection.UserDetailsProjection;
import lombok.RequiredArgsConstructor;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.concurrent.ExecutionException;

@Component
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final QueryGateway queryGateway;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        try {

            if (!existsByEmail(email)) {

                throw new UsernameNotFoundException(String.format("Unable to find user with email: %s", email));
            }

            UserDetailsProjection userDetails = getUserDetails(email);

            return User.builder()
                    .username(userDetails.getEmail())
                    .password(userDetails.getPassword())
                    .roles(userDetails.getType())
                    .build();

        } catch (Exception e) {

            throw new UsernameNotFoundException("Error occurred loading user details.", e);
        }
    }

    private boolean existsByEmail(String email) throws ExecutionException, InterruptedException {

        ExistsUserByEmailQuery query = new ExistsUserByEmailQuery(email);
        ExistsUserByEmailResponse response = queryGateway.query(query, ExistsUserByEmailResponse.class).get();

        return response.exists();
    }

    private UserDetailsProjection getUserDetails(String email) throws ExecutionException, InterruptedException {

        FetchUserDetailsByEmailQuery query = new FetchUserDetailsByEmailQuery(email);
        FetchUserDetailsByEmailResponse response = queryGateway.query(query, FetchUserDetailsByEmailResponse.class).get();

        return response.getUserDetails();
    }
}
