package com.example.demo.awx.project.feign;

import com.example.demo.awx.feign.ListResponse;
import com.example.demo.awx.project.feign.model.ProjectApi;
import com.example.demo.awx.project.feign.model.ProjectCreateApi;
import com.example.demo.framework.properties.AwxConfig;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ProjectFeignServiceImpl implements ProjectFeignService {

    private final AwxConfig awxConfig;
    private final ProjectClient projectClient;

    @Override
    public ListResponse<ProjectApi> getProjects() {

        return projectClient.getProjects(awxConfig.getOrganization().getId());
    }

    @Override
    public ProjectApi createProject(ProjectCreateApi body) {

        return projectClient.createProject(awxConfig.getOrganization().getId(), body);
    }
}
