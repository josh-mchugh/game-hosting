package com.example.demo.ovh.instance.projection.model;

import com.example.demo.ovh.instance.entity.model.Instance;
import com.google.common.collect.ImmutableList;
import lombok.AllArgsConstructor;
import lombok.Value;

@Value
@AllArgsConstructor
public class FetchInstanceByOvhIdsProjection {

    ImmutableList<Instance> instances;
}
