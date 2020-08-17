package com.example.demo.ovh.feign.instance.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Value;

@Value
@Builder(builderClassName = "builder")
public class InstanceCreateApi {

    String name;
    String flavorId;
    String region;
    String imageId;
    @JsonProperty("autobackup")
    AutoBackup autoBackup;
    String groupId;
    Boolean monthlyBilling;
    String sshKeyId;
    String userData;
    String volumeId;

    @Value
    @Builder(builderClassName = "builder")
    public static class AutoBackup {

        String cron;
        Long rotation;
    }

    @Value
    @Builder(builderClassName = "builder")
    public static class Network {

        String ip;
        String networkId;
    }
}
