package com.example.demo.awx.project.projection;

import com.example.demo.awx.project.entity.model.AwxProject;

public interface IAwxProjectProjector {

    boolean existsAny();

    AwxProject getByProjectId(Long projectId);
}
