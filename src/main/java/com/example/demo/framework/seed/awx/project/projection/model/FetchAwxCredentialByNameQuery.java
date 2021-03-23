package com.example.demo.framework.seed.awx.project.projection.model;

import lombok.AllArgsConstructor;
import lombok.Value;

@Value
@AllArgsConstructor
public class FetchAwxCredentialByNameQuery {

    String name;
}
