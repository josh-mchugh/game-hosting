package com.example.demo.web.awx.service;

import com.example.demo.awx.playbook.feign.IPlaybookFeignService;
import com.example.demo.awx.project.entity.model.AwxProject;
import com.example.demo.sample.SampleBuilder;
import com.example.demo.web.awx.service.model.PlaybookCreateRequest;
import com.google.common.collect.ImmutableList;
import feign.FeignException;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;

import javax.transaction.Transactional;
import java.util.Collections;
import java.util.UUID;

@SpringBootTest
@Transactional
@ActiveProfiles("test")
public class AwxControllerPlaybookServiceTest {

    @Autowired
    private IAwxControllerPlaybookService awxControllerPlaybookService;

    @Autowired
    private SampleBuilder sampleBuilder;

    @MockBean
    private IPlaybookFeignService playbookFeignService;

    @MockBean
    private CommandGateway commandGateway;

    private AwxProject awxProject;

    @BeforeEach
    public void setup() {

        awxProject = sampleBuilder.builder()
                .awxOrganization()
                .awxGitlabCredential()
                .awxProject()
                .build()
                .getAwxProject();
    }

    @Test
    public void whenCreatePlayBooksHasValidRequestAndNonExitsThenReturnPlaybooks() {

        Mockito.when(playbookFeignService.getPlaybooks(Mockito.anyLong())).thenReturn(Collections.singletonList("cowsay-playbook.yml"));
        Mockito.when(commandGateway.sendAndWait(Mockito.any())).thenReturn(UUID.randomUUID());

        PlaybookCreateRequest request = new PlaybookCreateRequest(awxProject.getAwxId());

        ImmutableList<Object> awxPlaybooks = awxControllerPlaybookService.handleCreatePlaybooks(request);

        Assertions.assertEquals(1L, awxPlaybooks.size());
    }

    @Test
    public void whenCreatePlaybooksAlreadyExistsThenReturnEmptyList() {

        sampleBuilder.builder()
                .awxPlaybook()
                .build();

        PlaybookCreateRequest request = new PlaybookCreateRequest(awxProject.getAwxId());

        ImmutableList<Object> awxPlaybooks = awxControllerPlaybookService.handleCreatePlaybooks(request);

        Assertions.assertEquals(0L, awxPlaybooks.size());
    }

    @Test
    public void whenCreatePlaybooksWithNullProjectIdThenThrowException() {

        PlaybookCreateRequest request = new PlaybookCreateRequest(null);

        Assertions.assertThrows(IllegalArgumentException.class, () -> awxControllerPlaybookService.handleCreatePlaybooks(request));
    }

    @Test
    public void whenCreatePlaybooksHasNullParamThenThrowException() {

        Assertions.assertThrows(NullPointerException.class, () -> awxControllerPlaybookService.handleCreatePlaybooks(null));
    }

    @Test
    public void whenCreatePlaybooksWhenPlaybookClientThrowsFeignExceptionThenThrowException() {

        Mockito.when(playbookFeignService.getPlaybooks(Mockito.anyLong())).thenThrow(FeignException.FeignClientException.class);

        PlaybookCreateRequest request = new PlaybookCreateRequest(awxProject.getAwxId());

        Assertions.assertThrows(FeignException.FeignClientException.class, () -> awxControllerPlaybookService.handleCreatePlaybooks(request));
    }
}
