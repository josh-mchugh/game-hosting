package com.example.demo.controller.ansible.model.jobs;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class JobRelaunchRequest {

    @JsonProperty("hosts")
    private String hosts;
}
