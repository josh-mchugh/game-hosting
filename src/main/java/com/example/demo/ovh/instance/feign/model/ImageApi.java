package com.example.demo.ovh.instance.feign.model;

import com.example.demo.framework.deserializer.DateTimeDeserializer;
import com.example.demo.ovh.feign.PlanCodeApi;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ImageApi {

    @JsonProperty("id")
    private String imageId;
    private String name;
    @JsonDeserialize(using = DateTimeDeserializer.class)
    private LocalDateTime creationDate;
    private String flavorType;
    private Integer minDisk;
    private Integer minRam;
    private PlanCodeApi planCode;
    @JsonProperty("region")
    private String regionName;
    private Double size;
    private String status;
    private String type;
    private String user;
    private String visibility;
}
