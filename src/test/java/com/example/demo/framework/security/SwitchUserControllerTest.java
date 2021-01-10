package com.example.demo.framework.security;

import com.example.demo.sample.SampleBuilder;
import com.example.demo.user.entity.UserType;
import com.example.demo.user.entity.model.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors;
import org.springframework.security.web.authentication.switchuser.SwitchUserFilter;
import org.springframework.security.web.authentication.switchuser.SwitchUserGrantedAuthority;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import javax.transaction.Transactional;
import java.util.Arrays;
import java.util.Collection;

@ActiveProfiles("test")
@Transactional
@SpringBootTest
@AutoConfigureMockMvc
public class SwitchUserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private SampleBuilder sampleBuilder;

    @Test
    public void whenNonAdminAttemptsToAccessSwitchUsersExpectForbidden() throws Exception {

        User user = sampleBuilder.builder()
                .user()
                .build()
                .getUser();

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.get("/switch_user")
                .param("username", user.getEmail())
                .with(SecurityMockMvcRequestPostProcessors.user("regular").roles(UserType.REGULAR.name()));

        this.mockMvc.perform(request)
                .andDo(MockMvcResultHandlers.log())
                .andExpect(MockMvcResultMatchers.status().isForbidden());
    }

    @Test
    public void whenAdminAttemptsToAccessSwitchUsersExpectRedirect() throws Exception {

        User user = sampleBuilder.builder()
                .user()
                .build()
                .getUser();

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.get("/switch_user")
                .param("username", user.getEmail())
                .with(SecurityMockMvcRequestPostProcessors.user("admin").roles(UserType.ADMIN.name()));

        this.mockMvc.perform(request)
                .andDo(MockMvcResultHandlers.log())
                .andExpect(MockMvcResultMatchers.status().is3xxRedirection())
                .andExpect(MockMvcResultMatchers.redirectedUrl("/dashboard"));
    }

    @Test
    public void whenNonAdminAttemptsToExitSwitchUsersExpectForbidden() throws Exception {

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.get("/switch_user_exit")
                .with(SecurityMockMvcRequestPostProcessors.user("regular").roles(UserType.REGULAR.name()));

        this.mockMvc.perform(request)
                .andDo(MockMvcResultHandlers.log())
                .andExpect(MockMvcResultMatchers.status().isForbidden());
    }

    @Test
    public void whenAdminAttemptsToExitSwitchUsersExpectForbidden() throws Exception {

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.get("/switch_user_exit")
                .with(SecurityMockMvcRequestPostProcessors.user("admin").roles(UserType.ADMIN.name()));

        this.mockMvc.perform(request)
                .andDo(MockMvcResultHandlers.log())
                .andExpect(MockMvcResultMatchers.status().isForbidden());
    }

    @Test
    public void whenImpersonatorAttemptsToExitSwitchUsersExpectRedirect() throws Exception {

        Authentication adminAuth = new UsernamePasswordAuthenticationToken("admin@admin", "password");

        Collection<? extends GrantedAuthority> authorities = Arrays.asList(
                new SimpleGrantedAuthority("ROLE_USER"),
                new SimpleGrantedAuthority(SwitchUserFilter.ROLE_PREVIOUS_ADMINISTRATOR),
                new SwitchUserGrantedAuthority("ROLE_ADMIN", adminAuth)
        );

        Authentication userAuth = new UsernamePasswordAuthenticationToken("user@user", "password", authorities);

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.get("/switch_user_exit")
                .with(SecurityMockMvcRequestPostProcessors.authentication(userAuth));

        this.mockMvc.perform(request)
                .andDo(MockMvcResultHandlers.log())
                .andExpect(MockMvcResultMatchers.status().is3xxRedirection())
                .andExpect(MockMvcResultMatchers.redirectedUrl("/admin/users"));
    }
}
