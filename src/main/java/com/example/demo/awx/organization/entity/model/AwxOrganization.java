package com.example.demo.awx.organization.entity.model;

import lombok.Builder;
import lombok.Value;

@Value
@Builder(builderClassName = "Builder")
public class AwxOrganization {

    String id;
    Long awxId;
    String name;
    String description;
}
