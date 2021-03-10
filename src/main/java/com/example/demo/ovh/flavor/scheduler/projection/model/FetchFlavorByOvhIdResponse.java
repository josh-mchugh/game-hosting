package com.example.demo.ovh.flavor.scheduler.projection.model;

import com.example.demo.ovh.flavor.scheduler.projection.projection.FlavorProjection;
import lombok.AllArgsConstructor;
import lombok.Value;

@Value
@AllArgsConstructor
public class FetchFlavorByOvhIdResponse {

    FlavorProjection flavor;
}
