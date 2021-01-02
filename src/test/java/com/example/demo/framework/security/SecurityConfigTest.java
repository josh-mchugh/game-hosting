package com.example.demo.framework.security;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.security.config.annotation.ObjectPostProcessor;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.HashMap;
import java.util.Map;

public class SecurityConfigTest {

    @Test
    public void whenSecurityConfigConfigureManagerThenExpectNoException() {

        UserDetailsServiceImpl userDetailsService = Mockito.mock(UserDetailsServiceImpl.class);
        AuthenticationSuccessHandlerImpl authenticationSuccessHandler = Mockito.mock(AuthenticationSuccessHandlerImpl.class);
        PasswordEncoder passwordEncoder = Mockito.mock(PasswordEncoder.class);

        SecurityConfig config = new SecurityConfig(userDetailsService, authenticationSuccessHandler, passwordEncoder);

        ObjectPostProcessor objectPostProcessor = Mockito.mock(ObjectPostProcessor.class);

        Assertions.assertDoesNotThrow(() -> config.configure(new AuthenticationManagerBuilder(objectPostProcessor)));
    }

    @Test
    public void whenSecurityConfigConfigureHttpSecurityThenExpectNoException() {

        UserDetailsServiceImpl userDetailsService = Mockito.mock(UserDetailsServiceImpl.class);
        AuthenticationSuccessHandlerImpl authenticationSuccessHandler = Mockito.mock(AuthenticationSuccessHandlerImpl.class);
        PasswordEncoder passwordEncoder = Mockito.mock(PasswordEncoder.class);

        SecurityConfig config = new SecurityConfig(userDetailsService, authenticationSuccessHandler, passwordEncoder);

        ObjectPostProcessor objectPostProcessor = Mockito.mock(ObjectPostProcessor.class);
        AuthenticationManagerBuilder authenticationManagerBuilder = new AuthenticationManagerBuilder(objectPostProcessor);
        Map<Class<?>, Object> sharedObjects = new HashMap<>();

        HttpSecurity httpSecurity = new HttpSecurity(objectPostProcessor, authenticationManagerBuilder, sharedObjects);

        Assertions.assertDoesNotThrow(() -> config.configure(httpSecurity));
    }
}
