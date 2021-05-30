package com.example.demo.project.aggregate.saga.projection.model;

import lombok.AllArgsConstructor;
import lombok.Value;

import java.util.UUID;

@Value
@AllArgsConstructor
public class FetchProjectRegionNameByIdQuery {

    UUID id;
}
