package com.example.demo.ovh.flavor.scheduler.projection.model;

import lombok.AllArgsConstructor;
import lombok.Value;

@Value
@AllArgsConstructor
public class FetchFlavorByOvhIdQuery {

    String ovhId;
}
