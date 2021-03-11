package com.example.demo.ovh.image.scheduler.projection.model;

import lombok.AllArgsConstructor;
import lombok.Value;

@Value
@AllArgsConstructor
public class ExistsImageByNameAndRegionNameQuery {

    String name;
    String regionName;
}
