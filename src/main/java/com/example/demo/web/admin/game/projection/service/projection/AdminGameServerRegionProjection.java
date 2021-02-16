package com.example.demo.web.admin.game.projection.service.projection;

import com.example.demo.ovh.region.entity.RegionStatus;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Value;

@Value
public class AdminGameServerRegionProjection {

    String id;
    String name;
    String dataCenter;
    RegionStatus status;

    @QueryProjection
    public AdminGameServerRegionProjection(String id, String name, String dataCenter, RegionStatus status) {

        this.id = id;
        this.name = name;
        this.dataCenter = dataCenter;
        this.status = status;
    }
}
