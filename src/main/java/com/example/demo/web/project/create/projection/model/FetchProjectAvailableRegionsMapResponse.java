package com.example.demo.web.project.create.projection.model;

import com.google.common.collect.ImmutableMap;
import lombok.AllArgsConstructor;
import lombok.Value;

@Value
@AllArgsConstructor
public class FetchProjectAvailableRegionsMapResponse {

    ImmutableMap<String, String> availableRegions;
}
