package com.example.demo.ovh.instance.scheduler.projection.model;

import com.example.demo.ovh.instance.scheduler.projection.projection.InstanceProjection;
import com.google.common.collect.ImmutableList;
import lombok.AllArgsConstructor;
import lombok.Value;

@Value
@AllArgsConstructor
public class FetchInstancesByOvhIdsResponse {

    ImmutableList<InstanceProjection> instances;
}
