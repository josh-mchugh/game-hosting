package com.example.demo.awx.project.feign;

import com.example.demo.awx.project.feign.model.ProjectApi;
import com.example.demo.awx.project.feign.model.ProjectCreateApi;
import com.example.demo.framework.properties.AwxConfig;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class ProjectFeignServiceCreateProjectTest {

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
    public void whenServiceCreateProjectThenReturnProject() {

        ProjectApi expected = new ProjectApi();

        Mockito.when(projectClient.createProject(Mockito.anyLong(), Mockito.any())).thenReturn(expected);

        ProjectFeignServiceImpl projectFeignService = new ProjectFeignServiceImpl(awxConfig, projectClient);

        ProjectApi response = projectFeignService.createProject(ProjectCreateApi.builder().build());

        Assertions.assertEquals(expected, response);
    }
}
