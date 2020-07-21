package com.example.demo.ovh.model;

import lombok.Builder;
import lombok.Value;

@Value
@Builder(builderClassName = "Builder")
public class GroupCreate {

    String name;
    String region;
    String type;
}
