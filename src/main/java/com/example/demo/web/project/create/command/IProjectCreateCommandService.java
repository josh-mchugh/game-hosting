package com.example.demo.web.project.create.command;

import com.example.demo.web.project.create.command.model.ProjectAddFlavorRequest;
import com.example.demo.web.project.create.command.model.ProjectAddRegionRequest;
import com.example.demo.web.project.create.command.model.ProjectCreateRequest;
import com.example.demo.web.project.create.command.model.ProjectCreateResponse;

public interface IProjectCreateCommandService {

    ProjectCreateResponse handleCreate(ProjectCreateRequest request);

    void handleAddRegion(ProjectAddRegionRequest request);

    void handleAddFlavor(ProjectAddFlavorRequest request);
}
