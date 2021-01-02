package com.example.demo.web.verification;

import com.example.demo.user.aggregate.event.UserCreatedEvent;
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
@SpringBootTest
@Transactional
@AutoConfigureMockMvc
public class VerifyCommandControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private IUserService userService;

    @Test
    public void testVerifyEmail() throws Exception {

        UserCreatedEvent event = UserCreatedEvent.builder()
                .id(UUID.randomUUID())
                .email("test@test")
                .password("password")
                .type(UserType.REGULAR)
                .state(UserState.ACTIVE)
                .verification(UserCreatedEvent.createVerification())
                .build();

        User user = userService.handleCreated(event);

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.get(String.format("/verify/%s", user.getVerification().getToken()));

        this.mockMvc.perform(request)
                .andDo(MockMvcResultHandlers.log())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("verification/view-default"))
                .andExpect(MockMvcResultMatchers.model().attribute("validToken", true))
                .andExpect(MockMvcResultMatchers.model().attribute("authenticated", false));
    }

    @Test
    public void testInvalidVerifyEmail() throws Exception {

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.get("/verify/ASDFASDFBX");

        this.mockMvc.perform(request)
                .andDo(MockMvcResultHandlers.log())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("verification/view-default"))
                .andExpect(MockMvcResultMatchers.model().attribute("validToken", false))
                .andExpect(MockMvcResultMatchers.model().attribute("authenticated", false));
    }

    @Test
    public void testVerifyResendEmail() throws Exception {

        UserCreatedEvent event = UserCreatedEvent.builder()
                .id(UUID.randomUUID())
                .email("test@test")
                .password("password")
                .type(UserType.REGULAR)
                .state(UserState.ACTIVE)
                .verification(UserCreatedEvent.createVerification())
                .build();

        User user = userService.handleCreated(event);

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.post("/verify/resend")
                .with(SecurityMockMvcRequestPostProcessors.csrf())
                .with(SecurityMockMvcRequestPostProcessors.user(user.getEmail()));

        this.mockMvc.perform(request)
                .andDo(MockMvcResultHandlers.log())
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
}
