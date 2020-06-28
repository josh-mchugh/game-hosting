package com.example.demo.controller.ansible.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class Ping {

    @JsonProperty("ha")
    private Boolean isHA;

    @JsonProperty("version")
    private String version;

    @JsonProperty("active_node")
    private String activeNode;

    @JsonProperty("install_uuid")
    private String installUUID;

    @JsonProperty("instances")
    private List<Instance> instances;

    @JsonProperty("instance_groups")
    private List<InstanceGroup> instanceGroups;

    @Data
    public static class Instance {

        @JsonProperty("node")
        private String node;

        @JsonProperty("uuid")
        private String uuid;

        @JsonProperty("heartbeat")
        private String heartbeat;

        @JsonProperty("capacity")
        private Integer capacity;

        @JsonProperty("version")
        private String version;
    }

    @Data
    public static class InstanceGroup {

        @JsonProperty("name")
        private String name;

        @JsonProperty("capacity")
        private Integer capacity;

        @JsonProperty("instances")
        private List<String> instances;
    }
}
