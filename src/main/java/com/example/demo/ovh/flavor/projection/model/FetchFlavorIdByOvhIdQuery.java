package com.example.demo.ovh.flavor.projection.model;

import lombok.AllArgsConstructor;
import lombok.Value;

@Value
@AllArgsConstructor
public class FetchFlavorIdByOvhIdQuery {

    String ovhId;
}
