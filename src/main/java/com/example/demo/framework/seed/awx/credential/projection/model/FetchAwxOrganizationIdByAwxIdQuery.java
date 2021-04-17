package com.example.demo.framework.seed.awx.credential.projection.model;

import lombok.AllArgsConstructor;
import lombok.Value;

@Value
@AllArgsConstructor
public class FetchAwxOrganizationIdByAwxIdQuery {

    Long awxId;
}
