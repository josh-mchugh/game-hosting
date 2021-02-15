package com.example.demo.web.admin.game.projection;

import com.example.demo.framework.web.Select2Response;
import com.example.demo.web.admin.game.projection.service.model.FetchAdminGameServerGamesQuery;
import com.example.demo.web.admin.game.projection.service.model.FetchAdminGameServerGamesResponse;
import com.example.demo.web.admin.game.projection.service.projection.AdminGameServerGameProjection;
import com.fasterxml.jackson.databind.ObjectMapper;
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

import java.util.ArrayList;
import java.util.concurrent.CompletableFuture;

@ActiveProfiles("test")
@SpringBootTest
@AutoConfigureMockMvc
public class AdminGameServerProjectorControllerGetGamesTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private QueryGateway queryGateway;

    @Test
    public void whenUserIsUnauthorizedThenExpectLoginScreen() throws Exception {

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.get("/admin/game-servers/games");

        this.mockMvc.perform(request)
                .andDo(MockMvcResultHandlers.log())
                .andExpect(MockMvcResultMatchers.status().is3xxRedirection())
                .andExpect(MockMvcResultMatchers.redirectedUrl("http://localhost/login"));
    }

    @Test
    public void whenUserIsRegularUserThenExpectForbidden() throws Exception {

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.get("/admin/game-servers/games")
                .with(SecurityMockMvcRequestPostProcessors.user("regular").roles("REGULAR"));

        this.mockMvc.perform(request)
                .andDo(MockMvcResultHandlers.log())
                .andExpect(MockMvcResultMatchers.status().isForbidden());
    }

    @Test
    public void whenUserIsAdminThenReturnOk() throws Exception {

        Mockito.when(queryGateway.query(new FetchAdminGameServerGamesQuery(), FetchAdminGameServerGamesResponse.class))
            .thenReturn(CompletableFuture.completedFuture(new FetchAdminGameServerGamesResponse(null)));

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.get("/admin/game-servers/games")
                .param("regionId", "regionId")
                .with(SecurityMockMvcRequestPostProcessors.user("admin").roles("ADMIN"));

        this.mockMvc.perform(request)
                .andDo(MockMvcResultHandlers.log())
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void whenRequestIsValidThenReturnResponse() throws Exception {

        FetchAdminGameServerGamesResponse response = new FetchAdminGameServerGamesResponse(new ArrayList<>());

        Mockito.when(queryGateway.query(new FetchAdminGameServerGamesQuery(), FetchAdminGameServerGamesResponse.class))
                .thenReturn(CompletableFuture.completedFuture(response));

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.get("/admin/game-servers/games")
                .with(SecurityMockMvcRequestPostProcessors.user("admin").roles("ADMIN"));

        Select2Response<AdminGameServerGameProjection> expected = new Select2Response<>(new ArrayList<>());

        this.mockMvc.perform(request)
                .andDo(MockMvcResultHandlers.log())
                .andExpect(MockMvcResultMatchers.content().json(new ObjectMapper().writeValueAsString(expected)));
    }
}
