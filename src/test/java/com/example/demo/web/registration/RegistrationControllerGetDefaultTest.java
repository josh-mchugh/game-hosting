package com.example.demo.web.registration;

import com.example.demo.web.registration.form.RegistrationForm;
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
public class RegistrationControllerGetDefaultTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testRegistrationDefault() throws Exception {

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.get("/registration");

        this.mockMvc.perform(request)
                .andDo(MockMvcResultHandlers.log())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("registration/view-default"))
                .andExpect(MockMvcResultMatchers.model().attribute("form", new RegistrationForm()));
    }
}
