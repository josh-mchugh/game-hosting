package com.example.demo.web.project.dashboard;

import com.example.demo.game.entity.GameType;
import com.example.demo.ovh.instance.entity.InstanceStatus;
import com.example.demo.project.entity.ProjectState;
import com.example.demo.project.entity.ProjectStatus;
import com.example.demo.web.project.dashboard.projection.model.FetchProjectDetailsQuery;
import com.example.demo.web.project.dashboard.projection.model.FetchProjectDetailsResponse;
import org.axonframework.queryhandling.QueryGateway;
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
import java.util.concurrent.CompletableFuture;

@ActiveProfiles("test")
@SpringBootTest
@Transactional
@AutoConfigureMockMvc
public class ProjectDashboardControllerDefaultViewTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private QueryGateway queryGateway;

    @Test
    public void whenRequestIsValidThenExpectOk() throws Exception {

        FetchProjectDetailsResponse response = FetchProjectDetailsResponse.builder()
                .name("project")
                .gameType(GameType.MINECRAFT_JAVA)
                .status(ProjectStatus.ACTIVE)
                .state(ProjectState.ACTIVE)
                .build();

        Mockito.when(queryGateway.query(new FetchProjectDetailsQuery("id"), FetchProjectDetailsResponse.class))
                .thenReturn(CompletableFuture.completedFuture(response));

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.get("/project/dashboard/id")
                .with(SecurityMockMvcRequestPostProcessors.user("test@test"));

        this.mockMvc.perform(request)
                .andDo(MockMvcResultHandlers.log())
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void whenRequestIsValidThenExpectView() throws Exception {

        FetchProjectDetailsResponse response = FetchProjectDetailsResponse.builder()
                .name("project")
                .gameType(GameType.MINECRAFT_JAVA)
                .status(ProjectStatus.ACTIVE)
                .state(ProjectState.ACTIVE)
                .build();

        Mockito.when(queryGateway.query(new FetchProjectDetailsQuery("id"), FetchProjectDetailsResponse.class))
                .thenReturn(CompletableFuture.completedFuture(response));

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.get("/project/dashboard/id")
                .with(SecurityMockMvcRequestPostProcessors.user("test@test"));

        this.mockMvc.perform(request)
                .andDo(MockMvcResultHandlers.log())
                .andExpect(MockMvcResultMatchers.view().name("project/view-default"));
    }

    @Test
    public void whenRequestIsValidThenExpectModel() throws Exception {

        FetchProjectDetailsQuery query = new FetchProjectDetailsQuery("id");
        FetchProjectDetailsResponse response = FetchProjectDetailsResponse.builder()
                .name("project-name")
                .gameType(GameType.MINECRAFT_JAVA)
                .status(ProjectStatus.ACTIVE)
                .state(ProjectState.ACTIVE)
                .instanceId("instance-id")
                .instanceStatus(InstanceStatus.BUILD)
                .ip4Address("0.0.0.0.0")
                .build();

        Mockito.when(queryGateway.query(query, FetchProjectDetailsResponse.class))
                .thenReturn(CompletableFuture.completedFuture(response));

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.get("/project/dashboard/id")
                .with(SecurityMockMvcRequestPostProcessors.user("test@test"));

        this.mockMvc.perform(request)
                .andDo(MockMvcResultHandlers.log())
                .andExpect(MockMvcResultMatchers.model().attribute("details", response));
    }
}
