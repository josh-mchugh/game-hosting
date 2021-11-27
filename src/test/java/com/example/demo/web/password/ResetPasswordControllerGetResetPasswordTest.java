package com.example.demo.web.password;

import com.example.demo.web.password.reset.form.ResetPasswordForm;
import com.example.demo.web.password.reset.service.model.ExistsByRecoveryTokenQuery;
import com.example.demo.web.password.reset.service.model.ExistsByRecoveryTokenResponse;
import org.axonframework.queryhandling.QueryGateway;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.concurrent.CompletableFuture;

@ActiveProfiles("test")
@SpringBootTest
@AutoConfigureMockMvc
public class ResetPasswordControllerGetResetPasswordTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private QueryGateway queryGateway;

    @Test
    public void whenRequestDoesNotHaveTokenThenExpectNotFound() throws Exception {

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.get("/reset-password/");

        this.mockMvc.perform(request)
                .andDo(MockMvcResultHandlers.log())
                .andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    @Test
    public void whenRequestHasTokenThenExpectOk() throws Exception {

        defaultMockTokenExists();

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.get("/reset-password/token");

        this.mockMvc.perform(request)
                .andDo(MockMvcResultHandlers.log())
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void whenRequestHasTokenThenExpectView() throws Exception {

        defaultMockTokenExists();

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.get("/reset-password/token");

        this.mockMvc.perform(request)
                .andDo(MockMvcResultHandlers.log())
                .andExpect(MockMvcResultMatchers.view().name("password/reset/view-default"));
    }

    @Test
    public void whenRequestHasValidTokenThenExpectHasValidTokenBeFalse() throws Exception {

        mockTokenExists("token", false);

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.get("/reset-password/token");

        this.mockMvc.perform(request)
                .andDo(MockMvcResultHandlers.log())
                .andExpect(MockMvcResultMatchers.model().attribute("hasValidToken", false));
    }

    @Test
    public void whenRequestHasValidTokenThenExpectHasValidTokenBeTrue() throws Exception {

        defaultMockTokenExists();

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.get("/reset-password/token");

        this.mockMvc.perform(request)
                .andDo(MockMvcResultHandlers.log())
                .andExpect(MockMvcResultMatchers.model().attribute("hasValidToken", true));
    }

    @Test
    public void whenRequestHasValidTokenThenExpectResetPasswordForm() throws Exception {

        defaultMockTokenExists();

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.get("/reset-password/token");

        this.mockMvc.perform(request)
                .andDo(MockMvcResultHandlers.log())
                .andExpect(MockMvcResultMatchers.model().attribute("form", new ResetPasswordForm()));
    }

    private void defaultMockTokenExists() {

        mockTokenExists("token", true);
    }

    private void mockTokenExists(String token, boolean exists) {

        Mockito.when(queryGateway.query(new ExistsByRecoveryTokenQuery(token), ExistsByRecoveryTokenResponse.class))
                .thenReturn(CompletableFuture.completedFuture(new ExistsByRecoveryTokenResponse(exists)));
    }
}
