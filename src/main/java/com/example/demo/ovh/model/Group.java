package com.example.demo.ovh.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class Group {

    private String id;
    private String name;

    @JsonProperty("instance_ids")
    private List<String> instanceIds;

    private String region;
    private String type;
}
