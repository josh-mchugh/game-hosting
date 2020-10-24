package com.example.demo.ovh.flavor.projection.model;

import lombok.Builder;
import lombok.Value;

@Value
@Builder(builderClassName = "Builder")
public class FetchFlavorIdByFlavorIdQuery {

    String flavorId;
}
