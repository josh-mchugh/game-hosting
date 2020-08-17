package com.example.demo.ovh.feign.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class OvhInstanceGroupApiResponse {

    private String id;
    private String name;

    @JsonProperty("instance_ids")
    private List<String> instanceIds;

    private String region;
    private String type;
}
