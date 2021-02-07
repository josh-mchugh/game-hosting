package com.example.demo.ovh.flavor.projection.model;

import lombok.AllArgsConstructor;
import lombok.Value;

import java.util.Collection;

@Value
@AllArgsConstructor
public class FetchAdminGameServerFlavorsResponse {

    Collection<AdminGameServerFlavorProjection> flavors;

}
