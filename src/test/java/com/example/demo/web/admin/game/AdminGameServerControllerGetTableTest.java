package com.example.demo.web.admin.game;

import com.example.demo.web.admin.game.service.AdminGameServerService;
import com.example.demo.web.admin.game.service.model.FetchAdminGameServerTableQuery;
import com.example.demo.web.admin.game.service.model.FetchAdminGameServerTableResponse;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
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
public class AdminGameServerControllerGetTableTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AdminGameServerService service;

    @Test
    public void whenUserIsUnauthorizedThenExpectLoginScreen() throws Exception {

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.get("/admin/game-servers/table");

        this.mockMvc.perform(request)
                .andDo(MockMvcResultHandlers.log())
                .andExpect(MockMvcResultMatchers.status().is3xxRedirection())
                .andExpect(MockMvcResultMatchers.redirectedUrl("http://localhost/login"));
    }

    @Test
    public void whenUserIsRegularUserThenExpectForbidden() throws Exception {

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.get("/admin/game-servers/table")
                .with(SecurityMockMvcRequestPostProcessors.user("regular").roles("REGULAR"));

        this.mockMvc.perform(request)
                .andDo(MockMvcResultHandlers.log())
                .andExpect(MockMvcResultMatchers.status().isForbidden());
    }

    @Test
    public void whenUserIsAdminThenReturnOk() throws Exception {

        Mockito.when(service.getTable(new FetchAdminGameServerTableQuery(PageRequest.of(0, 20))))
                .thenReturn(new FetchAdminGameServerTableResponse(new PageImpl<>(new ArrayList<>())));

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.get("/admin/game-servers/table")
                .param("regionId", "regionId")
                .with(SecurityMockMvcRequestPostProcessors.user("admin").roles("ADMIN"));

        this.mockMvc.perform(request)
                .andDo(MockMvcResultHandlers.log())
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void whenRequestIsValidThenReturnView() throws Exception {

        Mockito.when(service.getTable(new FetchAdminGameServerTableQuery(PageRequest.of(0, 20))))
                .thenReturn(new FetchAdminGameServerTableResponse(new PageImpl<>(new ArrayList<>())));

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.get("/admin/game-servers/table")
                .with(SecurityMockMvcRequestPostProcessors.user("admin").roles("ADMIN"));

        this.mockMvc.perform(request)
                .andDo(MockMvcResultHandlers.log())
                .andExpect(MockMvcResultMatchers.view().name("admin/game/partial/partial-table"));
    }

    @Test
    public void whenRequestIsValidThenExpectPageableInModel() throws Exception {

        Mockito.when(service.getTable(new FetchAdminGameServerTableQuery(PageRequest.of(0, 20))))
                .thenReturn(new FetchAdminGameServerTableResponse(new PageImpl<>(new ArrayList<>())));

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.get("/admin/game-servers/table")
                .with(SecurityMockMvcRequestPostProcessors.user("admin").roles("ADMIN"));

        this.mockMvc.perform(request)
                .andDo(MockMvcResultHandlers.log())
                .andExpect(MockMvcResultMatchers.model().attributeExists("pageable"));
    }
}
