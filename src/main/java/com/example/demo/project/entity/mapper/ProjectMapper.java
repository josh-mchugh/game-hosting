package com.example.demo.project.entity.mapper;

import com.example.demo.project.entity.ProjectEntity;
import com.example.demo.project.entity.model.Project;

public class ProjectMapper {

    public static Project map(ProjectEntity entity) {

        if (entity == null) {

            return null;
        }

        return Project.builder()
                .id(entity.getUUID())
                .name(entity.getName())
                .status(entity.getStatus())
                .state(entity.getState())
                .build();
    }
}
