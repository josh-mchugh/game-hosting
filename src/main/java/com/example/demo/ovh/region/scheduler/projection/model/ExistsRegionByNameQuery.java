package com.example.demo.ovh.region.scheduler.projection.model;

import lombok.AllArgsConstructor;
import lombok.Value;

@Value
@AllArgsConstructor
public class ExistsRegionByNameQuery {

    String name;
}
