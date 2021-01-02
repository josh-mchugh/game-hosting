package com.example.demo.framework.security;

import com.example.demo.user.entity.UserType;
import com.example.demo.user.entity.model.User;
import com.example.demo.user.projection.IUserProjector;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class UserDetailsServiceImplTest {

    @Test
    public void whenUserDetailsLoadsUserAndDoesNotExistsThenThrowException() {

        IUserProjector userProjector = Mockito.mock(IUserProjector.class);
        Mockito.when(userProjector.existsByEmail(Mockito.anyString())).thenReturn(false);

        UserDetailsServiceImpl service = new UserDetailsServiceImpl(userProjector);

        Assertions.assertThrows(UsernameNotFoundException.class, () -> service.loadUserByUsername("test@test"));
    }
    
    @Test
    public void whenUserDetailsLoadUsersAndUserExistsThenReturnUser() {

        User user = User.builder()
                .email("test@test")
                .password("password")
                .type(UserType.ADMIN)
                .build();

        IUserProjector userProjector = Mockito.mock(IUserProjector.class);
        Mockito.when(userProjector.existsByEmail(Mockito.anyString())).thenReturn(true);
        Mockito.when(userProjector.getUserByEmail(Mockito.anyString())).thenReturn(user);

        UserDetailsServiceImpl service = new UserDetailsServiceImpl(userProjector);

        UserDetails expected = org.springframework.security.core.userdetails.User.builder()
                .username("test@test")
                .password("password")
                .roles("ADMIN")
                .build();

        Assertions.assertEquals(expected, service.loadUserByUsername("test@test"));
    }
}
