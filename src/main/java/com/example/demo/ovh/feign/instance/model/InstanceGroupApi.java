package com.example.demo.ovh.feign.instance.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class InstanceGroupApi {

    private String id;
    private String name;

    @JsonProperty("instance_ids")
    private List<String> instanceIds;

    private String region;
    private String type;
}
