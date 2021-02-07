package com.example.demo.ovh.flavor.projection.model;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Value;

@Value
public class AdminGameServerFlavorProjection {

    String id;
    String name;
    Integer vcpus;
    Integer ram;

    @QueryProjection
    public AdminGameServerFlavorProjection(String id, String name, Integer vcpus, Integer ram) {

        this.id = id;
        this.name = name;
        this.vcpus = vcpus;
        this.ram = ram;
    }
}
