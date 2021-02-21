package com.example.demo.web.password.reset.command;

import com.example.demo.user.aggregate.command.UserPasswordChangeCommand;
import com.example.demo.web.password.reset.command.service.model.FetchUserIdByRecoveryTokenQuery;
import com.example.demo.web.password.reset.command.service.model.FetchUserIdByRecoveryTokenResponse;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.queryhandling.QueryGateway;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;

@ActiveProfiles("test")
@SpringBootTest
@AutoConfigureMockMvc
public class ResetPasswordCommandControllerResetPasswordTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CommandGateway commandGateway;

    @MockBean
    private QueryGateway queryGateway;

    @Test
    public void whenRequestDoesNotHaveTokenThenExpectNotFound() throws Exception {

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.post("/reset-password/")
            .with(SecurityMockMvcRequestPostProcessors.csrf());

        this.mockMvc.perform(request)
                .andDo(MockMvcResultHandlers.log())
                .andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    @Test
    public void whenRequestIsAnonymousUserWithTokenThenExpectOk() throws Exception {

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.post("/reset-password/token")
                .with(SecurityMockMvcRequestPostProcessors.csrf())
                .with(SecurityMockMvcRequestPostProcessors.anonymous());

        this.mockMvc.perform(request)
                .andDo(MockMvcResultHandlers.log())
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void whenRequestIsUserWithTokenThenExpectOk() throws Exception {

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.post("/reset-password/token")
                .with(SecurityMockMvcRequestPostProcessors.csrf())
                .with(SecurityMockMvcRequestPostProcessors.user("user"));

        this.mockMvc.perform(request)
                .andDo(MockMvcResultHandlers.log())
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void whenRequestDoesNotHaveParamsThenExpectErrors() throws Exception {

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.post("/reset-password/token")
                .with(SecurityMockMvcRequestPostProcessors.csrf());

        this.mockMvc.perform(request)
                .andDo(MockMvcResultHandlers.log())
                .andExpect(MockMvcResultMatchers.model().hasErrors());
    }

    @Test
    public void whenRequestHasBlankPasswordsThenExpectErrors() throws Exception {

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.post("/reset-password/token")
                .param("password", "")
                .param("confirmPassword", "")
                .with(SecurityMockMvcRequestPostProcessors.csrf());

        this.mockMvc.perform(request)
                .andDo(MockMvcResultHandlers.log())
                .andExpect(MockMvcResultMatchers.model().hasErrors());
    }

    @Test
    public void whenRequestHasWeakPasswordThenExpectErrors() throws Exception {

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.post("/reset-password/token")
                .param("password", "password")
                .param("confirmPassword", "password")
                .with(SecurityMockMvcRequestPostProcessors.csrf());

        this.mockMvc.perform(request)
                .andDo(MockMvcResultHandlers.log())
                .andExpect(MockMvcResultMatchers.model().hasErrors());
    }

    @Test
    public void whenRequestHasErrorsThenHasInvalidTokenTrueInModel() throws Exception {

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.post("/reset-password/token")
                .param("password", "password")
                .param("confirmPassword", "password")
                .with(SecurityMockMvcRequestPostProcessors.csrf());

        this.mockMvc.perform(request)
                .andDo(MockMvcResultHandlers.log())
                .andExpect(MockMvcResultMatchers.model().attribute("hasValidToken", true));
    }

    @Test
    public void whenRequestHasErrorsThenExpectView() throws Exception {

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.post("/reset-password/token")
                .param("password", "password")
                .param("confirmPassword", "password")
                .with(SecurityMockMvcRequestPostProcessors.csrf());

        this.mockMvc.perform(request)
                .andDo(MockMvcResultHandlers.log())
                .andExpect(MockMvcResultMatchers.view().name("password/reset/view-default"));
    }

    @Test
    public void whenRequestIsValidThenExpectRedirect() throws Exception {

        mockQueryGateway();

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.post("/reset-password/token")
                .param("password", "Password1!")
                .param("confirmPassword", "Password1!")
                .with(SecurityMockMvcRequestPostProcessors.csrf());

        this.mockMvc.perform(request)
                .andDo(MockMvcResultHandlers.log())
                .andExpect(MockMvcResultMatchers.status().is3xxRedirection());
    }

    @Test
    public void whenRequestIsValidThenExpectRedirectionURL() throws Exception {

        mockQueryGateway();

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.post("/reset-password/token")
                .param("password", "Password1!")
                .param("confirmPassword", "Password1!")
                .with(SecurityMockMvcRequestPostProcessors.csrf());

        this.mockMvc.perform(request)
                .andDo(MockMvcResultHandlers.log())
                .andExpect(MockMvcResultMatchers.redirectedUrl("/reset-password/success"));
    }

    @Test
    public void whenRequestIsValidThenExpectCommandCalled() throws Exception {

        mockQueryGateway();

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.post("/reset-password/token")
                .param("password", "Password1!")
                .param("confirmPassword", "Password1!")
                .with(SecurityMockMvcRequestPostProcessors.csrf());

        this.mockMvc.perform(request);

        Mockito.verify(commandGateway, Mockito.times(1)).send(Mockito.any(UserPasswordChangeCommand.class));
    }

    private void mockQueryGateway() {

        Mockito.when(queryGateway.query(new FetchUserIdByRecoveryTokenQuery("token"), FetchUserIdByRecoveryTokenResponse.class))
                .thenReturn(CompletableFuture.completedFuture(new FetchUserIdByRecoveryTokenResponse(UUID.randomUUID())));
    }
}
