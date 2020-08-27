package com.example.demo.awx.playbook.service.model;

import lombok.Builder;
import lombok.Value;

@Value
@Builder(builderClassName = "Builder")
public class AwxPlaybookCreateRequest {

    Long projectId;
    String name;
}
