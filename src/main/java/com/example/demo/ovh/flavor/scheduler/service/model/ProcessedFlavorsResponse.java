package com.example.demo.ovh.flavor.scheduler.service.model;

import lombok.Builder;
import lombok.Singular;
import lombok.Value;

import java.util.List;

@Value
@Builder(builderClassName = "Builder")
public class ProcessedFlavorsResponse {

    @Singular
    List<Object> updatedFlavors;

    @Singular
    List<Object> createdFlavors;
}
