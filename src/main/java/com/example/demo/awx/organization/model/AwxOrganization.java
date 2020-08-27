package com.example.demo.awx.organization.model;

import lombok.Builder;
import lombok.Value;

@Value
@Builder(builderClassName = "Builder")
public class AwxOrganization {

    String id;
    Long organizationId;
    String name;
    String description;
}
