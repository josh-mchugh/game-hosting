package com.example.demo.web.awx;

import com.example.demo.awx.playbook.aggregate.command.AwxPlaybookCreateCommand;
import com.example.demo.awx.playbook.feign.IPlaybookFeignService;
import com.example.demo.web.awx.service.AwxService;
import com.example.demo.web.awx.service.model.ExistsAnyPlaybooksQuery;
import com.example.demo.web.awx.service.model.ExistsAnyPlaybooksResponse;
import com.example.demo.web.awx.service.model.FetchProjectByAwxIdQuery;
import com.example.demo.web.awx.service.model.FetchProjectByAwxIdResponse;
import com.example.demo.web.awx.service.projection.ProjectProjection;
import org.axonframework.commandhandling.gateway.CommandGateway;
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

import java.util.Collections;
import java.util.UUID;

@ActiveProfiles("test")
@SpringBootTest
@AutoConfigureMockMvc
public class AwxControllerPostNotificationProjectCallbackTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AwxService service;

    @MockBean
    private CommandGateway commandGateway;

    @MockBean
    private IPlaybookFeignService playbookFeignService;

    @Test
    public void whenRequestIsAnonymousThenExpectOk() throws Exception {

        Mockito.when(service.existsAnyPlaybooks(new ExistsAnyPlaybooksQuery()))
                .thenReturn(new ExistsAnyPlaybooksResponse(true));

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.post("/awx/notification/project/1/success")
                .with(SecurityMockMvcRequestPostProcessors.anonymous());

        this.mockMvc.perform(request)
                .andDo(MockMvcResultHandlers.log())
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void whenRequestIsUserThenExpectOk() throws Exception {

        Mockito.when(service.existsAnyPlaybooks(new ExistsAnyPlaybooksQuery()))
                .thenReturn(new ExistsAnyPlaybooksResponse(true));

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.post("/awx/notification/project/1/success")
                .with(SecurityMockMvcRequestPostProcessors.user("user"));

        this.mockMvc.perform(request)
                .andDo(MockMvcResultHandlers.log())
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void whenPlaybooksExistsThenExpectCommandNotCalled() throws Exception {

        Mockito.when(service.existsAnyPlaybooks(new ExistsAnyPlaybooksQuery()))
                .thenReturn(new ExistsAnyPlaybooksResponse(true));

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.post("/awx/notification/project/1/success")
                .with(SecurityMockMvcRequestPostProcessors.user("user"));

        this.mockMvc.perform(request);

        Mockito.verify(commandGateway, Mockito.times(0)).sendAndWait(Mockito.any(AwxPlaybookCreateCommand.class));
    }

    @Test
    public void whenPlaybooksExistsThenExpectCommandCalled() throws Exception {

        Mockito.when(service.existsAnyPlaybooks(new ExistsAnyPlaybooksQuery()))
                .thenReturn(new ExistsAnyPlaybooksResponse(false));

        ProjectProjection projection = new ProjectProjection(UUID.randomUUID().toString(), 1L);
        Mockito.when(service.getProjectByAwxId(new FetchProjectByAwxIdQuery(1L)))
                .thenReturn(new FetchProjectByAwxIdResponse(projection));

        Mockito.when(playbookFeignService.getPlaybooks(1L)).thenReturn(Collections.singletonList("playbook"));

        Mockito.when(commandGateway.sendAndWait(Mockito.any(AwxPlaybookCreateCommand.class))).thenReturn(UUID.randomUUID());

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.post("/awx/notification/project/1/success")
                .with(SecurityMockMvcRequestPostProcessors.user("user"));

        this.mockMvc.perform(request);

        Mockito.verify(commandGateway, Mockito.times(1)).sendAndWait(Mockito.any(AwxPlaybookCreateCommand.class));
    }
}
