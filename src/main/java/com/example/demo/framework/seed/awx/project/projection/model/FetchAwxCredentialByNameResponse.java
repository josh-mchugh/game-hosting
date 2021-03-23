package com.example.demo.framework.seed.awx.project.projection.model;

import com.example.demo.framework.seed.awx.project.projection.projection.AwxCredentialProjection;
import lombok.AllArgsConstructor;
import lombok.Value;

@Value
@AllArgsConstructor
public class FetchAwxCredentialByNameResponse {

    AwxCredentialProjection projection;
}
