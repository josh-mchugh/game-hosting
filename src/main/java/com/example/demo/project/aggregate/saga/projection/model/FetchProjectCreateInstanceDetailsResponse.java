package com.example.demo.project.aggregate.saga.projection.model;

import lombok.Builder;
import lombok.Value;

import java.util.UUID;

@Value
@Builder(builderClassName = "Builder")
public class FetchProjectCreateInstanceDetailsResponse {

    String regionName;
    UUID flavorId;
    String flavorOvhId;
    UUID imageId;
    String imageOvhId;
    UUID instanceGroupId;
    String instanceGroupOvhId;
}
