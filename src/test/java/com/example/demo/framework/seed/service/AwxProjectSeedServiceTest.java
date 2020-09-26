package com.example.demo.framework.seed.service;

import com.example.demo.awx.feign.common.ListResponse;
import com.example.demo.awx.feign.notification.NotificationClient;
import com.example.demo.awx.feign.notification.model.NotificationApi;
import com.example.demo.awx.feign.notification.model.NotificationConfiguration;
import com.example.demo.awx.feign.project.ProjectClient;
import com.example.demo.awx.feign.project.model.ProjectApi;
import com.example.demo.awx.project.model.AwxProject;
import com.example.demo.sample.SampleBuilder;
import com.example.demo.sample.SampleData;
import com.google.common.collect.ImmutableList;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;

import javax.transaction.Transactional;
import java.util.Collections;

@SpringBootTest
@Transactional
@ActiveProfiles("test")
public class AwxProjectSeedServiceTest {

    @Autowired
    private AwxProjectSeedService awxProjectSeedService;

    @Autowired
    private SampleBuilder sampleBuilder;

    @MockBean
    private ProjectClient projectClient;

    @MockBean
    private NotificationClient notificationClient;

    @Test
    public void whenAwxProjectExistsThenDataDoesNotExistsReturnFalse() {

        sampleBuilder.builder()
                .awxOrganization()
                .awxCredential()
                .awxProject()
                .build();

        Assertions.assertFalse(awxProjectSeedService.dataNotExists());
    }

    @Test
    public void whenAwxProjectDoesNotExistsThenDataDoesNotExistsReturnTrue() {

        Assertions.assertTrue(awxProjectSeedService.dataNotExists());
    }

    @Test
    public void whenAwxProjectClientReturnsMatchingNameThenReturnList() {

        SampleData sampleData = sampleBuilder.builder()
                .awxOrganization()
                .awxGitlabCredential()
                .build();

        ProjectApi projectApi = new ProjectApi();
        projectApi.setId(1L);
        projectApi.setName("Game Hosting Project");
        projectApi.setOrganizationId(sampleData.getAwxOrganization().getOrganizationId());
        projectApi.setCredentialId(sampleData.getAwxCredential().getCredentialId());
        projectApi.setScmType("git");
        projectApi.setScmBranch("master");
        projectApi.setScmUrl("url");

        ListResponse<ProjectApi> clientResponse = new ListResponse<>();
        clientResponse.setResults(Collections.singletonList(projectApi));

        Mockito.when(projectClient.getProjects(Mockito.anyLong())).thenReturn(clientResponse);

        ImmutableList<AwxProject> awxProjects = awxProjectSeedService.initializeData();

        Assertions.assertEquals(1, awxProjects.size());
    }

    @Test
    public void whenAwxProjectClientDoesNotReturnMatchingNameThenReturnList() {

        SampleData sampleData = sampleBuilder.builder()
                .awxOrganization()
                .awxGitlabCredential()
                .build();

        ProjectApi wrongProjectApi = new ProjectApi();
        wrongProjectApi.setId(2L);
        wrongProjectApi.setName("Wrong Project");
        wrongProjectApi.setOrganizationId(sampleData.getAwxOrganization().getOrganizationId());
        wrongProjectApi.setCredentialId(sampleData.getAwxCredential().getCredentialId());
        wrongProjectApi.setScmType("git");
        wrongProjectApi.setScmBranch("master");
        wrongProjectApi.setScmUrl("url");

        ListResponse<ProjectApi> clientResponse = new ListResponse<>();
        clientResponse.setResults(Collections.singletonList(wrongProjectApi));

        Mockito.when(projectClient.getProjects(Mockito.anyLong())).thenReturn(clientResponse);

        ProjectApi projectApi = new ProjectApi();
        projectApi.setId(2L);
        projectApi.setName("Game Hosting Project");
        projectApi.setOrganizationId(sampleData.getAwxOrganization().getOrganizationId());
        projectApi.setCredentialId(sampleData.getAwxCredential().getCredentialId());
        projectApi.setScmType("git");
        projectApi.setScmBranch("master");
        projectApi.setScmUrl("url");

        Mockito.when(projectClient.createProject(Mockito.anyLong(), Mockito.any())).thenReturn(projectApi);

        NotificationApi notificationApi = new NotificationApi();
        notificationApi.setId(1L);
        notificationApi.setName("Notification Name");
        notificationApi.setDescription("Notification Description");
        notificationApi.setOrganizationId(sampleData.getAwxOrganization().getOrganizationId());
        notificationApi.setNotificationType("webhook");
        notificationApi.setNotificationConfiguration(new NotificationConfiguration("url"));

        Mockito.when(notificationClient.createSuccessNotificationForProject(Mockito.anyLong(), Mockito.any())).thenReturn(notificationApi);

        ImmutableList<AwxProject> awxProjects = awxProjectSeedService.initializeData();

        Assertions.assertEquals(1, awxProjects.size());
    }

    @Test
    public void whenTypeHasValueThenReturnValue() {

        Assertions.assertEquals("Awx Project", awxProjectSeedService.type());
    }

    @Test
    public void typeShouldNotBeNull() {

        Assertions.assertNotNull(awxProjectSeedService.type());
    }

    @Test
    public void whenOrderHasValueReturnValue() {

        Assertions.assertEquals(9, awxProjectSeedService.order());
    }

    @Test
    public void orderShouldNotBeNull() {

        Assertions.assertNotNull(awxProjectSeedService.order());
    }
}
