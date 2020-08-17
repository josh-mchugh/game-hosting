package com.example.demo.web.dashboard;

import com.example.demo.game.entity.GameType;
import com.example.demo.web.dashboard.model.DashboardProjectCreateForm;
import com.example.demo.web.dashboard.service.projections.DashboardProjectProjection;
import com.example.demo.web.dashboard.service.IDashboardService;
import com.example.demo.web.dashboard.service.model.DashboardDetailsResponse;
import com.example.demo.web.dashboard.service.model.DashboardProjectCreateResponse;
import com.google.common.collect.ImmutableList;
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
public class DashboardControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private IDashboardService dashboardService;

    @Test
    public void testAuthenticatedDashboard() throws Exception {

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.get("/dashboard")
                .with(SecurityMockMvcRequestPostProcessors.user("test@test"));

        this.mockMvc.perform(request)
                .andDo(MockMvcResultHandlers.log())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("dashboard/view-default"));
    }

    @Test
    public void testUnauthenticatedDashboard() throws Exception {

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.get("/dashboard");

        this.mockMvc.perform(request)
                .andDo(MockMvcResultHandlers.log())
                .andExpect(MockMvcResultMatchers.status().is3xxRedirection())
                .andExpect(MockMvcResultMatchers.redirectedUrl("http://localhost/login"));
    }

    @Test
    public void testDefaultDashboardView() throws Exception {

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.get("/dashboard")
                .with(SecurityMockMvcRequestPostProcessors.user("test@test"));

        this.mockMvc.perform(request)
                .andDo(MockMvcResultHandlers.log())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("dashboard/view-default"));
    }

    @Test
    public void testDashboardContentNotVerified() throws Exception {

        DashboardDetailsResponse detailsResponse = DashboardDetailsResponse.builder()
                .emailVerified(false)
                .hasProjects(false)
                .build();

        Mockito.when(dashboardService.getDashboardDetails()).thenReturn(detailsResponse);

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.get("/dashboard/content")
                .with(SecurityMockMvcRequestPostProcessors.user("test@test"));

        this.mockMvc.perform(request)
                .andDo(MockMvcResultHandlers.log())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("dashboard/partial-content"))
                .andExpect(MockMvcResultMatchers.model().attribute("details", detailsResponse));
    }

    @Test
    public void testDashboardContentVerified() throws Exception {

        DashboardProjectProjection projectProjection = new DashboardProjectProjection("id", "name", GameType.MINECRAFT_JAVA);

        DashboardDetailsResponse detailsResponse = DashboardDetailsResponse.builder()
                .emailVerified(true)
                .hasProjects(false)
                .projects(ImmutableList.of(projectProjection))
                .build();

        Mockito.when(dashboardService.getDashboardDetails()).thenReturn(detailsResponse);

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.get("/dashboard/content")
                .with(SecurityMockMvcRequestPostProcessors.user("test@test"));

        this.mockMvc.perform(request)
                .andDo(MockMvcResultHandlers.log())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("dashboard/partial-content"))
                .andExpect(MockMvcResultMatchers.model().attribute("details", detailsResponse));
    }

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

        DashboardProjectCreateResponse projectCreateResponse = DashboardProjectCreateResponse.builder()
                .projectId("project-id")
                .build();

        Mockito.when(dashboardService.handleDashboardProjectCreate(Mockito.any())).thenReturn(projectCreateResponse);

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
