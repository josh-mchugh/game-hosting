package com.example.demo.web.password.forgot.projection;

import com.example.demo.web.password.forgot.command.model.ForgotPasswordForm;
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
public class ForgotPasswordProjectorControllerTest {

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
    public void testGetForGotPasswordSuccessView() throws Exception {

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.get("/forgot-password/success");

        this.mockMvc.perform(request)
                .andDo(MockMvcResultHandlers.log())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("password/forgot/view-success"));
    }
}
