package com.example.demo.ovh.image.scheduler.projection.model;

import lombok.AllArgsConstructor;
import lombok.Value;

@Value
@AllArgsConstructor
public class FetchImageProjectionByNameAndRegionNameQuery {

    String name;
    String regionName;
}
