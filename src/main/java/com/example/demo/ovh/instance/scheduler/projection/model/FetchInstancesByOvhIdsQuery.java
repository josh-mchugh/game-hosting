package com.example.demo.ovh.instance.scheduler.projection.model;

import com.google.common.collect.ImmutableCollection;
import lombok.AllArgsConstructor;
import lombok.Value;

@Value
@AllArgsConstructor
public class FetchInstancesByOvhIdsQuery {

    ImmutableCollection<String> ovhIds;
}
