package com.example.demo.awx.project.feign;

import com.example.demo.awx.feign.ListResponse;
import com.example.demo.awx.project.feign.model.ProjectApi;
import com.example.demo.awx.project.feign.model.ProjectCreateApi;

public interface IProjectFeignService {

    ListResponse<ProjectApi> getProjects();

    ProjectApi createProject(ProjectCreateApi body);
}
