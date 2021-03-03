package com.example.demo.framework.security.user;

import com.example.demo.framework.security.user.projection.model.ExistsUserByEmailQuery;
import com.example.demo.framework.security.user.projection.model.ExistsUserByEmailResponse;
import com.example.demo.framework.security.user.projection.model.FetchUserDetailsByEmailQuery;
import com.example.demo.framework.security.user.projection.model.FetchUserDetailsByEmailResponse;
import com.example.demo.framework.security.user.projection.projection.UserDetailsProjection;
import com.example.demo.user.entity.UserType;
import org.axonframework.queryhandling.QueryGateway;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.concurrent.CompletableFuture;

public class UserDetailsServiceImplTest {

    @Test
    public void whenUserDetailsLoadsUserAndDoesNotExistsThenThrowException() {

        QueryGateway queryGateway = Mockito.mock(QueryGateway.class);
        Mockito.when(queryGateway.query(new ExistsUserByEmailQuery("test@test"), ExistsUserByEmailResponse.class))
                .thenReturn(CompletableFuture.completedFuture(new ExistsUserByEmailResponse(false)));

        UserDetailsServiceImpl service = new UserDetailsServiceImpl(queryGateway);

        Assertions.assertThrows(UsernameNotFoundException.class, () -> service.loadUserByUsername("test@test"));
    }
    
    @Test
    public void whenUserDetailsLoadUsersAndUserExistsThenReturnUser() {

        QueryGateway queryGateway = Mockito.mock(QueryGateway.class);
        Mockito.when(queryGateway.query(new ExistsUserByEmailQuery("test@test"), ExistsUserByEmailResponse.class))
                .thenReturn(CompletableFuture.completedFuture(new ExistsUserByEmailResponse(true)));

        UserDetailsProjection projection = new UserDetailsProjection("test@test", "password", UserType.ADMIN);
        Mockito.when(queryGateway.query(new FetchUserDetailsByEmailQuery("test@test"), FetchUserDetailsByEmailResponse.class))
                .thenReturn(CompletableFuture.completedFuture(new FetchUserDetailsByEmailResponse(projection)));

        UserDetailsServiceImpl service = new UserDetailsServiceImpl(queryGateway);

        UserDetails expected = User.builder()
                .username("test@test")
                .password("password")
                .roles("ADMIN")
                .build();

        Assertions.assertEquals(expected, service.loadUserByUsername("test@test"));
    }
}
