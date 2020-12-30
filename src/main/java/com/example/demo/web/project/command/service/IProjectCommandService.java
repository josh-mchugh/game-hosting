package com.example.demo.web.project.command.service;

import com.example.demo.web.project.command.service.model.ProjectInstanceStartRequest;
import com.example.demo.web.project.command.service.model.ProjectInstanceStopRequest;

public interface IProjectCommandService {

    void handleProjectInstanceStart(ProjectInstanceStartRequest request);

    void handleProjectInstanceStop(ProjectInstanceStopRequest request);
}
