package com.example.demo.web.admin.game.projection.service.model;

import com.example.demo.web.admin.game.projection.service.projection.AdminGameServerImageProjection;
import lombok.AllArgsConstructor;
import lombok.Value;

import java.util.Collection;

@Value
@AllArgsConstructor
public class FetchAdminGameServerImagesResponse {

    Collection<AdminGameServerImageProjection> images;
}
