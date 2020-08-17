package com.example.demo.ovh.instance.service.model;

import lombok.Builder;
import lombok.Value;

@Value
@Builder(builderClassName = "Builder")
public class InstanceGroupCreateRequest {

    String instanceGroupId;
    String projectId;
    String name;
    String type;
}
