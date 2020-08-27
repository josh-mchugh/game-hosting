package com.example.demo.awx.project.mapper;

import com.example.demo.awx.project.entity.AwxProjectEntity;
import com.example.demo.awx.project.model.AwxProject;

public class AwxProjectMapper {

    public static AwxProject map(AwxProjectEntity entity) {

        if (entity == null) {

            return null;
        }

        return AwxProject.builder()
                .id(entity.getId())
                .projectId(entity.getProjectId())
                .name(entity.getName())
                .description(entity.getDescription())
                .scmType(entity.getScmType())
                .scmUrl(entity.getScmUrl())
                .scmBranch(entity.getScmBranch())
                .build();
    }
}
