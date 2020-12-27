package com.example.demo.web.dashboard.command;

import com.example.demo.game.entity.GameType;
import com.example.demo.web.dashboard.command.model.DashboardProjectCreateForm;
import com.example.demo.web.dashboard.command.service.IDashboardCommandService;
import com.example.demo.web.dashboard.command.service.model.DashboardProjectCreateResponse;
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
public class DashboardCommandControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private IDashboardCommandService dashboardService;

    @Test
    public void testGetDashboardProjectCreate() throws Exception {

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.get("/dashboard/project/create")
                .with(SecurityMockMvcRequestPostProcessors.user("test@test"));

        DashboardProjectCreateForm form = new DashboardProjectCreateForm();

        this.mockMvc.perform(request)
                .andDo(MockMvcResultHandlers.log())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("dashboard/modal-project-create"))
                .andExpect(MockMvcResultMatchers.model().attribute("form", form));
    }

    @Test
    public void testPostDashboardProjectCreateErrors() throws Exception {

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.post("/dashboard/project/create")
                .with(SecurityMockMvcRequestPostProcessors.user("test@test"))
                .with(SecurityMockMvcRequestPostProcessors.csrf());

        this.mockMvc.perform(request)
                .andDo(MockMvcResultHandlers.log())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("dashboard/modal-project-create"))
                .andExpect(MockMvcResultMatchers.model().hasErrors())
                .andExpect(MockMvcResultMatchers.model().errorCount(4));
    }

    @Test
    public void testPostDashboardProjectCreateValid() throws Exception {

        DashboardProjectCreateResponse projectCreateResponse = new DashboardProjectCreateResponse("project-id");

        Mockito.when(dashboardService.handleProjectCreate(Mockito.any())).thenReturn(projectCreateResponse);

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.post("/dashboard/project/create")
                .with(SecurityMockMvcRequestPostProcessors.user("test@test"))
                .with(SecurityMockMvcRequestPostProcessors.csrf())
                .param("name", "Test Project 1")
                .param("game", GameType.MINECRAFT_JAVA.name())
                .param("region", "US_EAST_VA_1")
                .param("server", "1cpu - 2gb ram");

        this.mockMvc.perform(request)
                .andDo(MockMvcResultHandlers.log())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("component/modal-response"))
                .andExpect(MockMvcResultMatchers.model().attribute("redirectUrl", String.format("/project/%s", projectCreateResponse.getProjectId())));
    }
}
