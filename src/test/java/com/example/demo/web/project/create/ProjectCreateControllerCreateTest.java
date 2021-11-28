package com.example.demo.web.project.create;

import com.example.demo.web.project.create.command.ProjectCreateCommandService;
import com.example.demo.web.project.create.command.model.ProjectCreateRequest;
import com.example.demo.web.project.create.command.model.ProjectCreateResponse;
import com.example.demo.web.project.create.form.ProjectCreateForm;
import com.example.demo.web.project.create.query.ProjectCreateService;
import com.example.demo.web.project.create.query.model.FetchProjectAvailableGameMapQuery;
import com.example.demo.web.project.create.query.model.FetchProjectAvailableGameMapResponse;
import com.google.common.collect.ImmutableMap;
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

@ActiveProfiles("test")
@SpringBootTest
@Transactional
@AutoConfigureMockMvc
public class ProjectCreateControllerCreateTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProjectCreateCommandService commandService;

    @MockBean
    private ProjectCreateService queryService;

    @Test
    public void whenRequestIsAnonymousThenExpectRedirect() throws Exception {

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.get("/project/create")
                .with(SecurityMockMvcRequestPostProcessors.anonymous());

        this.mockMvc.perform(request)
                .andDo(MockMvcResultHandlers.log())
                .andExpect(MockMvcResultMatchers.status().is3xxRedirection());
    }

    @Test
    public void whenRequestIsAnonymousThenExpectRedirectURL() throws Exception {

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.get("/project/create")
                .with(SecurityMockMvcRequestPostProcessors.anonymous());

        this.mockMvc.perform(request)
                .andDo(MockMvcResultHandlers.log())
                .andExpect(MockMvcResultMatchers.redirectedUrl("http://localhost/login"));
    }

    @Test
    public void whenRequestIsUserThenExpectOk() throws Exception {

        Mockito.when(queryService.fetchAvailableGameMap(new FetchProjectAvailableGameMapQuery()))
                .thenReturn(new FetchProjectAvailableGameMapResponse(ImmutableMap.of()));

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.get("/project/create")
                .with(SecurityMockMvcRequestPostProcessors.user("user"));

        this.mockMvc.perform(request)
                .andDo(MockMvcResultHandlers.log())
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void whenRequestIsValidThenExpectView() throws Exception {

        Mockito.when(queryService.fetchAvailableGameMap(new FetchProjectAvailableGameMapQuery()))
                .thenReturn(new FetchProjectAvailableGameMapResponse(ImmutableMap.of()));

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.get("/project/create")
                .with(SecurityMockMvcRequestPostProcessors.user("user"));

        this.mockMvc.perform(request)
                .andDo(MockMvcResultHandlers.log())
                .andExpect(MockMvcResultMatchers.view().name("project/create/view-create"));
    }

    @Test
    public void whenRequestIsValidThenExpectModel() throws Exception {

        Mockito.when(queryService.fetchAvailableGameMap(new FetchProjectAvailableGameMapQuery()))
                .thenReturn(new FetchProjectAvailableGameMapResponse(ImmutableMap.of()));

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.get("/project/create")
                .with(SecurityMockMvcRequestPostProcessors.user("user"));

        ProjectCreateForm expected = new ProjectCreateForm();
        expected.setAvailableGames(ImmutableMap.of());

        this.mockMvc.perform(request)
                .andDo(MockMvcResultHandlers.log())
                .andExpect(MockMvcResultMatchers.model().attribute("form", expected));
    }

    @Test
    public void whenRequestHasFlashAttrThenExpectModel() throws Exception {

        Mockito.when(queryService.fetchAvailableGameMap(new FetchProjectAvailableGameMapQuery()))
                .thenReturn(new FetchProjectAvailableGameMapResponse(ImmutableMap.of()));

        ProjectCreateForm flashAttr = new ProjectCreateForm();
        flashAttr.setProjectName("projectName");
        flashAttr.setSelectedGameId("selectedGameId");

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.get("/project/create")
                .flashAttr("form", flashAttr)
                .with(SecurityMockMvcRequestPostProcessors.user("user"));

        ProjectCreateForm expected = new ProjectCreateForm();
        expected.setProjectName("projectName");
        expected.setSelectedGameId("selectedGameId");
        expected.setAvailableGames(ImmutableMap.of());

        this.mockMvc.perform(request)
                .andDo(MockMvcResultHandlers.log())
                .andExpect(MockMvcResultMatchers.model().attribute("form", expected));
    }

    @Test
    public void whenPostHasErrorsThenExpectRedirect() throws Exception {

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.post("/project/create")
                .with(SecurityMockMvcRequestPostProcessors.user("user"))
                .with(SecurityMockMvcRequestPostProcessors.csrf());

        this.mockMvc.perform(request)
                .andDo(MockMvcResultHandlers.log())
                .andExpect(MockMvcResultMatchers.redirectedUrl("/project/create"));
    }

    @Test
    public void whenPostHasErrorsThenExpectErrors() throws Exception {

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.post("/project/create")
                .with(SecurityMockMvcRequestPostProcessors.user("user"))
                .with(SecurityMockMvcRequestPostProcessors.csrf());

        this.mockMvc.perform(request)
                .andDo(MockMvcResultHandlers.log())
                .andExpect(MockMvcResultMatchers.flash().attributeExists("form"))
                .andExpect(MockMvcResultMatchers.flash().attributeExists("org.springframework.validation.BindingResult.form"));
    }

    @Test
    public void whenPostIsValidThenExpectRedirect() throws Exception {

        UUID id = UUID.randomUUID();

        Mockito.when(commandService.handleCreate(Mockito.any(ProjectCreateRequest.class)))
                .thenReturn(new ProjectCreateResponse(id));

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.post("/project/create")
                .param("projectName", "test")
                .param("selectedGameId", "gameId")
                .with(SecurityMockMvcRequestPostProcessors.user("user"))
                .with(SecurityMockMvcRequestPostProcessors.csrf());

        this.mockMvc.perform(request)
                .andDo(MockMvcResultHandlers.log())
                .andExpect(MockMvcResultMatchers.redirectedUrl(String.format("/project/create/%s/region", id)));
    }
}
