package com.example.demo.ovh.image.projection.model;

import lombok.AllArgsConstructor;
import lombok.Value;

@Value
@AllArgsConstructor
public class FetchAdminGameServerImagesQuery {

    String search;
    String regionId;
}
