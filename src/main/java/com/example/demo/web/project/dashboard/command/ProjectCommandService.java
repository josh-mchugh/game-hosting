package com.example.demo.web.project.dashboard.command;

import com.example.demo.web.project.dashboard.command.model.ProjectInstanceStartRequest;
import com.example.demo.web.project.dashboard.command.model.ProjectInstanceStopRequest;

import java.util.concurrent.ExecutionException;

public interface ProjectCommandService {

    void handleProjectInstanceStart(ProjectInstanceStartRequest request) throws ExecutionException, InterruptedException;

    void handleProjectInstanceStop(ProjectInstanceStopRequest request) throws ExecutionException, InterruptedException;
}
