package com.example.demo.ovh.region.projection.model;

import lombok.AllArgsConstructor;
import lombok.Value;

import java.util.Collection;

@Value
@AllArgsConstructor
public class FetchAdminGameServerRegionsResponse {

    Collection<AdminGameServerRegionProjection> regions;
}
