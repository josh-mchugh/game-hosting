package com.example.demo.awx.project.entity.mapper;

import com.example.demo.awx.project.entity.AwxProjectEntity;
import com.example.demo.awx.project.entity.model.AwxProject;

public class AwxProjectMapper {

    public static AwxProject map(AwxProjectEntity entity) {

        if (entity == null) {

            return null;
        }

        return AwxProject.builder()
                .id(entity.getId())
                .awxId(entity.getAwxId())
                .name(entity.getName())
                .description(entity.getDescription())
                .scmType(entity.getScmType())
                .scmUrl(entity.getScmUrl())
                .scmBranch(entity.getScmBranch())
                .build();
    }
}
