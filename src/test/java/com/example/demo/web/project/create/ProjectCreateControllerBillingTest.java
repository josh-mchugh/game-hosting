package com.example.demo.web.project.create;

import com.example.demo.web.project.create.command.IProjectCreateCommandService;
import com.example.demo.web.project.create.command.model.ProjectAddBillingRequest;
import com.example.demo.web.project.create.form.ProjectCreateBillingForm;
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
public class ProjectCreateControllerBillingTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private IProjectCreateCommandService commandService;

    @Test
    public void whenRequestIsAnonymousThenExpectRedirect() throws Exception {

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.get("/project/create/1/billing")
                .with(SecurityMockMvcRequestPostProcessors.anonymous());

        this.mockMvc.perform(request)
                .andDo(MockMvcResultHandlers.log())
                .andExpect(MockMvcResultMatchers.status().is3xxRedirection());
    }

    @Test
    public void whenRequestIsAnonymousThenExpectRedirectURL() throws Exception {

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.get("/project/create/1/billing")
                .with(SecurityMockMvcRequestPostProcessors.anonymous());

        this.mockMvc.perform(request)
                .andDo(MockMvcResultHandlers.log())
                .andExpect(MockMvcResultMatchers.redirectedUrl("http://localhost/login"));
    }

    @Test
    public void whenRequestIsUserThenExpectOk() throws Exception {

        UUID id = UUID.randomUUID();

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.get(String.format("/project/create/%s/billing", id))
                .with(SecurityMockMvcRequestPostProcessors.user("user"));

        this.mockMvc.perform(request)
                .andDo(MockMvcResultHandlers.log())
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void whenRequestIsValidThenExpectView() throws Exception {

        UUID id = UUID.randomUUID();

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.get(String.format("/project/create/%s/billing", id))
                .with(SecurityMockMvcRequestPostProcessors.user("user"));

        this.mockMvc.perform(request)
                .andDo(MockMvcResultHandlers.log())
                .andExpect(MockMvcResultMatchers.view().name("project/create/view-billing"));
    }

    @Test
    public void whenRequestIsValidThenExpectModel() throws Exception {

        UUID id = UUID.randomUUID();

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.get(String.format("/project/create/%s/billing", id))
                .with(SecurityMockMvcRequestPostProcessors.user("user"));

        this.mockMvc.perform(request)
                .andDo(MockMvcResultHandlers.log())
                .andExpect(MockMvcResultMatchers.model().attribute("form", new ProjectCreateBillingForm()));
    }

    @Test
    public void whenPostHasErrorsThenExpectRedirect() throws Exception {

        UUID id = UUID.randomUUID();

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.post(String.format("/project/create/%s/billing", id))
                .with(SecurityMockMvcRequestPostProcessors.user("user"))
                .with(SecurityMockMvcRequestPostProcessors.csrf());

        this.mockMvc.perform(request)
                .andDo(MockMvcResultHandlers.log())
                .andExpect(MockMvcResultMatchers.redirectedUrl(String.format("/project/create/%s/billing", id)));
    }

    @Test
    public void whenPostHasErrorsThenExpectErrors() throws Exception {

        UUID id = UUID.randomUUID();

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.post(String.format("/project/create/%s/billing", id))
                .with(SecurityMockMvcRequestPostProcessors.user("user"))
                .with(SecurityMockMvcRequestPostProcessors.csrf());

        this.mockMvc.perform(request)
                .andDo(MockMvcResultHandlers.log())
                .andExpect(MockMvcResultMatchers.flash().attributeExists("org.springframework.validation.BindingResult.form"));
    }

    @Test
    public void whenPostIsValidThenExpectRedirect() throws Exception {

        UUID id = UUID.randomUUID();

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.post(String.format("/project/create/%s/billing", id))
                .param("cardNumber", "cardNumber")
                .with(SecurityMockMvcRequestPostProcessors.user("user"))
                .with(SecurityMockMvcRequestPostProcessors.csrf());

        this.mockMvc.perform(request)
                .andDo(MockMvcResultHandlers.log())
                .andExpect(MockMvcResultMatchers.redirectedUrl(String.format("/project/dashboard/%s", id)));
    }

    @Test
    public void whenPostIsValidThenExpectRequestCalled() throws Exception {

        UUID id = UUID.randomUUID();

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.post(String.format("/project/create/%s/billing", id))
                .param("cardNumber", "cardNumber")
                .with(SecurityMockMvcRequestPostProcessors.user("user"))
                .with(SecurityMockMvcRequestPostProcessors.csrf());

        this.mockMvc.perform(request)
                .andDo(MockMvcResultHandlers.log());

        Mockito.verify(commandService, Mockito.times(1)).handleAddBilling(Mockito.any(ProjectAddBillingRequest.class));
    }
}
