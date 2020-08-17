package com.example.demo.ovh.feign.model;

import com.example.demo.framework.deserializer.DateTimeDeserializer;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class OvhImageApiResponse {

    @JsonProperty("id")
    private String imageId;
    private String name;
    @JsonDeserialize(using = DateTimeDeserializer.class)
    private LocalDateTime creationDate;
    private String flavorType;
    private Integer minDisk;
    private Integer minRam;
    private OvhPlanCodeApi planCode;
    @JsonProperty("region")
    private String regionName;
    private Double size;
    private String status;
    private List<String> tags;
    private String type;
    private String user;
    private String visibility;
}
