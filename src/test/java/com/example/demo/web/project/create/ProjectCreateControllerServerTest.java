package com.example.demo.web.project.create;

import com.example.demo.web.project.create.command.IProjectCreateCommandService;
import com.example.demo.web.project.create.command.model.ProjectAddFlavorRequest;
import com.example.demo.web.project.create.form.ProjectCreateServerForm;
import com.example.demo.web.project.create.projection.model.FetchProjectAvailableServersMapQuery;
import com.example.demo.web.project.create.projection.model.FetchProjectAvailableServersMapResponse;
import com.google.common.collect.ImmutableMap;
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
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

@ActiveProfiles("test")
@SpringBootTest
@Transactional
@AutoConfigureMockMvc
public class ProjectCreateControllerServerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private QueryGateway queryGateway;

    @MockBean
    private IProjectCreateCommandService commandService;

    @Test
    public void whenRequestIsAnonymousThenExpectRedirect() throws Exception {

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.get("/project/create/1/server")
                .with(SecurityMockMvcRequestPostProcessors.anonymous());

        this.mockMvc.perform(request)
                .andDo(MockMvcResultHandlers.log())
                .andExpect(MockMvcResultMatchers.status().is3xxRedirection());
    }

    @Test
    public void whenRequestIsAnonymousThenExpectRedirectURL() throws Exception {

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.get("/project/create/1/server")
                .with(SecurityMockMvcRequestPostProcessors.anonymous());

        this.mockMvc.perform(request)
                .andDo(MockMvcResultHandlers.log())
                .andExpect(MockMvcResultMatchers.redirectedUrl("http://localhost/login"));
    }

    @Test
    public void whenRequestIsUserThenExpectOk() throws Exception {

        UUID id = UUID.randomUUID();

        Mockito.when(queryGateway.query(new FetchProjectAvailableServersMapQuery(id), FetchProjectAvailableServersMapResponse.class))
                .thenReturn(CompletableFuture.completedFuture(new FetchProjectAvailableServersMapResponse(ImmutableMap.of())));

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.get(String.format("/project/create/%s/server", id))
                .with(SecurityMockMvcRequestPostProcessors.user("user"));

        this.mockMvc.perform(request)
                .andDo(MockMvcResultHandlers.log())
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void whenRequestIsValidThenExpectView() throws Exception {

        UUID id = UUID.randomUUID();

        Mockito.when(queryGateway.query(new FetchProjectAvailableServersMapQuery(id), FetchProjectAvailableServersMapResponse.class))
                .thenReturn(CompletableFuture.completedFuture(new FetchProjectAvailableServersMapResponse(ImmutableMap.of())));

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.get(String.format("/project/create/%s/server", id))
                .with(SecurityMockMvcRequestPostProcessors.user("user"));

        this.mockMvc.perform(request)
                .andDo(MockMvcResultHandlers.log())
                .andExpect(MockMvcResultMatchers.view().name("project/create/view-server"));
    }

    @Test
    public void whenRequestIsValidThenExpectModel() throws Exception {

        UUID id = UUID.randomUUID();

        Mockito.when(queryGateway.query(new FetchProjectAvailableServersMapQuery(id), FetchProjectAvailableServersMapResponse.class))
                .thenReturn(CompletableFuture.completedFuture(new FetchProjectAvailableServersMapResponse(ImmutableMap.of())));

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.get(String.format("/project/create/%s/server", id))
                .with(SecurityMockMvcRequestPostProcessors.user("user"));

        ProjectCreateServerForm expected = new ProjectCreateServerForm();
        expected.setAvailableServers(ImmutableMap.of());

        this.mockMvc.perform(request)
                .andDo(MockMvcResultHandlers.log())
                .andExpect(MockMvcResultMatchers.model().attribute("form", expected));
    }

    @Test
    public void whenRequestHasFlashAttributesThenExpectModel() throws Exception {

        UUID id = UUID.randomUUID();

        Mockito.when(queryGateway.query(new FetchProjectAvailableServersMapQuery(id), FetchProjectAvailableServersMapResponse.class))
                .thenReturn(CompletableFuture.completedFuture(new FetchProjectAvailableServersMapResponse(ImmutableMap.of())));

        ProjectCreateServerForm flashAttr = new ProjectCreateServerForm();
        flashAttr.setSelectedServerId("selectedServerId");

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.get(String.format("/project/create/%s/server", id))
                .flashAttr("form", flashAttr)
                .with(SecurityMockMvcRequestPostProcessors.user("user"));

        ProjectCreateServerForm expected = new ProjectCreateServerForm();
        expected.setSelectedServerId("selectedServerId");
        expected.setAvailableServers(ImmutableMap.of());

        this.mockMvc.perform(request)
                .andDo(MockMvcResultHandlers.log())
                .andExpect(MockMvcResultMatchers.model().attribute("form", expected));
    }

    @Test
    public void whenPostHasErrorsThenExpectRedirect() throws Exception {

        UUID id = UUID.randomUUID();

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.post(String.format("/project/create/%s/server", id))
                .with(SecurityMockMvcRequestPostProcessors.user("user"))
                .with(SecurityMockMvcRequestPostProcessors.csrf());

        this.mockMvc.perform(request)
                .andDo(MockMvcResultHandlers.log())
                .andExpect(MockMvcResultMatchers.redirectedUrl(String.format("/project/create/%s/server", id)));
    }

    @Test
    public void whenPostHasErrorsThenExpectErrors() throws Exception {

        UUID id = UUID.randomUUID();

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.post(String.format("/project/create/%s/server", id))
                .with(SecurityMockMvcRequestPostProcessors.user("user"))
                .with(SecurityMockMvcRequestPostProcessors.csrf());

        this.mockMvc.perform(request)
                .andDo(MockMvcResultHandlers.log())
                .andExpect(MockMvcResultMatchers.flash().attributeExists("org.springframework.validation.BindingResult.form"));
    }

    @Test
    public void whenPostIsValidThenExpectRedirect() throws Exception {

        UUID id = UUID.randomUUID();

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.post(String.format("/project/create/%s/server", id))
                .param("selectedServerId", "serverId")
                .with(SecurityMockMvcRequestPostProcessors.user("user"))
                .with(SecurityMockMvcRequestPostProcessors.csrf());

        this.mockMvc.perform(request)
                .andDo(MockMvcResultHandlers.log())
                .andExpect(MockMvcResultMatchers.redirectedUrl(String.format("/project/create/%s/billing", id)));
    }
}
