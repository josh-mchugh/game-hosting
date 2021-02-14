package com.example.demo.web.dashboard.projection;

import com.example.demo.game.entity.GameType;
import com.example.demo.project.projection.model.ProjectDashboardProjection;
import com.example.demo.web.dashboard.projection.service.IDashboardProjectionService;
import com.example.demo.web.dashboard.projection.service.model.DashboardDetailsResponse;
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

import java.util.UUID;

@ActiveProfiles("test")
@SpringBootTest
@AutoConfigureMockMvc
public class DashboardProjectionControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private IDashboardProjectionService dashboardService;

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

        ProjectDashboardProjection projectProjection = new ProjectDashboardProjection(UUID.randomUUID().toString(), "name", GameType.MINECRAFT_JAVA);

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


}
