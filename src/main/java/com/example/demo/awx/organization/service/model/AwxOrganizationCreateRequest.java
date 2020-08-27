package com.example.demo.awx.organization.service.model;

import lombok.Builder;
import lombok.Value;

@Value
@Builder(builderClassName = "Builder")
public class AwxOrganizationCreateRequest {

    Long organizationId;
    String name;
    String description;
}
