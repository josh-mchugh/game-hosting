package com.example.demo.web.project.projection.service.model;

import com.example.demo.web.project.projection.service.projection.AwxHostProjection;
import lombok.AllArgsConstructor;
import lombok.Value;

@Value
@AllArgsConstructor
public class FetchAwxHostByInstanceOvhIdResponse {

    AwxHostProjection awxHost;
}
