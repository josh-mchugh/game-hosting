package com.example.demo.awx.playbook.entity.model;

import com.example.demo.awx.playbook.entity.PlaybookType;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder(builderClassName = "Builder")
public class AwxPlaybook {

    String id;
    String name;
    PlaybookType type;
}
