package com.example.demo.web.dashboard;

import com.example.demo.test.TestUserUtil;
import com.example.demo.user.model.User;
import com.example.demo.user.service.IUserService;
import com.example.demo.user.service.model.UserCreateRequest;
import com.example.demo.web.dashboard.service.model.DashboardDetailsResponse;
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

@ActiveProfiles("test")
@SpringBootTest
@AutoConfigureMockMvc
public class DashboardControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private IUserService userService;

    @Test
    public void testAuthenticatedDashboard() throws Exception {

        UserCreateRequest userCreateRequest = TestUserUtil.createUser("test-dashboard@dashboard-controller.com");
        User user = userService.handleCreateUser(userCreateRequest);

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.get("/dashboard")
                .with(SecurityMockMvcRequestPostProcessors.user(user.getEmail()));

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

        UserCreateRequest userCreateRequest = TestUserUtil.createUser("default-dashboard-view@dashboard-controller.com");
        User user = userService.handleCreateUser(userCreateRequest);

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.get("/dashboard")
                .with(SecurityMockMvcRequestPostProcessors.user(user.getEmail()));

        this.mockMvc.perform(request)
                .andDo(MockMvcResultHandlers.log())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("dashboard/view-default"));
    }

    @Test
    public void testDashboardContentNotVerified() throws Exception {

        UserCreateRequest userCreateRequest = TestUserUtil.createUser("dashboard-content-verified@dahboard-controller.com");
        User user = userService.handleCreateUser(userCreateRequest);

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.get("/dashboard/content")
                .with(SecurityMockMvcRequestPostProcessors.user(user.getEmail()));

        DashboardDetailsResponse detailsResponse = DashboardDetailsResponse.builder()
                .emailVerified(user.getVerification().isVerified())
                .hasProjects(false)
                .build();

        this.mockMvc.perform(request)
                .andDo(MockMvcResultHandlers.log())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("dashboard/partial-content"))
                .andExpect(MockMvcResultMatchers.model().attribute("details", detailsResponse));
    }

    @Test
    public void testDashboardContentVerified() throws Exception {

        UserCreateRequest userCreateRequest = TestUserUtil.createUser("dashboard-content-not-verified@dahboard-controller.com");
        User user = userService.handleCreateUser(userCreateRequest);

        user = userService.handleEmailVerification(user.getVerification().getToken());

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.get("/dashboard/content")
                .with(SecurityMockMvcRequestPostProcessors.user(user.getEmail()));

        DashboardDetailsResponse detailsResponse = DashboardDetailsResponse.builder()
                .emailVerified(user.getVerification().isVerified())
                .hasProjects(true)
                .build();

        this.mockMvc.perform(request)
                .andDo(MockMvcResultHandlers.log())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("dashboard/partial-content"))
                .andExpect(MockMvcResultMatchers.model().attribute("details", detailsResponse));
    }
}
