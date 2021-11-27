package com.example.demo.web.admin.game;

import com.example.demo.framework.web.Select2Response;
import com.example.demo.web.admin.game.service.AdminGameServerService;
import com.example.demo.web.admin.game.service.model.FetchAdminGameServerFlavorsQuery;
import com.example.demo.web.admin.game.service.model.FetchAdminGameServerFlavorsResponse;
import com.example.demo.web.admin.game.service.projection.AdminGameServerFlavorProjection;
import com.fasterxml.jackson.databind.ObjectMapper;
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

@ActiveProfiles("test")
@SpringBootTest
@AutoConfigureMockMvc
public class AdminGameServerControllerGetFlavorsTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AdminGameServerService service;

    @Test
    public void whenUserIsUnauthorizedThenExpectLoginScreen() throws Exception {

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.get("/admin/game-servers/flavors");

        this.mockMvc.perform(request)
                .andDo(MockMvcResultHandlers.log())
                .andExpect(MockMvcResultMatchers.status().is3xxRedirection())
                .andExpect(MockMvcResultMatchers.redirectedUrl("http://localhost/login"));
    }

    @Test
    public void whenUserIsRegularUserThenExpectForbidden() throws Exception {

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.get("/admin/game-servers/flavors")
                .with(SecurityMockMvcRequestPostProcessors.user("regular").roles("REGULAR"));

        this.mockMvc.perform(request)
                .andDo(MockMvcResultHandlers.log())
                .andExpect(MockMvcResultMatchers.status().isForbidden());
    }

    @Test
    public void whenUserIsAdminThenReturnOk() throws Exception {

        Mockito.when(service.getFlavors(new FetchAdminGameServerFlavorsQuery(null, "regionId")))
                .thenReturn(new FetchAdminGameServerFlavorsResponse(new ArrayList<>()));

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.get("/admin/game-servers/flavors")
                .param("regionId", "regionId")
                .with(SecurityMockMvcRequestPostProcessors.user("admin").roles("ADMIN"));

        this.mockMvc.perform(request)
                .andDo(MockMvcResultHandlers.log())
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void whenRequestMissingParamRegionIdThenReturnBadRequest() throws Exception {

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.get("/admin/game-servers/flavors")
                .with(SecurityMockMvcRequestPostProcessors.user("admin").roles("ADMIN"));

        this.mockMvc.perform(request)
                .andDo(MockMvcResultHandlers.log())
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    @Test
    public void whenRequestMissingParamSearchThenReturnOk() throws Exception {

        Mockito.when(service.getFlavors(new FetchAdminGameServerFlavorsQuery(null, "regionId")))
                .thenReturn(new FetchAdminGameServerFlavorsResponse(new ArrayList<>()));

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.get("/admin/game-servers/flavors")
                .param("regionId", "regionId")
                .with(SecurityMockMvcRequestPostProcessors.user("admin").roles("ADMIN"));

        this.mockMvc.perform(request)
                .andDo(MockMvcResultHandlers.log())
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void whenRequestIsValidThenReturnResponse() throws Exception {

        Mockito.when(service.getFlavors(new FetchAdminGameServerFlavorsQuery("", "regionId")))
                .thenReturn(new FetchAdminGameServerFlavorsResponse(new ArrayList<>()));

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.get("/admin/game-servers/flavors")
                .param("search", "")
                .param("regionId", "regionId")
                .with(SecurityMockMvcRequestPostProcessors.user("admin").roles("ADMIN"));

        Select2Response<AdminGameServerFlavorProjection> expected = new Select2Response<>(new ArrayList<>());

        this.mockMvc.perform(request)
                .andDo(MockMvcResultHandlers.log())
                .andExpect(MockMvcResultMatchers.content().json(new ObjectMapper().writeValueAsString(expected)));
    }
}
