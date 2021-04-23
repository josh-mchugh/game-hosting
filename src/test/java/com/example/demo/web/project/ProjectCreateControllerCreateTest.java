package com.example.demo.web.project;

import com.example.demo.web.project.form.ProjectCreateForm;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
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
public class ProjectCreateControllerCreateTest {

    @Autowired
    private MockMvc mockMvc;

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

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.get("/project/create")
                .with(SecurityMockMvcRequestPostProcessors.user("user"));

        this.mockMvc.perform(request)
                .andDo(MockMvcResultHandlers.log())
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void whenRequestIsValidThenExpectView() throws Exception {

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.get("/project/create")
                .with(SecurityMockMvcRequestPostProcessors.user("user"));

        this.mockMvc.perform(request)
                .andDo(MockMvcResultHandlers.log())
                .andExpect(MockMvcResultMatchers.view().name("project/create/view-create"));
    }

    @Test
    public void whenRequestIsValidThenExpectModel() throws Exception {

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.get("/project/create")
                .with(SecurityMockMvcRequestPostProcessors.user("user"));

        this.mockMvc.perform(request)
                .andDo(MockMvcResultHandlers.log())
                .andExpect(MockMvcResultMatchers.model().attribute("form", new ProjectCreateForm()));
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
                .andExpect(MockMvcResultMatchers.flash().attributeExists("org.springframework.validation.BindingResult.form"));
    }

    @Test
    public void whenPostIsValidThenExpectRedirect() throws Exception {

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.post("/project/create")
                .param("projectName", "test")
                .param("selectedGameId", "gameId")
                .with(SecurityMockMvcRequestPostProcessors.user("user"))
                .with(SecurityMockMvcRequestPostProcessors.csrf());

        this.mockMvc.perform(request)
                .andDo(MockMvcResultHandlers.log())
                .andExpect(MockMvcResultMatchers.redirectedUrl("/project/1/create/region"));
    }
}
