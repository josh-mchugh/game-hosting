package com.example.demo.ovh.region.projection.model;

import lombok.Builder;
import lombok.Value;

@Value
@Builder(builderClassName = "Builder")
public class FetchIdByNameQuery {

    private final String name;
}