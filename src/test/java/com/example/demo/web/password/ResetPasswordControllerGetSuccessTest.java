package com.example.demo.web.password;

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
public class ResetPasswordControllerGetSuccessTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void whenRequestIsValidThenExpectOk() throws Exception {

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.get("/reset-password/success");

        this.mockMvc.perform(request)
                .andDo(MockMvcResultHandlers.log())
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void whenRequestIsValidThenExpectView() throws Exception {

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.get("/reset-password/success");

        this.mockMvc.perform(request)
                .andDo(MockMvcResultHandlers.log())
                .andExpect(MockMvcResultMatchers.view().name("password/reset/view-success"));
    }
}
