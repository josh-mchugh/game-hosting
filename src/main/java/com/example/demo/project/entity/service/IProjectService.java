package com.example.demo.project.entity.service;

import com.example.demo.project.aggregate.event.ProjectCreatedEvent;
import com.example.demo.project.aggregate.event.ProjectRegionAddedEvent;
import com.example.demo.project.entity.model.Project;

public interface IProjectService {

    Project handleCreated(ProjectCreatedEvent event);

    Project handleRegionAdded(ProjectRegionAddedEvent event);
}
