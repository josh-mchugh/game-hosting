package com.example.demo.project.mapper;

import com.example.demo.project.entity.ProjectEntity;
import com.example.demo.project.model.Project;

public class ProjectMapper {

    public static Project map(ProjectEntity entity) {

        if (entity == null) {

            return null;
        }

        return Project.builder()
                .id(entity.getId())
                .name(entity.getName())
                .status(entity.getStatus())
                .state(entity.getState())
                .build();
    }
}
