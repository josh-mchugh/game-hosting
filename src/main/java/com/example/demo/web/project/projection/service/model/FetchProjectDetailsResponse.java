package com.example.demo.web.project.projection.service.model;

import com.example.demo.game.entity.GameType;
import com.example.demo.ovh.instance.entity.InstanceStatus;
import lombok.Builder;
import lombok.Value;

@Value
@Builder(builderClassName = "Builder")
public class FetchProjectDetailsResponse {

    String name;
    GameType gameType;
    String instanceId;
    InstanceStatus instanceStatus;
    String ip4Address;
}
