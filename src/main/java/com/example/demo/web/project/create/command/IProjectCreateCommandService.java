package com.example.demo.web.project.create.command;

import com.example.demo.web.project.create.command.model.ProjectCreateRequest;
import com.example.demo.web.project.create.command.model.ProjectCreateResponse;

public interface IProjectCreateCommandService {

    ProjectCreateResponse handleProjectCreate(ProjectCreateRequest request);
}
