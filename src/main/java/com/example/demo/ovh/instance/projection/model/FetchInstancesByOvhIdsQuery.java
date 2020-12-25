package com.example.demo.ovh.instance.projection.model;

import com.google.common.collect.ImmutableList;
import lombok.AllArgsConstructor;
import lombok.Value;

@Value
@AllArgsConstructor
public class FetchInstancesByOvhIdsQuery {

    ImmutableList<String> ovhIds;
}
