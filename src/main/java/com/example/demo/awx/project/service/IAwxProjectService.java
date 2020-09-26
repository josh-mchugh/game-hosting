package com.example.demo.awx.project.service;

import com.example.demo.awx.project.model.AwxProject;
import com.example.demo.awx.project.service.model.AwxProjectCreateRequest;

public interface IAwxProjectService {

    boolean existsAny();

    AwxProject getByProjectId(Long projectId);

    AwxProject handleCreateRequest(AwxProjectCreateRequest request);
}
