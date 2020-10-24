package com.example.demo.ovh.flavor.scheduler.service.model;

import com.google.common.collect.ImmutableList;
import lombok.Builder;
import lombok.Singular;
import lombok.Value;

@Value
@Builder(builderClassName = "Builder")
public class ProcessedFlavorsResponse {

    @Singular
    ImmutableList<Object> updatedFlavors;

    @Singular
    ImmutableList<Object> createdFlavors;
}
