package com.example.demo.framework.seed.awx.inventory.projection.model;

import lombok.AllArgsConstructor;
import lombok.Value;

import java.util.UUID;

@Value
@AllArgsConstructor
public class FetchAwxOrganizationIdByAwxIdResponse {

    UUID id;
}
