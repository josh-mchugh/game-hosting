package com.example.demo.web.project.service.model;

import com.example.demo.game.entity.GameType;
import com.example.demo.ovh.instance.entity.InstanceStatus;
import lombok.Builder;
import lombok.Value;

@Value
@Builder(builderClassName = "Builder")
public class ProjectDetails {

    String name;
    GameType gameType;
    String instanceId;
    InstanceStatus instanceStatus;
    String ip4Address;
}
