package com.example.demo.web.project.create.query.model;

import com.example.demo.project.entity.ProjectState;
import com.example.demo.project.entity.ProjectStatus;
import lombok.AllArgsConstructor;
import lombok.Value;

@Value
@AllArgsConstructor
public class FetchProjectStatusAndStateResponse {

    ProjectStatus status;
    ProjectState state;
}
