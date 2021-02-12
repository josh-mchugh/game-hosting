package com.example.demo.awx.organization.entity.model;

import lombok.Builder;
import lombok.Value;

import java.util.UUID;

@Value
@Builder(builderClassName = "Builder")
public class AwxOrganization {

    UUID id;
    Long awxId;
    String name;
    String description;
}
