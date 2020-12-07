package com.example.demo.awx.organization.projection.model;

import lombok.AllArgsConstructor;
import lombok.Value;

@Value
@AllArgsConstructor
public class FetchAwxOrganizationIdByAwxIdQuery {

    Long awxId;
}
