package com.example.demo.web.verify;

import com.example.demo.user.entity.UserState;
import com.example.demo.user.entity.UserType;
import com.example.demo.user.model.User;
import com.example.demo.user.service.IUserService;
import com.example.demo.user.service.model.UserCreateRequest;
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

@ActiveProfiles("test")
@SpringBootTest
@AutoConfigureMockMvc
public class VerifyControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private IUserService userService;

    @Test
    public void testVerifyEmail() throws Exception {

        UserCreateRequest userCreateRequest = UserCreateRequest.builder()
                .email("valid-verify@verify-controller.com")
                .password("Password1")
                .type(UserType.REGULAR)
                .state(UserState.ACTIVE)
                .build();

        User user = userService.handleCreateUser(userCreateRequest);

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.get(String.format("/verify/%s", user.getVerification().getToken()));

        this.mockMvc.perform(request)
                .andDo(MockMvcResultHandlers.log())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("verify/view-default"))
                .andExpect(MockMvcResultMatchers.model().attribute("validToken", true))
                .andExpect(MockMvcResultMatchers.model().attribute("authenticated", false));
    }

    @Test
    public void testInvalidVerifyEmail() throws Exception {

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.get("/verify/ASDFASDFBX");

        this.mockMvc.perform(request)
                .andDo(MockMvcResultHandlers.log())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("verify/view-default"))
                .andExpect(MockMvcResultMatchers.model().attribute("validToken", false))
                .andExpect(MockMvcResultMatchers.model().attribute("authenticated", false));
    }

    @Test
    public void testVerifyResendEmail() throws Exception {

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.post("/verify/resend")
                .with(SecurityMockMvcRequestPostProcessors.csrf());

        this.mockMvc.perform(request)
                .andDo(MockMvcResultHandlers.log())
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
}
