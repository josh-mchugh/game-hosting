package com.example.demo.web.password.reset;

import com.example.demo.user.aggregate.event.UserCreatedEvent;
import com.example.demo.user.aggregate.event.UserRecoveryTokenCreatedEvent;
import com.example.demo.user.entity.UserState;
import com.example.demo.user.entity.UserType;
import com.example.demo.user.entity.model.User;
import com.example.demo.user.entity.service.IUserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import javax.transaction.Transactional;
import java.util.UUID;

@ActiveProfiles("test")
@Transactional
@SpringBootTest
@AutoConfigureMockMvc
public class ResetPasswordControllerTest {

    @Autowired
    private IUserService userService;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testResetPasswordWithValidId() throws Exception {

        UserCreatedEvent event = UserCreatedEvent.builder()
                .id(UUID.randomUUID())
                .email("test@test")
                .password("password")
                .type(UserType.REGULAR)
                .state(UserState.ACTIVE)
                .verification(UserCreatedEvent.createVerification())
                .build();

        User user = userService.handleCreated(event);

        UserRecoveryTokenCreatedEvent recoveryTokenCreatedEvent = UserRecoveryTokenCreatedEvent.builder()
                .id(UUID.fromString(user.getId()))
                .recoveryToken(UserRecoveryTokenCreatedEvent.createRecoveryToken(1000L * 60))
                .build();

        user = userService.handleRecoveryTokenCreated(recoveryTokenCreatedEvent);

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.get(String.format("/reset-password/%s", user.getRecoveryToken().getToken()));

        this.mockMvc.perform(request)
                .andDo(MockMvcResultHandlers.log())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("password/reset/view-default"))
                .andExpect(MockMvcResultMatchers.model().attribute("hasValidToken", true));
    }

    @Test
    public void testResetPasswordWithInvalidId() throws Exception {

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.get("/reset-password/asdfaasdf");

        this.mockMvc.perform(request)
                .andDo(MockMvcResultHandlers.log())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("password/reset/view-default"))
                .andExpect(MockMvcResultMatchers.model().attribute("hasValidToken", false));
    }

    @Test
    public void testResetPasswordEmptyPasswords() throws Exception {

        UserCreatedEvent event = UserCreatedEvent.builder()
                .id(UUID.randomUUID())
                .email("test@test")
                .password("password")
                .type(UserType.REGULAR)
                .state(UserState.ACTIVE)
                .verification(UserCreatedEvent.createVerification())
                .build();

        User user = userService.handleCreated(event);

        UserRecoveryTokenCreatedEvent recoveryTokenCreatedEvent = UserRecoveryTokenCreatedEvent.builder()
                .id(UUID.fromString(user.getId()))
                .recoveryToken(UserRecoveryTokenCreatedEvent.createRecoveryToken(1000L * 60))
                .build();

        user = userService.handleRecoveryTokenCreated(recoveryTokenCreatedEvent);

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.post(String.format("/reset-password/%s", user.getRecoveryToken().getToken()))
                .with(SecurityMockMvcRequestPostProcessors.csrf())
                .param("password", "")
                .param("confirmPassword", "");

        this.mockMvc.perform(request)
                .andDo(MockMvcResultHandlers.log())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("password/reset/view-default"))
                .andExpect(MockMvcResultMatchers.model().hasErrors());
    }

    @Test
    public void testResetPasswordMismatchPassword() throws Exception {

        UserCreatedEvent event = UserCreatedEvent.builder()
                .id(UUID.randomUUID())
                .email("test@test")
                .password("password")
                .type(UserType.REGULAR)
                .state(UserState.ACTIVE)
                .verification(UserCreatedEvent.createVerification())

                .build();

        User user = userService.handleCreated(event);

        UserRecoveryTokenCreatedEvent recoveryTokenCreatedEvent = UserRecoveryTokenCreatedEvent.builder()
                .id(UUID.fromString(user.getId()))
                .recoveryToken(UserRecoveryTokenCreatedEvent.createRecoveryToken(1000L * 60))
                .build();

        user = userService.handleRecoveryTokenCreated(recoveryTokenCreatedEvent);

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.post(String.format("/reset-password/%s", user.getRecoveryToken().getToken()))
                .with(SecurityMockMvcRequestPostProcessors.csrf())
                .param("password", "Password1!")
                .param("confirmPassword", "Password2!");

        this.mockMvc.perform(request)
                .andDo(MockMvcResultHandlers.log())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("password/reset/view-default"))
                .andExpect(MockMvcResultMatchers.model().hasErrors());
    }

    @Test
    public void testResetPasswordWeakPassword() throws Exception {

        UserCreatedEvent event = UserCreatedEvent.builder()
                .id(UUID.randomUUID())
                .email("test@test")
                .password("password")
                .type(UserType.REGULAR)
                .state(UserState.ACTIVE)
                .verification(UserCreatedEvent.createVerification())
                .build();

        User user = userService.handleCreated(event);

        UserRecoveryTokenCreatedEvent recoveryTokenCreatedEvent = UserRecoveryTokenCreatedEvent.builder()
                .id(UUID.fromString(user.getId()))
                .recoveryToken(UserRecoveryTokenCreatedEvent.createRecoveryToken(1000L * 60))
                .build();

        user = userService.handleRecoveryTokenCreated(recoveryTokenCreatedEvent);

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.post(String.format("/reset-password/%s", user.getRecoveryToken().getToken()))
                .with(SecurityMockMvcRequestPostProcessors.csrf())
                .param("password", "password")
                .param("confirmPassword", "password");

        this.mockMvc.perform(request)
                .andDo(MockMvcResultHandlers.log())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("password/reset/view-default"))
                .andExpect(MockMvcResultMatchers.model().hasErrors());
    }

    @Test
    public void testResetPasswordSubmissionValid() throws Exception {

        UserCreatedEvent event = UserCreatedEvent.builder()
                .id(UUID.randomUUID())
                .email("test@test")
                .password("password")
                .type(UserType.REGULAR)
                .state(UserState.ACTIVE)
                .verification(UserCreatedEvent.createVerification())
                .build();

        User user = userService.handleCreated(event);

        UserRecoveryTokenCreatedEvent recoveryTokenCreatedEvent = UserRecoveryTokenCreatedEvent.builder()
                .id(UUID.fromString(user.getId()))
                .recoveryToken(UserRecoveryTokenCreatedEvent.createRecoveryToken(1000L * 60))
                .build();

        user = userService.handleRecoveryTokenCreated(recoveryTokenCreatedEvent);

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.post(String.format("/reset-password/%s", user.getRecoveryToken().getToken()))
                .with(SecurityMockMvcRequestPostProcessors.csrf())
                .param("password", "Password1!")
                .param("confirmPassword", "Password1!");

        this.mockMvc.perform(request)
                .andDo(MockMvcResultHandlers.log())
                .andExpect(MockMvcResultMatchers.status().is3xxRedirection())
                .andExpect(MockMvcResultMatchers.redirectedUrl("/reset-password/success"));
    }

    @Test
    public void testResetPasswordSuccessDefault() throws Exception {

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.get("/reset-password/success");

        this.mockMvc.perform(request)
                .andDo(MockMvcResultHandlers.log())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("password/reset/view-success"));
    }
}
