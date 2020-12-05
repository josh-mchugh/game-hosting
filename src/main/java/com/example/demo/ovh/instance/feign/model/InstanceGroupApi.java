package com.example.demo.ovh.instance.feign.model;

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
