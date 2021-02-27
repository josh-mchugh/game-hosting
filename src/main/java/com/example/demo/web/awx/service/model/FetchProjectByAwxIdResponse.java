package com.example.demo.web.awx.service.model;

import com.example.demo.web.awx.service.projection.ProjectProjection;
import lombok.AllArgsConstructor;
import lombok.Value;

@Value
@AllArgsConstructor
public class FetchProjectByAwxIdResponse {

    ProjectProjection project;
}
