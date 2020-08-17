package com.example.demo.ovh.instance.service.model;

import com.example.demo.ovh.instance.entity.InstanceStatus;
import lombok.Builder;
import lombok.Value;

import java.time.LocalDateTime;

@Value
@Builder(builderClassName = "Builder")
public class InstanceCreateRequest {

    String instanceId;
    String flavorId;
    String imageId;
    String groupId;
    String sshKeyId;
    String name;
    InstanceStatus status;
    LocalDateTime instanceCreatedDate;
    String ip4Address;
    String ip6Address;
}
