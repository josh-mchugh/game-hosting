package com.example.demo.project.projection.model;

import com.google.common.collect.ImmutableList;
import lombok.AllArgsConstructor;
import lombok.Value;

@Value
@AllArgsConstructor
public class FetchProjectDashboardProjection {

    ImmutableList<ProjectDashboardProjection> projects;
}
