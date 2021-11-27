package com.example.demo.web.verification;

import com.example.demo.framework.security.session.ISessionUtil;
import com.example.demo.user.aggregate.command.UserVerifyCommand;
import com.example.demo.web.verification.service.IVerifyProjectorService;
import com.example.demo.web.verification.service.model.ExistsUserByVerifyTokenQuery;
import com.example.demo.web.verification.service.model.ExistsUserByVerifyTokenResponse;
import com.example.demo.web.verification.service.model.FetchUserIdByVerificationTokenQuery;
import com.example.demo.web.verification.service.model.FetchUserIdByVerificationTokenResponse;
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
public class VerificationControllerGetDefaultTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ISessionUtil sessionUtil;

    @MockBean
    private IVerifyProjectorService verifyProjectorService;

    @MockBean
    private CommandGateway commandGateway;

    @Test
    public void whenUserIsUnauthorizedThenReturnOk() throws Exception {

        Mockito.when(verifyProjectorService.existsByToken(new ExistsUserByVerifyTokenQuery("token")))
                .thenReturn(new ExistsUserByVerifyTokenResponse(false));

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.get("/verify/token");

        this.mockMvc.perform(request)
                .andDo(MockMvcResultHandlers.log())
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void whenUserIsRegularUserThenReturnOk() throws Exception {

        Mockito.when(verifyProjectorService.existsByToken(new ExistsUserByVerifyTokenQuery("token")))
                .thenReturn(new ExistsUserByVerifyTokenResponse(false));

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.get("/verify/token")
                .with(SecurityMockMvcRequestPostProcessors.user("regular").roles("USER"));

        this.mockMvc.perform(request)
                .andDo(MockMvcResultHandlers.log())
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void whenRequestIsValidThenReturnView() throws Exception {

        Mockito.when(verifyProjectorService.existsByToken(new ExistsUserByVerifyTokenQuery("token")))
                .thenReturn(new ExistsUserByVerifyTokenResponse(false));

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.get("/verify/token")
                .with(SecurityMockMvcRequestPostProcessors.user("regular").roles("USER"));

        this.mockMvc.perform(request)
                .andDo(MockMvcResultHandlers.log())
                .andExpect(MockMvcResultMatchers.view().name("verification/view-default"));
    }

    @Test
    public void whenRequestHasValidSessionThenExpectAuthenticatedTrueInModel() throws Exception {

        Mockito.when(verifyProjectorService.existsByToken(new ExistsUserByVerifyTokenQuery("token")))
                .thenReturn(new ExistsUserByVerifyTokenResponse(false));

        Mockito.when(sessionUtil.isAuthenticated()).thenReturn(true);

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.get("/verify/token")
                .with(SecurityMockMvcRequestPostProcessors.user("regular").roles("USER"));

        this.mockMvc.perform(request)
                .andDo(MockMvcResultHandlers.log())
                .andExpect(MockMvcResultMatchers.model().attribute("authenticated", true));
    }

    @Test
    public void whenRequestHasValidSessionThenExpectAuthenticatedFalseInModel() throws Exception {

        Mockito.when(verifyProjectorService.existsByToken(new ExistsUserByVerifyTokenQuery("token")))
                .thenReturn(new ExistsUserByVerifyTokenResponse(false));

        Mockito.when(sessionUtil.isAuthenticated()).thenReturn(false);

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.get("/verify/token")
                .with(SecurityMockMvcRequestPostProcessors.user("regular").roles("USER"));

        this.mockMvc.perform(request)
                .andDo(MockMvcResultHandlers.log())
                .andExpect(MockMvcResultMatchers.model().attribute("authenticated", false));

    }

    @Test
    public void whenRequestHasValidTokenThenExpectValidTokenTrueInModel() throws Exception {

        Mockito.when(verifyProjectorService.existsByToken(new ExistsUserByVerifyTokenQuery("token")))
                .thenReturn(new ExistsUserByVerifyTokenResponse(true));

        Mockito.when(verifyProjectorService.fetchUserIdByVerificationToken(new FetchUserIdByVerificationTokenQuery("token")))
                .thenReturn(new FetchUserIdByVerificationTokenResponse(UUID.randomUUID()));

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.get("/verify/token")
                .with(SecurityMockMvcRequestPostProcessors.user("regular").roles("USER"));

        this.mockMvc.perform(request)
                .andDo(MockMvcResultHandlers.log())
                .andExpect(MockMvcResultMatchers.model().attribute("validToken", true));

    }

    @Test
    public void whenRequestHasInvalidTokenThenExpectValidTokenFalseInModel() throws Exception {

        Mockito.when(verifyProjectorService.existsByToken(new ExistsUserByVerifyTokenQuery("token")))
                .thenReturn(new ExistsUserByVerifyTokenResponse(false));

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.get("/verify/token")
                .with(SecurityMockMvcRequestPostProcessors.user("regular").roles("USER"));

        this.mockMvc.perform(request)
                .andDo(MockMvcResultHandlers.log())
                .andExpect(MockMvcResultMatchers.model().attribute("validToken", false));
    }

    @Test
    public void whenRequestHasValidTokenThenExpectCommandCalled() throws Exception {

        Mockito.when(verifyProjectorService.existsByToken(new ExistsUserByVerifyTokenQuery("token")))
                .thenReturn(new ExistsUserByVerifyTokenResponse(true));

        Mockito.when(verifyProjectorService.fetchUserIdByVerificationToken(new FetchUserIdByVerificationTokenQuery("token")))
                .thenReturn(new FetchUserIdByVerificationTokenResponse(UUID.randomUUID()));

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.get("/verify/token")
                .with(SecurityMockMvcRequestPostProcessors.user("regular").roles("USER"));

        this.mockMvc.perform(request);

        Mockito.verify(commandGateway, Mockito.times(1)).send(Mockito.any(UserVerifyCommand.class));
    }
}
