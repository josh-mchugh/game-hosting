package com.example.demo.ovh.region.projection;

import com.example.demo.ovh.region.entity.model.Region;
import com.example.demo.ovh.region.projection.model.FetchAdminGameServerRegionsQuery;
import com.example.demo.ovh.region.projection.model.FetchAdminGameServerRegionsResponse;
import com.example.demo.ovh.region.projection.model.FetchRegionByNameQuery;
import com.example.demo.ovh.region.projection.model.FetchRegionIdsGroupByNameProjection;

public interface IRegionProjector {

    boolean existsByName(String name);

    boolean existsAny();

    Region fetchRegionByName(FetchRegionByNameQuery query);

    FetchRegionIdsGroupByNameProjection fetchRegionIdsGroupedByName();

    FetchAdminGameServerRegionsResponse fetchRegions(FetchAdminGameServerRegionsQuery query);
}
