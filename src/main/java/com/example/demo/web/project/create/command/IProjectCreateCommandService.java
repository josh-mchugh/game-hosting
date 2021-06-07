package com.example.demo.web.project.create.command;

import com.example.demo.web.project.create.command.model.ProjectAddBillingRequest;
import com.example.demo.web.project.create.command.model.ProjectAddServerRequest;
import com.example.demo.web.project.create.command.model.ProjectAddRegionRequest;
import com.example.demo.web.project.create.command.model.ProjectCreateRequest;
import com.example.demo.web.project.create.command.model.ProjectCreateResponse;

import java.util.concurrent.ExecutionException;

public interface IProjectCreateCommandService {

    ProjectCreateResponse handleCreate(ProjectCreateRequest request);

    void handleAddRegion(ProjectAddRegionRequest request);

    void handleAddServer(ProjectAddServerRequest request) throws ExecutionException, InterruptedException;

    void handleAddBilling(ProjectAddBillingRequest request);
}
