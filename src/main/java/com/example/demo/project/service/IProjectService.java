package com.example.demo.project.service;

import com.example.demo.project.model.Project;
import com.example.demo.project.service.model.ProjectCreateRequest;

public interface IProjectService {

    Project handleProjectCreate(ProjectCreateRequest projectCreateRequest);
}
