package com.example.demo.web.project.dashboard.projection.model;

import com.example.demo.game.entity.GameType;
import com.example.demo.ovh.instance.entity.InstanceStatus;
import com.example.demo.project.entity.ProjectState;
import com.example.demo.project.entity.ProjectStatus;
import lombok.Builder;
import lombok.Value;

@Value
@Builder(builderClassName = "Builder")
public class FetchProjectDetailsResponse {

    String name;
    GameType gameType;
    ProjectStatus status;
    ProjectState state;
    String instanceId;
    InstanceStatus instanceStatus;
    String ip4Address;
}
