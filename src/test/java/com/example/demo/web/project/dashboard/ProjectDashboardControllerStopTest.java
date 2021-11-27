package com.example.demo.web.project.dashboard;

import com.example.demo.web.project.dashboard.command.ProjectCommandService;
import com.example.demo.web.project.dashboard.command.model.ProjectInstanceStopRequest;
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

@ActiveProfiles("test")
@SpringBootTest
@AutoConfigureMockMvc
public class ProjectDashboardControllerStopTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProjectCommandService projectCommandService;

    @Test
    public void whenDefaultPostStopInstanceThenReturnOk() throws Exception {

        Mockito.doNothing().when(projectCommandService).handleProjectInstanceStop(ProjectInstanceStopRequest.builder().build());

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.post("/project/dashboard/asdfasdf/instance/asdasdf/stop")
                .with(SecurityMockMvcRequestPostProcessors.user("test@test"))
                .with(SecurityMockMvcRequestPostProcessors.csrf());

        this.mockMvc.perform(request)
                .andDo(MockMvcResultHandlers.log())
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
}
