package com.example.demo.framework.seed.service;

import com.example.demo.awx.credential.model.AwxCredential;
import com.example.demo.awx.credential.service.IAwxCredentialService;
import com.example.demo.awx.feign.common.ListResponse;
import com.example.demo.awx.feign.project.ProjectClient;
import com.example.demo.awx.feign.project.model.ProjectApi;
import com.example.demo.awx.feign.project.model.ProjectCreateApi;
import com.example.demo.awx.project.model.AwxProject;
import com.example.demo.awx.project.service.IAwxProjectService;
import com.example.demo.awx.project.service.model.AwxProjectCreateRequest;
import com.example.demo.framework.properties.AppConfig;
import com.example.demo.framework.seed.ISeedService;
import com.google.common.collect.ImmutableList;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class AwxProjectSeedService implements ISeedService<AwxProject> {

    private final AppConfig appConfig;
    private final IAwxCredentialService awxCredentialService;
    private final IAwxProjectService awxProjectService;
    private final ProjectClient projectClient;

    @Override
    public boolean dataNotExists() {

        return !awxProjectService.existsAny();
    }

    @Override
    public ImmutableList<AwxProject> initializeData() {

        ListResponse<ProjectApi> projectApis = projectClient.getProjects(appConfig.getAwx().getOrganization().getId());

        Optional<ProjectApi> projectApi = projectApis.getResults().stream()
                .filter(project -> project.getName().equals(appConfig.getAwx().getProject().getName()))
                .findFirst();

        if(projectApi.isPresent()) {

            AwxProjectCreateRequest request = createAwxProjectRequest(projectApi.get());

            return ImmutableList.of(awxProjectService.handleCreateRequest(request));
        }

        return ImmutableList.of(createNewAwxProject());
    }

    @Override
    public String type() {

        return "Awx Project";
    }

    @Override
    public Integer order() {

        return 9;
    }

    private ProjectApi createProjectApi() {

        AwxCredential awxCredential = awxCredentialService.getByName(appConfig.getAwx().getProject().getCredentialName());

        ProjectCreateApi projectCreateApi = ProjectCreateApi.builder()
                .credentialId(awxCredential.getCredentialId())
                .name(appConfig.getAwx().getProject().getName())
                .description(appConfig.getAwx().getProject().getDescription())
                .scmType(appConfig.getAwx().getProject().getScmType())
                .scmUrl(appConfig.getAwx().getProject().getScmUrl())
                .scmBranch(appConfig.getAwx().getProject().getScmBranch())
                .build();

        return projectClient.createProject(appConfig.getAwx().getOrganization().getId(), projectCreateApi);
    }

    private AwxProjectCreateRequest createAwxProjectRequest(ProjectApi projectApi) {

        return AwxProjectCreateRequest.builder()
                .organizationId(projectApi.getOrganizationId())
                .credentialId(projectApi.getCredentialId())
                .projectId(projectApi.getId())
                .name(projectApi.getName())
                .description(projectApi.getDescription())
                .scmType(projectApi.getScmType())
                .scmBranch(projectApi.getScmBranch())
                .scmUrl(projectApi.getScmUrl())
                .build();
    }

    private AwxProject createNewAwxProject() {

        ProjectApi api = createProjectApi();
        AwxProjectCreateRequest request = createAwxProjectRequest(api);

        return awxProjectService.handleCreateRequest(request);
    }
}
