package com.example.demo.web.awx;

import com.example.demo.web.awx.service.IAwxControllerPlaybookService;
import com.google.common.collect.ImmutableList;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@ActiveProfiles("test")
@SpringBootTest
@AutoConfigureMockMvc
public class AwxCommandControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private IAwxControllerPlaybookService awxControllerPlaybookService;

    @Test
    public void whenNotificationProjectCallbackHasValidRequestAndReturnsOk() throws Exception {

        Mockito.when(awxControllerPlaybookService.handleCreatePlaybooks(Mockito.any())).thenReturn(ImmutableList.of());

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.post(String.format("/awx/notification/project/%s/success", 1L))
                .contentType(MediaType.APPLICATION_JSON);

        this.mockMvc.perform(request)
                .andDo(MockMvcResultHandlers.log())
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
}
