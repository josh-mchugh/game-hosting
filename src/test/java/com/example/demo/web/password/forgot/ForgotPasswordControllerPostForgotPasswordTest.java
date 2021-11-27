package com.example.demo.web.password.forgot;

import com.example.demo.user.aggregate.command.UserRecoveryTokenCreateCommand;
import com.example.demo.web.password.forgot.service.ForgotPasswordService;
import com.example.demo.web.password.forgot.service.model.ExistsUserByEmailQuery;
import com.example.demo.web.password.forgot.service.model.ExistsUserByEmailResponse;
import com.example.demo.web.password.forgot.service.model.FetchUserIdByEmailQuery;
import com.example.demo.web.password.forgot.service.model.FetchUserIdByEmailResponse;
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

@ActiveProfiles("test")
@SpringBootTest
@AutoConfigureMockMvc
public class ForgotPasswordControllerPostForgotPasswordTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CommandGateway commandGateway;

    @MockBean
    private QueryGateway queryGateway;

    @MockBean
    private ForgotPasswordService queryService;

    @Test
    public void whenRequestIsAnonymousThenExpectOk() throws Exception {

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.post("/forgot-password")
                .with(SecurityMockMvcRequestPostProcessors.anonymous())
                .with(SecurityMockMvcRequestPostProcessors.csrf());

        this.mockMvc.perform(request)
                .andDo(MockMvcResultHandlers.log())
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void whenRequestIsUserThenExpectOk() throws Exception {

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.post("/forgot-password")
                .with(SecurityMockMvcRequestPostProcessors.user("user"))
                .with(SecurityMockMvcRequestPostProcessors.csrf());

        this.mockMvc.perform(request)
                .andDo(MockMvcResultHandlers.log())
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void whenRequestHasInvalidFormThenExpectErrors() throws Exception {

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.post("/forgot-password")
                .param("email", "")
                .with(SecurityMockMvcRequestPostProcessors.user("user"))
                .with(SecurityMockMvcRequestPostProcessors.csrf());

        this.mockMvc.perform(request)
                .andDo(MockMvcResultHandlers.log())
                .andExpect(MockMvcResultMatchers.model().hasErrors());
    }

    @Test
    public void whenRequestHasInvalidFormThenExpectView() throws Exception {

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.post("/forgot-password")
                .param("email", "")
                .with(SecurityMockMvcRequestPostProcessors.user("user"))
                .with(SecurityMockMvcRequestPostProcessors.csrf());

        this.mockMvc.perform(request)
                .andDo(MockMvcResultHandlers.log())
                .andExpect(MockMvcResultMatchers.view().name("password/forgot/view-default"));
    }

    @Test
    public void whenRequestHasValidFormThenExpectRedirection() throws Exception {

        Mockito.when(queryService.existsByEmail(new ExistsUserByEmailQuery("test@test")))
                .thenReturn(new ExistsUserByEmailResponse(false));

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.post("/forgot-password")
                .param("email", "test@test")
                .with(SecurityMockMvcRequestPostProcessors.user("user"))
                .with(SecurityMockMvcRequestPostProcessors.csrf());

        this.mockMvc.perform(request)
                .andDo(MockMvcResultHandlers.log())
                .andExpect(MockMvcResultMatchers.status().is3xxRedirection());
    }

    @Test
    public void whenRequestHasValidFormThenExpectView() throws Exception {

        Mockito.when(queryService.existsByEmail(new ExistsUserByEmailQuery("test@test")))
                .thenReturn(new ExistsUserByEmailResponse(false));

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.post("/forgot-password")
                .param("email", "test@test")
                .with(SecurityMockMvcRequestPostProcessors.user("user"))
                .with(SecurityMockMvcRequestPostProcessors.csrf());

        this.mockMvc.perform(request)
                .andDo(MockMvcResultHandlers.log())
                .andExpect(MockMvcResultMatchers.redirectedUrl("/forgot-password/success"));
    }

    @Test
    public void whenRequestHasValidFormAndEmailDoesNotExistsExpectCommandNotCalled() throws Exception {

        Mockito.when(queryService.existsByEmail(new ExistsUserByEmailQuery("test@test")))
                .thenReturn(new ExistsUserByEmailResponse(false));

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.post("/forgot-password")
                .param("email", "test@test")
                .with(SecurityMockMvcRequestPostProcessors.user("user"))
                .with(SecurityMockMvcRequestPostProcessors.csrf());

        this.mockMvc.perform(request);

        Mockito.verify(commandGateway,Mockito.times(0)).send(Mockito.any(UserRecoveryTokenCreateCommand.class));
    }

    @Test
    public void whenRequestHasValidFormAndEmailExistsExpectCommandCalled() throws Exception {

        Mockito.when(queryService.existsByEmail(new ExistsUserByEmailQuery("test@test")))
                .thenReturn(new ExistsUserByEmailResponse(true));

        Mockito.when(queryService.getUserIdByEmail(new FetchUserIdByEmailQuery("test@test")))
                .thenReturn(new FetchUserIdByEmailResponse(UUID.randomUUID()));

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.post("/forgot-password")
                .param("email", "test@test")
                .with(SecurityMockMvcRequestPostProcessors.user("user"))
                .with(SecurityMockMvcRequestPostProcessors.csrf());

        this.mockMvc.perform(request);

        Mockito.verify(commandGateway,Mockito.times(1)).send(Mockito.any(UserRecoveryTokenCreateCommand.class));
    }
}
