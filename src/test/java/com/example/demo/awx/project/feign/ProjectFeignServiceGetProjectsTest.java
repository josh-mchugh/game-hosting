package com.example.demo.awx.project.feign;

import com.example.demo.awx.feign.ListResponse;
import com.example.demo.awx.project.feign.model.ProjectApi;
import com.example.demo.framework.properties.AwxConfig;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Arrays;
import java.util.List;

public class ProjectFeignServiceGetProjectsTest {

    private AwxConfig awxConfig;
    private ProjectClient projectClient;

    @BeforeEach
    public void setup() {

        AwxConfig.Organization organization = new AwxConfig.Organization();
        organization.setId(1L);

        awxConfig = new AwxConfig();
        awxConfig.setOrganization(organization);

        projectClient = Mockito.mock(ProjectClient.class);
    }

    @Test
    public void whenServiceGetsProjectsThenReturnProjects() {

        List<ProjectApi> expected = Arrays.asList(new ProjectApi(), new ProjectApi());

        ListResponse<ProjectApi> apiListResponse = new ListResponse<>();
        apiListResponse.setResults(expected);

        Mockito.when(projectClient.getProjects(Mockito.anyLong())).thenReturn(apiListResponse);

        ProjectFeignServiceImpl projectFeignService = new ProjectFeignServiceImpl(awxConfig, projectClient);

        List<ProjectApi> response = projectFeignService.getProjects().getResults();

        Assertions.assertEquals(expected, response);
    }
}
