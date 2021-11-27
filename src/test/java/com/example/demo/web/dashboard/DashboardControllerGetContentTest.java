package com.example.demo.web.dashboard;

import com.example.demo.framework.security.session.ISessionUtil;
import com.example.demo.web.dashboard.service.DashboardService;
import com.example.demo.web.dashboard.service.model.FetchDashboardDetailsQuery;
import com.example.demo.web.dashboard.service.model.FetchDashboardDetailsResponse;
import org.junit.jupiter.api.BeforeEach;
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
public class DashboardControllerGetContentTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private DashboardService service;

    @MockBean
    private ISessionUtil sessionUtil;

    @BeforeEach
    public void setup() {

        Mockito.when(sessionUtil.getCurrentUserEmail()).thenReturn("test@test");

        Mockito.when(service.fetchUserDashboard(new FetchDashboardDetailsQuery("test@test")))
                .thenReturn(FetchDashboardDetailsResponse.builder().build());
    }

    @Test
    public void whenRequestIsAnonymousThenExpectRedirect() throws Exception {

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.get("/dashboard/content")
                .with(SecurityMockMvcRequestPostProcessors.anonymous());

        this.mockMvc.perform(request)
                .andDo(MockMvcResultHandlers.log())
                .andExpect(MockMvcResultMatchers.status().is3xxRedirection());
    }

    @Test
    public void whenRequestIsAnonymousThenExpectRedirectURL() throws Exception {

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.get("/dashboard/content")
                .with(SecurityMockMvcRequestPostProcessors.anonymous());

        this.mockMvc.perform(request)
                .andDo(MockMvcResultHandlers.log())
                .andExpect(MockMvcResultMatchers.redirectedUrl("http://localhost/login"));
    }

    @Test
    public void whenRequestIsUserThenExpectOk() throws Exception {

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.get("/dashboard/content")
                .with(SecurityMockMvcRequestPostProcessors.user("user"));

        this.mockMvc.perform(request)
                .andDo(MockMvcResultHandlers.log())
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void whenRequestIsValidThenExpectView() throws Exception {

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.get("/dashboard/content")
                .with(SecurityMockMvcRequestPostProcessors.user("user"));

        this.mockMvc.perform(request)
                .andDo(MockMvcResultHandlers.log())
                .andExpect(MockMvcResultMatchers.view().name("dashboard/partial-content"));
    }

    @Test
    public void whenRequestIsValidThenExpectModel() throws Exception {

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.get("/dashboard/content")
                .with(SecurityMockMvcRequestPostProcessors.user("user"));

        this.mockMvc.perform(request)
                .andDo(MockMvcResultHandlers.log())
                .andExpect(MockMvcResultMatchers.model().attribute("details", FetchDashboardDetailsResponse.builder().build()));
    }
}
