package com.example.demo.web.password.forgot;

import com.example.demo.web.password.forgot.model.ForgotPasswordForm;
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
public class ForgotPasswordControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testGetForgotPasswordDefaultView() throws Exception {

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.get("/forgot-password");

        this.mockMvc.perform(request)
                .andDo(MockMvcResultHandlers.log())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("password/forgot/view-default"));
    }

    @Test
    public void testGetForgotDefaultWithEmailParamView() throws Exception {

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.get("/forgot-password")
                .param("email", "test@test");

        ForgotPasswordForm form = new ForgotPasswordForm();
        form.setEmail("test@test");

        this.mockMvc.perform(request)
                .andDo(MockMvcResultHandlers.log())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("password/forgot/view-default"))
                .andExpect(MockMvcResultMatchers.model().attribute("form", form));
    }

    @Test
    public void testPostForgotPasswordDefaultView() throws Exception {

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.post("/forgot-password")
                .param("email", "test@test.com")
                .with(SecurityMockMvcRequestPostProcessors.csrf());

        this.mockMvc.perform(request)
                .andDo(MockMvcResultHandlers.log())
                .andExpect(MockMvcResultMatchers.status().is3xxRedirection())
                .andExpect(MockMvcResultMatchers.redirectedUrl("/forgot-password/success"));
    }

    @Test
    public void testPostForgotPasswordInvalid() throws Exception {

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.post("/forgot-password")
                .with(SecurityMockMvcRequestPostProcessors.csrf());

        this.mockMvc.perform(request)
                .andDo(MockMvcResultHandlers.log())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("password/forgot/view-default"))
                .andExpect(MockMvcResultMatchers.model().hasErrors());
    }

    @Test
    public void testGetForGotPasswordSuccessView() throws Exception {

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.get("/forgot-password/success");

        this.mockMvc.perform(request)
                .andDo(MockMvcResultHandlers.log())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("password/forgot/view-success"));
    }
}
