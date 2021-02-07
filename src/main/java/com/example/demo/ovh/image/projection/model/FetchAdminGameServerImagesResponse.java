package com.example.demo.ovh.image.projection.model;

import lombok.AllArgsConstructor;
import lombok.Value;

import java.util.Collection;

@Value
@AllArgsConstructor
public class FetchAdminGameServerImagesResponse {

    Collection<AdminGameServerImageProjection> images;
}
