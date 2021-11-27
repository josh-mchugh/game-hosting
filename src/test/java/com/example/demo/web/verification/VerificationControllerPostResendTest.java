package com.example.demo.web.verification;

import com.example.demo.framework.security.session.ISessionUtil;
import com.example.demo.user.aggregate.command.UserVerifyResetCommand;
import org.axonframework.commandhandling.gateway.CommandGateway;
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

import javax.transaction.Transactional;
import java.util.UUID;

@ActiveProfiles("test")
@SpringBootTest
@Transactional
@AutoConfigureMockMvc
public class VerificationControllerPostResendTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ISessionUtil sessionUtil;

    @MockBean
    private CommandGateway commandGateway;

    @Test
    public void whenUserIsUnauthorizedThenReturnOk() throws Exception {

        Mockito.when(sessionUtil.isAuthenticated()).thenReturn(false);

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.post("/verify/resend")
                .with(SecurityMockMvcRequestPostProcessors.csrf());

        this.mockMvc.perform(request)
                .andDo(MockMvcResultHandlers.log())
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void whenUserIsRegularUserThenReturnOk() throws Exception {

        Mockito.when(sessionUtil.getCurrentUserId()).thenReturn(UUID.randomUUID());
        Mockito.when(sessionUtil.isAuthenticated()).thenReturn(true);

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.post("/verify/resend")
                .with(SecurityMockMvcRequestPostProcessors.user("regular").roles("USER"))
                .with(SecurityMockMvcRequestPostProcessors.csrf());

        this.mockMvc.perform(request)
                .andDo(MockMvcResultHandlers.log())
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void whenRequestIsAuthenticatedThenExpectCommandCalled() throws Exception {

        Mockito.when(sessionUtil.getCurrentUserId()).thenReturn(UUID.randomUUID());
        Mockito.when(sessionUtil.isAuthenticated()).thenReturn(true);

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.post("/verify/resend")
                .with(SecurityMockMvcRequestPostProcessors.user("regular").roles("USER"))
                .with(SecurityMockMvcRequestPostProcessors.csrf());

        this.mockMvc.perform(request);

        Mockito.verify(commandGateway, Mockito.times(1)).send(Mockito.any(UserVerifyResetCommand.class));
    }

    @Test
    public void whenRequestIsUnAuthenticatedThenExpectCommandNotCalled() throws Exception {

        Mockito.when(sessionUtil.isAuthenticated()).thenReturn(false);

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.post("/verify/resend")
                .with(SecurityMockMvcRequestPostProcessors.user("regular").roles("USER"))
                .with(SecurityMockMvcRequestPostProcessors.csrf());

        this.mockMvc.perform(request);

        Mockito.verify(commandGateway, Mockito.times(0)).send(Mockito.any(UserVerifyResetCommand.class));
    }
}
