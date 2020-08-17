package com.example.demo.ovh.flavor.scheduler.service.model;

import com.example.demo.ovh.flavor.model.Flavor;
import com.google.common.collect.ImmutableList;
import lombok.Builder;
import lombok.Singular;
import lombok.Value;

@Value
@Builder(builderClassName = "Builder")
public class ProcessedFlavorsResponse {

    @Singular
    ImmutableList<Flavor> updatedFlavors;

    @Singular
    ImmutableList<Flavor> createdFlavors;
}
