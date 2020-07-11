package com.example.demo.web.password.reset;

import com.example.demo.recovery.model.RecoveryToken;
import com.example.demo.recovery.service.IRecoveryTokenService;
import com.example.demo.user.entity.UserState;
import com.example.demo.user.entity.UserType;
import com.example.demo.user.model.User;
import com.example.demo.user.service.IUserService;
import com.example.demo.user.service.model.UserCreateRequest;
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

@ActiveProfiles("test")
@SpringBootTest
@AutoConfigureMockMvc
public class ResetPasswordControllerTest {

    @Autowired
    private IUserService userService;

    @Autowired
    private IRecoveryTokenService recoveryTokenService;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testResetPasswordWithValidId() throws Exception {

        UserCreateRequest userCreateRequest = buildUserCreateRequest("user1@reset-password-controller.com");
        User user = userService.handleCreateUser(userCreateRequest);

        RecoveryToken recoveryToken = recoveryTokenService.handleCreateRecoveryToken(user.getEmail());

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.get(String.format("/reset-password/%s", recoveryToken.getId()));

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

    private UserCreateRequest buildUserCreateRequest(String email) {

        return UserCreateRequest.builder()
                .email(email)
                .password("Password1")
                .state(UserState.ACTIVE)
                .type(UserType.REGULAR)
                .build();
    }
}
