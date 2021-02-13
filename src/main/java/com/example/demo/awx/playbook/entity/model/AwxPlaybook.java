package com.example.demo.awx.playbook.entity.model;

import com.example.demo.awx.playbook.entity.PlaybookType;
import lombok.Builder;
import lombok.Value;

import java.util.UUID;

@Value
@Builder(builderClassName = "Builder")
public class AwxPlaybook {

    UUID id;
    String name;
    PlaybookType type;
}
