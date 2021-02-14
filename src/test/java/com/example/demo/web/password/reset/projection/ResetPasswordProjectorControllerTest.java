package com.example.demo.web.password.reset.projection;

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
public class ResetPasswordProjectorControllerTest {

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
                .id(user.getId())
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
    public void testResetPasswordSuccessDefault() throws Exception {

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.get("/reset-password/success");

        this.mockMvc.perform(request)
                .andDo(MockMvcResultHandlers.log())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("password/reset/view-success"));
    }
}
