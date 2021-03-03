package com.example.demo.framework.security.config;

import com.example.demo.framework.security.authentication.AuthenticationSuccessHandlerImpl;
import com.example.demo.framework.security.user.UserDetailsServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.switchuser.SwitchUserFilter;

@Configuration
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final UserDetailsServiceImpl userDetailsService;
    private final AuthenticationSuccessHandlerImpl authenticationSuccessHandler;
    private final PasswordEncoder passwordEncoder;
    private final SwitchUserFilter switchUserFilter;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

        auth.userDetailsService(userDetailsService)
                .passwordEncoder(passwordEncoder);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        String[] publicResources = new String[]{
                "/resources/**",
                "/registration/**",
                "/forgot-password/**",
                "/reset-password/**",
                "/verify/**",
                "/awx/**"
        };

        http
                .csrf()
                .ignoringAntMatchers("/awx/**")
                .and()
                .authorizeRequests()
                .antMatchers(publicResources).permitAll()
                .antMatchers("/admin/**", "/switch_user").hasRole("ADMIN")
                .antMatchers("/switch_user_exit").hasRole("PREVIOUS_ADMINISTRATOR")
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/login")
                .failureUrl("/login?error=true")
                .successHandler(authenticationSuccessHandler)
                .permitAll()
                .and()
                .logout()
                .logoutUrl("/logout")
                .logoutSuccessUrl("/login")
                .permitAll()
                .and()
                .addFilter(switchUserFilter);
    }
}
