package com.example.demo.ovh.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Project {

    @JsonProperty("project_id")
    private String projectId;
    private String expiration;
    private String creationDate;
    private String projectName;
    private String description;
    private String planCode;
    private Boolean unleash;
    private Long orderId;
    private String access;
    private String status;
    private Boolean manualQuota;
}
