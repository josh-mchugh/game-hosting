package com.example.demo.web.project.service;

import com.example.demo.web.project.service.model.ProjectDetails;
import com.example.demo.web.project.service.model.ProjectInstanceStartRequest;
import com.example.demo.web.project.service.model.ProjectInstanceStopRequest;

public interface IProjectControllerService {

    ProjectDetails getProjectDetails(String id);

    void handleProjectInstanceStart(ProjectInstanceStartRequest request);

    void handleProjectInstanceStop(ProjectInstanceStopRequest request);
}
