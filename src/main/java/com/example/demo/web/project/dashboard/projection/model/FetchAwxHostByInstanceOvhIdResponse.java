package com.example.demo.web.project.dashboard.projection.model;

import com.example.demo.web.project.dashboard.projection.projection.AwxHostProjection;
import lombok.AllArgsConstructor;
import lombok.Value;

@Value
@AllArgsConstructor
public class FetchAwxHostByInstanceOvhIdResponse {

    AwxHostProjection awxHost;
}
