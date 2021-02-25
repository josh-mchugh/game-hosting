package com.example.demo.web.dashboard.command;

import com.example.demo.framework.security.session.ISessionUtil;
import com.example.demo.game.entity.GameType;
import com.example.demo.project.aggregate.command.ProjectCreateCommand;
import com.example.demo.user.entity.model.User;
import com.example.demo.web.dashboard.command.service.model.FetchGameIdByGameTypeQuery;
import com.example.demo.web.dashboard.command.service.model.FetchGameIdByGameTypeResponse;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.queryhandling.QueryGateway;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;

@ActiveProfiles("test")
@SpringBootTest
@AutoConfigureMockMvc
public class DashboardCommandControllerProjectCreateTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ISessionUtil sessionUtil;

    @MockBean
    private CommandGateway commandGateway;

    @MockBean
    private QueryGateway queryGateway;

    @Test
    public void whenRequestIsAnonymousThenExpectRedirect() throws Exception {

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.post("/dashboard/project/create")
                .with(SecurityMockMvcRequestPostProcessors.anonymous())
                .with(SecurityMockMvcRequestPostProcessors.csrf());

        this.mockMvc.perform(request)
                .andDo(MockMvcResultHandlers.log())
                .andExpect(MockMvcResultMatchers.status().is3xxRedirection());
    }

    @Test
    public void whenRequestIsAnonymousThenExpectRedirectURL() throws Exception {

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.post("/dashboard/project/create")
                .with(SecurityMockMvcRequestPostProcessors.anonymous())
                .with(SecurityMockMvcRequestPostProcessors.csrf());

        this.mockMvc.perform(request)
                .andDo(MockMvcResultHandlers.log())
                .andExpect(MockMvcResultMatchers.redirectedUrl("http://localhost/login"));
    }

    @Test
    public void whenRequestIsUserThenExpectOk() throws Exception {

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.post("/dashboard/project/create")
                .with(SecurityMockMvcRequestPostProcessors.user("user"))
                .with(SecurityMockMvcRequestPostProcessors.csrf());

        this.mockMvc.perform(request)
                .andDo(MockMvcResultHandlers.log())
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void whenRequestIsInValidThenExpectView() throws Exception {

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.post("/dashboard/project/create")
                .with(SecurityMockMvcRequestPostProcessors.user("user"))
                .with(SecurityMockMvcRequestPostProcessors.csrf());

        this.mockMvc.perform(request)
                .andDo(MockMvcResultHandlers.log())
                .andExpect(MockMvcResultMatchers.view().name("dashboard/modal-project-create"));
    }

    @Test
    public void whenRequestHasInvalidNameThenExpectError() throws Exception {

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.post("/dashboard/project/create")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("name", "")
                .param("game", "MINECRAFT_JAVA")
                .param("region", "region")
                .param("server", "server")
                .with(SecurityMockMvcRequestPostProcessors.user("user"))
                .with(SecurityMockMvcRequestPostProcessors.csrf());

        this.mockMvc.perform(request)
                .andDo(MockMvcResultHandlers.log())
                .andExpect(MockMvcResultMatchers.model().hasErrors());
    }

    @Test
    public void whenRequestHasInvalidGameThenExpectError() throws Exception {

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.post("/dashboard/project/create")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("name", "name")
                .param("game", "")
                .param("region", "region")
                .param("server", "server")
                .with(SecurityMockMvcRequestPostProcessors.user("user"))
                .with(SecurityMockMvcRequestPostProcessors.csrf());

        this.mockMvc.perform(request)
                .andDo(MockMvcResultHandlers.log())
                .andExpect(MockMvcResultMatchers.model().hasErrors());
    }

    @Test
    public void whenRequestHasInvalidRegionThenExpectError() throws Exception {

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.post("/dashboard/project/create")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("name", "name")
                .param("game", "game")
                .param("region", "")
                .param("server", "server")
                .with(SecurityMockMvcRequestPostProcessors.user("user"))
                .with(SecurityMockMvcRequestPostProcessors.csrf());

        this.mockMvc.perform(request)
                .andDo(MockMvcResultHandlers.log())
                .andExpect(MockMvcResultMatchers.model().hasErrors());
    }

    @Test
    public void whenRequestHasInvalidServerThenExpectError() throws Exception {

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.post("/dashboard/project/create")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("name", "name")
                .param("game", "game")
                .param("region", "region")
                .param("server", "")
                .with(SecurityMockMvcRequestPostProcessors.user("user"))
                .with(SecurityMockMvcRequestPostProcessors.csrf());

        this.mockMvc.perform(request)
                .andDo(MockMvcResultHandlers.log())
                .andExpect(MockMvcResultMatchers.model().hasErrors());
    }

    @Test
    public void whenRequestIsValidThenExpectView() throws Exception {

        Mockito.when(sessionUtil.getCurrentUser()).thenReturn(User.builder().id(UUID.randomUUID()).build());
        Mockito.when(commandGateway.sendAndWait(Mockito.any(ProjectCreateCommand.class))).thenReturn(UUID.randomUUID());
        Mockito.when(queryGateway.query(new FetchGameIdByGameTypeQuery(GameType.MINECRAFT_JAVA), FetchGameIdByGameTypeResponse.class))
                .thenReturn(CompletableFuture.completedFuture(new FetchGameIdByGameTypeResponse(UUID.randomUUID())));

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.post("/dashboard/project/create")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .params(getValidForm())
                .with(SecurityMockMvcRequestPostProcessors.user("user"))
                .with(SecurityMockMvcRequestPostProcessors.csrf());

        this.mockMvc.perform(request)
                .andDo(MockMvcResultHandlers.log())
                .andExpect(MockMvcResultMatchers.view().name("component/modal-response"));
    }

    @Test
    public void whenRequestIsValidThenExpectRedirectURL() throws Exception {

        UUID projectId = UUID.fromString("290fa41f-ab00-4d9c-b96a-c1748467ce50");

        Mockito.when(sessionUtil.getCurrentUser()).thenReturn(User.builder().id(UUID.randomUUID()).build());
        Mockito.when(commandGateway.sendAndWait(Mockito.any(ProjectCreateCommand.class))).thenReturn(projectId);
        Mockito.when(queryGateway.query(new FetchGameIdByGameTypeQuery(GameType.MINECRAFT_JAVA), FetchGameIdByGameTypeResponse.class))
                .thenReturn(CompletableFuture.completedFuture(new FetchGameIdByGameTypeResponse(UUID.randomUUID())));

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.post("/dashboard/project/create")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .params(getValidForm())
                .with(SecurityMockMvcRequestPostProcessors.user("user"))
                .with(SecurityMockMvcRequestPostProcessors.csrf());

        this.mockMvc.perform(request)
                .andDo(MockMvcResultHandlers.log())
                .andExpect(MockMvcResultMatchers.model().attribute("redirectUrl", "/project/290fa41f-ab00-4d9c-b96a-c1748467ce50"));

    }

    @Test
    public void whenRequestIsValidThenExpectCommandCalled() throws Exception {

        Mockito.when(sessionUtil.getCurrentUser()).thenReturn(User.builder().id(UUID.randomUUID()).build());
        Mockito.when(commandGateway.sendAndWait(Mockito.any(ProjectCreateCommand.class))).thenReturn(UUID.randomUUID());
        Mockito.when(queryGateway.query(new FetchGameIdByGameTypeQuery(GameType.MINECRAFT_JAVA), FetchGameIdByGameTypeResponse.class))
                .thenReturn(CompletableFuture.completedFuture(new FetchGameIdByGameTypeResponse(UUID.randomUUID())));

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.post("/dashboard/project/create")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .params(getValidForm())
                .with(SecurityMockMvcRequestPostProcessors.user("user"))
                .with(SecurityMockMvcRequestPostProcessors.csrf());

        this.mockMvc.perform(request);

        Mockito.verify(commandGateway, Mockito.times(1)).sendAndWait(Mockito.any(ProjectCreateCommand.class));
    }

    private MultiValueMap<String, String> getValidForm() {

        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("name", "name");
        params.add("game", "MINECRAFT_JAVA");
        params.add("region", "region");
        params.add("server", "server");

        return params;
    }
}
