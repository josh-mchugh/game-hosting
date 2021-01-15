package com.example.demo.web.admin.ovh;

import com.example.demo.web.admin.ovh.model.InstanceGroupStatistic;
import com.example.demo.web.admin.ovh.service.IAdminOvhUtilsProjectorService;
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
public class AdminOvhUtilsControllerInstanceGroupRefreshTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private IAdminOvhUtilsProjectorService adminOvhUtilsProjectorService;

    @Test
    public void whenUserIsUnauthorizedThenExpectLoginScreen() throws Exception {

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.get("/admin/ovh-utils/instance-group/refresh");

        this.mockMvc.perform(request)
                .andDo(MockMvcResultHandlers.log())
                .andExpect(MockMvcResultMatchers.status().is3xxRedirection())
                .andExpect(MockMvcResultMatchers.redirectedUrl("http://localhost/login"));
    }

    @Test
    public void whenUserIsRegularUserThenExpectForbidden() throws Exception {

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.get("/admin/ovh-utils/instance-group/refresh")
                .with(SecurityMockMvcRequestPostProcessors.user("regular").roles("REGULAR"));

        this.mockMvc.perform(request)
                .andDo(MockMvcResultHandlers.log())
                .andExpect(MockMvcResultMatchers.status().isForbidden());
    }

    @Test
    public void whenUserIsAdminThenReturnOk() throws Exception {

        Mockito.when(adminOvhUtilsProjectorService.getInstanceGroupStatistics()).thenReturn(new InstanceGroupStatistic(0));

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.get("/admin/ovh-utils/instance-group/refresh")
                .with(SecurityMockMvcRequestPostProcessors.user("admin").roles("ADMIN"));

        this.mockMvc.perform(request)
                .andDo(MockMvcResultHandlers.log())
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void whenUrlIsValidThenExpectResponse() throws Exception {

        Mockito.when(adminOvhUtilsProjectorService.getInstanceGroupStatistics()).thenReturn(new InstanceGroupStatistic(0));

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.get("/admin/ovh-utils/instance-group/refresh")
                .with(SecurityMockMvcRequestPostProcessors.user("admin").roles("ADMIN"));

        this.mockMvc.perform(request)
                .andDo(MockMvcResultHandlers.log())
                .andExpect(MockMvcResultMatchers.content().json("{total:0}"));
    }
}
