package com.example.demo.web.password.forgot;

import com.example.demo.web.password.forgot.form.ForgotPasswordForm;
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
public class ForgotPasswordControllerGetDefaultTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void whenRequestIsAnonymousThenExpectOk() throws Exception {

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.get("/forgot-password")
                .with(SecurityMockMvcRequestPostProcessors.anonymous());

        this.mockMvc.perform(request)
                .andDo(MockMvcResultHandlers.log())
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void whenRequestIsUserThenExpectOk() throws Exception {

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.get("/forgot-password")
                .with(SecurityMockMvcRequestPostProcessors.user("user"));

        this.mockMvc.perform(request)
                .andDo(MockMvcResultHandlers.log())
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void whenRequestIsValidThenExpectView() throws Exception {

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.get("/forgot-password")
                .with(SecurityMockMvcRequestPostProcessors.user("user"));

        this.mockMvc.perform(request)
                .andDo(MockMvcResultHandlers.log())
                .andExpect(MockMvcResultMatchers.view().name("password/forgot/view-default"));
    }

    @Test
    public void whenRequestIsValidThenExpectModel() throws Exception {

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.get("/forgot-password")
                .with(SecurityMockMvcRequestPostProcessors.user("user"));

        this.mockMvc.perform(request)
                .andDo(MockMvcResultHandlers.log())
                .andExpect(MockMvcResultMatchers.model().attribute("form", new ForgotPasswordForm()));
    }

    @Test
    public void whenRequestIsValidWithEmailParamThenExpectModelWithEmail() throws Exception {

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.get("/forgot-password")
                .param("email", "test@test")
                .with(SecurityMockMvcRequestPostProcessors.user("user"));

        ForgotPasswordForm form = new ForgotPasswordForm();
        form.setEmail("test@test");

        this.mockMvc.perform(request)
                .andDo(MockMvcResultHandlers.log())
                .andExpect(MockMvcResultMatchers.model().attribute("form", form));
    }
}
