package com.example.demo.web.dashboard.service.model;

import lombok.Builder;
import lombok.Value;

@Value
@Builder(builderClassName = "Builder")
public class DashboardProjectCreateResponse {

    String projectId;
}
