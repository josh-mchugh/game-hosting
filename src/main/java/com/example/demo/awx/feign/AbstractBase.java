package com.example.demo.awx.feign;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public abstract class AbstractBase {

    @JsonProperty("id")
    private Long id;

    @JsonProperty("type")
    private String type;

    @JsonProperty("created")
    private String created;

    @JsonProperty("modified")
    private String modified;
}