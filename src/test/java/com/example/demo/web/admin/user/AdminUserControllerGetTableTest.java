package com.example.demo.web.admin.user;

import com.example.demo.web.admin.user.service.AdminUserService;
import com.example.demo.web.admin.user.service.model.FetchAdminUserTableQuery;
import com.example.demo.web.admin.user.service.model.FetchAdminUserTableResponse;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.PageImpl;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;

@ActiveProfiles("test")
@SpringBootTest
@AutoConfigureMockMvc
public class AdminUserControllerGetTableTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AdminUserService service;

    @Test
    public void whenUserIsUnauthorizedThenExpectLoginScreen() throws Exception {

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.get("/admin/users/table");

        this.mockMvc.perform(request)
                .andDo(MockMvcResultHandlers.log())
                .andExpect(MockMvcResultMatchers.status().is3xxRedirection())
                .andExpect(MockMvcResultMatchers.redirectedUrl("http://localhost/login"));
    }

    @Test
    public void whenUserIsRegularUserThenExpectForbidden() throws Exception {

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.get("/admin/users/table")
                .with(SecurityMockMvcRequestPostProcessors.user("regular").roles("REGULAR"));

        this.mockMvc.perform(request)
                .andDo(MockMvcResultHandlers.log())
                .andExpect(MockMvcResultMatchers.status().isForbidden());
    }

    @Test
    public void whenUserIsAdminThenReturnOk() throws Exception {

        FetchAdminUserTableQuery query = FetchAdminUserTableQuery.builder().build();
        FetchAdminUserTableResponse response = new FetchAdminUserTableResponse(new PageImpl<>(new ArrayList<>()));

        Mockito.when(service.getTable(query)).thenReturn(response);

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.get("/admin/users/table")
                .with(SecurityMockMvcRequestPostProcessors.user("admin").roles("ADMIN"));

        this.mockMvc.perform(request)
                .andDo(MockMvcResultHandlers.log())
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void whenUrlIsValidThenExpectTemplate() throws Exception {

        FetchAdminUserTableQuery query = FetchAdminUserTableQuery.builder().build();
        FetchAdminUserTableResponse response = new FetchAdminUserTableResponse(new PageImpl<>(new ArrayList<>()));

        Mockito.when(service.getTable(query)).thenReturn(response);

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.get("/admin/users/table")
                .with(SecurityMockMvcRequestPostProcessors.user("admin").roles("ADMIN"));

        this.mockMvc.perform(request)
                .andDo(MockMvcResultHandlers.log())
                .andExpect(MockMvcResultMatchers.view().name("admin/user/partial/partial-table"));
    }

    @Test
    public void whenUrlIsValidThenExpectModel() throws Exception {

        FetchAdminUserTableQuery query = FetchAdminUserTableQuery.builder().build();
        FetchAdminUserTableResponse response = new FetchAdminUserTableResponse(new PageImpl<>(new ArrayList<>()));

        Mockito.when(service.getTable(query)).thenReturn(response);

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.get("/admin/users/table")
                .with(SecurityMockMvcRequestPostProcessors.user("admin").roles("ADMIN"));

        this.mockMvc.perform(request)
                .andDo(MockMvcResultHandlers.log())
                .andExpect(MockMvcResultMatchers.model().attribute("pageable", new PageImpl<>(new ArrayList<>())));
    }
}
