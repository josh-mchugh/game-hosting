package com.example.demo.project.entity.service;

import com.example.demo.project.aggregate.event.ProjectBillingAddedEvent;
import com.example.demo.project.aggregate.event.ProjectCreatedEvent;
import com.example.demo.project.aggregate.event.ProjectServerAddedEvent;
import com.example.demo.project.aggregate.event.ProjectRegionAddedEvent;
import com.example.demo.project.aggregate.event.ProjectStateCreateInstanceUpdatedEvent;
import com.example.demo.project.entity.model.Project;

public interface ProjectService {

    Project handleCreated(ProjectCreatedEvent event);

    Project handleRegionAdded(ProjectRegionAddedEvent event);

    Project handleServerAdded(ProjectServerAddedEvent event);

    Project handleBillingAdded(ProjectBillingAddedEvent event);

    Project handleStateInstanceBuildUpdated(ProjectStateCreateInstanceUpdatedEvent event);
}
