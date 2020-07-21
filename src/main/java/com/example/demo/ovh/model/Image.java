package com.example.demo.ovh.model;

import lombok.Data;

import java.util.List;

@Data
public class Image {

    private String id;
    private String name;
    private String creationDate;
    private String flavorType;
    private Long minDisk;
    private Long minRam;
    private PlanCode planCode;
    private String region;
    private Double size;
    private String status;
    private List<String> tags;
    private String type;
    private String user;
    private String visibility;
}
