package com.example.demo.project.aggregate.saga.projection.project;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Value;

import java.util.UUID;

@Value
public class ProjectCreateInstanceDetailsProjection {

    String regionName;
    UUID flavorId;
    String flavorOvhId;
    UUID imageId;
    String imageOvhId;
    UUID instanceGroupId;
    String instanceGroupOvhId;

    @QueryProjection
    public ProjectCreateInstanceDetailsProjection(String regionName, String flavorId, String flavorOvhId, String imageId, String imageOvhId, String instanceGroupId, String instanceGroupOvhId) {
        this.regionName = regionName;
        this.flavorId = UUID.fromString(flavorId);
        this.flavorOvhId = flavorOvhId;
        this.imageId = UUID.fromString(imageId);
        this.imageOvhId = imageOvhId;
        this.instanceGroupId = UUID.fromString(instanceGroupId);
        this.instanceGroupOvhId = instanceGroupOvhId;
    }
}
