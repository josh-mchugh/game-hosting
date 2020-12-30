package com.example.demo.ovh.instance.projection.model;

import lombok.AllArgsConstructor;
import lombok.Value;

@Value
@AllArgsConstructor
public class FetchInstanceDetailsByProjectIdQuery {

    String projectId;
}
