package com.example.demo.awx.organization.feign.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class OrganizationApi {

    private Long id;
    private String name;
    private String description;
}
