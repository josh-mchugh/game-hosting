package com.example.demo.framework.seed.awx.project;

import com.example.demo.awx.feign.ListResponse;
import com.example.demo.awx.notification.feign.NotificationFeignService;
import com.example.demo.awx.notification.feign.model.NotificationApi;
import com.example.demo.awx.notification.feign.model.NotificationConfiguration;
import com.example.demo.awx.project.feign.ProjectFeignService;
import com.example.demo.awx.project.feign.model.ProjectApi;
import com.example.demo.framework.seed.awx.project.projection.model.ExistsAnyAwxProjectQuery;
import com.example.demo.framework.seed.awx.project.projection.model.ExistsAnyAwxProjectResponse;
import com.example.demo.framework.seed.awx.project.projection.model.FetchAwxCredentialByNameQuery;
import com.example.demo.framework.seed.awx.project.projection.model.FetchAwxCredentialByNameResponse;
import com.example.demo.framework.seed.awx.project.projection.model.FetchAwxOrganizationIdByAwxIdQuery;
import com.example.demo.framework.seed.awx.project.projection.model.FetchAwxOrganizationIdByAwxIdResponse;
import com.example.demo.framework.seed.awx.project.projection.projection.AwxCredentialProjection;
import com.example.demo.sample.SampleBuilder;
import com.example.demo.sample.SampleData;
import com.google.common.collect.ImmutableList;
import org.axonframework.queryhandling.QueryGateway;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;

import javax.transaction.Transactional;
import java.util.Collections;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@SpringBootTest
@Transactional
@ActiveProfiles("test")
public class AwxProjectSeedServiceTest {

    @Autowired
    private AwxProjectSeedService awxProjectSeedService;

    @Autowired
    private SampleBuilder sampleBuilder;

    @MockBean
    private ProjectFeignService projectFeignService;

    @MockBean
    private NotificationFeignService notificationFeignService;

    @MockBean
    private QueryGateway queryGateway;

    @Test
    public void whenAwxProjectExistsThenDataDoesNotExistsReturnFalse() throws ExecutionException, InterruptedException {

        Mockito.when(queryGateway.query(new ExistsAnyAwxProjectQuery(), ExistsAnyAwxProjectResponse.class))
                .thenReturn(CompletableFuture.completedFuture(new ExistsAnyAwxProjectResponse(true)));

        Assertions.assertFalse(awxProjectSeedService.dataNotExists());
    }

    @Test
    public void whenAwxProjectDoesNotExistsThenDataDoesNotExistsReturnTrue() throws ExecutionException, InterruptedException {

        Mockito.when(queryGateway.query(new ExistsAnyAwxProjectQuery(), ExistsAnyAwxProjectResponse.class))
                .thenReturn(CompletableFuture.completedFuture(new ExistsAnyAwxProjectResponse(false)));

        Assertions.assertTrue(awxProjectSeedService.dataNotExists());
    }

    @Test
    public void whenAwxProjectClientReturnsMatchingNameThenReturnList() throws ExecutionException, InterruptedException {

        SampleData sampleData = sampleBuilder.builder()
                .awxOrganization()
                .awxGitlabCredential()
                .build();

        ProjectApi projectApi = new ProjectApi();
        projectApi.setId(1L);
        projectApi.setName("Game Hosting Project");
        projectApi.setOrganizationId(sampleData.getAwxOrganization().getAwxId());
        projectApi.setCredentialId(sampleData.getAwxCredential().getAwxId());
        projectApi.setScmType("git");
        projectApi.setScmBranch("master");
        projectApi.setScmUrl("url");

        ListResponse<ProjectApi> clientResponse = new ListResponse<>();
        clientResponse.setResults(Collections.singletonList(projectApi));

        Mockito.when(projectFeignService.getProjects()).thenReturn(clientResponse);

        AwxCredentialProjection awxCredentialProjection = new AwxCredentialProjection(UUID.randomUUID().toString(), 1L);
        Mockito.when(queryGateway.query(new FetchAwxCredentialByNameQuery(sampleData.getAwxCredential().getName()), FetchAwxCredentialByNameResponse.class))
                .thenReturn(CompletableFuture.completedFuture(new FetchAwxCredentialByNameResponse(awxCredentialProjection)));

        Mockito.when(queryGateway.query(new FetchAwxOrganizationIdByAwxIdQuery(sampleData.getAwxOrganization().getAwxId()), FetchAwxOrganizationIdByAwxIdResponse.class))
                .thenReturn(CompletableFuture.completedFuture(new FetchAwxOrganizationIdByAwxIdResponse(UUID.randomUUID())));

        ImmutableList<Object> awxProjects = awxProjectSeedService.initializeData();

        Assertions.assertEquals(1, awxProjects.size());
    }

    @Test
    public void whenAwxProjectClientDoesNotReturnMatchingNameThenReturnList() throws ExecutionException, InterruptedException {

        SampleData sampleData = sampleBuilder.builder()
                .awxOrganization()
                .awxGitlabCredential()
                .build();

        ProjectApi wrongProjectApi = new ProjectApi();
        wrongProjectApi.setId(2L);
        wrongProjectApi.setName("Wrong Project");
        wrongProjectApi.setOrganizationId(sampleData.getAwxOrganization().getAwxId());
        wrongProjectApi.setCredentialId(sampleData.getAwxCredential().getAwxId());
        wrongProjectApi.setScmType("git");
        wrongProjectApi.setScmBranch("master");
        wrongProjectApi.setScmUrl("url");

        ListResponse<ProjectApi> clientResponse = new ListResponse<>();
        clientResponse.setResults(Collections.singletonList(wrongProjectApi));

        Mockito.when(projectFeignService.getProjects()).thenReturn(clientResponse);

        ProjectApi projectApi = new ProjectApi();
        projectApi.setId(2L);
        projectApi.setName("Game Hosting Project");
        projectApi.setOrganizationId(sampleData.getAwxOrganization().getAwxId());
        projectApi.setCredentialId(sampleData.getAwxCredential().getAwxId());
        projectApi.setScmType("git");
        projectApi.setScmBranch("master");
        projectApi.setScmUrl("url");

        Mockito.when(projectFeignService.createProject(Mockito.any())).thenReturn(projectApi);

        NotificationConfiguration configuration = NotificationConfiguration.builder()
                .url("url")
                .build();

        NotificationApi notificationApi = new NotificationApi();
        notificationApi.setId(1L);
        notificationApi.setName("Notification Name");
        notificationApi.setDescription("Notification Description");
        notificationApi.setOrganizationId(sampleData.getAwxOrganization().getAwxId());
        notificationApi.setType("webhook");
        notificationApi.setNotificationConfiguration(configuration);

        Mockito.when(notificationFeignService.createSuccessNotificationForProject(Mockito.anyLong(), Mockito.any())).thenReturn(notificationApi);

        AwxCredentialProjection awxCredentialProjection = new AwxCredentialProjection(UUID.randomUUID().toString(), 1L);
        Mockito.when(queryGateway.query(new FetchAwxCredentialByNameQuery(sampleData.getAwxCredential().getName()), FetchAwxCredentialByNameResponse.class))
                .thenReturn(CompletableFuture.completedFuture(new FetchAwxCredentialByNameResponse(awxCredentialProjection)));

        Mockito.when(queryGateway.query(new FetchAwxOrganizationIdByAwxIdQuery(sampleData.getAwxOrganization().getAwxId()), FetchAwxOrganizationIdByAwxIdResponse.class))
                .thenReturn(CompletableFuture.completedFuture(new FetchAwxOrganizationIdByAwxIdResponse(sampleData.getAwxOrganization().getId())));

        ImmutableList<Object> awxProjects = awxProjectSeedService.initializeData();

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
