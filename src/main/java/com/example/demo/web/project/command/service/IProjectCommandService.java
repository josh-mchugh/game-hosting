package com.example.demo.web.project.command.service;

import com.example.demo.web.project.command.service.model.ProjectInstanceStartRequest;
import com.example.demo.web.project.command.service.model.ProjectInstanceStopRequest;

import java.util.concurrent.ExecutionException;

public interface IProjectCommandService {

    void handleProjectInstanceStart(ProjectInstanceStartRequest request) throws ExecutionException, InterruptedException;

    void handleProjectInstanceStop(ProjectInstanceStopRequest request) throws ExecutionException, InterruptedException;
}
