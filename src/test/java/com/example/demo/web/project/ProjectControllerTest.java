package com.example.demo.web.project;

import com.example.demo.game.entity.GameType;
import com.example.demo.ovh.instance.entity.InstanceStatus;
import com.example.demo.web.project.service.IProjectControllerService;
import com.example.demo.web.project.service.model.ProjectDetails;
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

@ActiveProfiles("test")
@SpringBootTest
@Transactional
@AutoConfigureMockMvc
public class ProjectControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private IProjectControllerService projectControllerService;

    @Test
    public void whenDefaultViewAuthenticatedThenReturnOk() throws Exception {

        ProjectDetails projectDetails = ProjectDetails.builder()
                .name("project-name")
                .gameType(GameType.MINECRAFT_JAVA)
                .instanceId("instance-id")
                .instanceStatus(InstanceStatus.BUILD)
                .ip4Address("0.0.0.0.0")
                .build();

        Mockito.when(projectControllerService.getProjectDetails(Mockito.anyString())).thenReturn(projectDetails);

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.get("/project/project-id")
                .with(SecurityMockMvcRequestPostProcessors.user("test@test"));

        this.mockMvc.perform(request)
                .andDo(MockMvcResultHandlers.log())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("project/view-default"))
                .andExpect(MockMvcResultMatchers.model().attribute("details", projectDetails));
    }

    @Test
    public void whenDefaultPostStartInstanceThenReturnOk() throws Exception {

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.post("/project/asdfasdf/instance/asdasdf/start")
                .with(SecurityMockMvcRequestPostProcessors.user("test@test"))
                .with(SecurityMockMvcRequestPostProcessors.csrf());

        this.mockMvc.perform(request)
                .andDo(MockMvcResultHandlers.log())
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void whenDefaultPostStopInstanceThenReturnOk() throws Exception {

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.post("/project/asdfasdf/instance/asdasdf/stop")
                .with(SecurityMockMvcRequestPostProcessors.user("test@test"))
                .with(SecurityMockMvcRequestPostProcessors.csrf());

        this.mockMvc.perform(request)
                .andDo(MockMvcResultHandlers.log())
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
}
