package com.example.demo.web.awx.service.model;

import lombok.AllArgsConstructor;
import lombok.Value;
import lombok.experimental.Accessors;

@Value
@AllArgsConstructor
public class ExistsAnyPlaybooksResponse {

    @Accessors(fluent = true)
    boolean exists;
}
