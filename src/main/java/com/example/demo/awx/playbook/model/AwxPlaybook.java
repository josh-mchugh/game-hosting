package com.example.demo.awx.playbook.model;

import com.example.demo.awx.playbook.entity.PlaybookType;
import lombok.Builder;
import lombok.Value;

@Value
@Builder(builderClassName = "Builder")
public class AwxPlaybook {

    String id;
    String name;
    PlaybookType type;
}
