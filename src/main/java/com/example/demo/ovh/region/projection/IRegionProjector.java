package com.example.demo.ovh.region.projection;

import com.example.demo.ovh.region.entity.model.Region;
import com.example.demo.ovh.region.projection.model.FetchRegionByNameQuery;
import com.example.demo.ovh.region.projection.model.FetchRegionIdsGroupByNameProjection;

public interface IRegionProjector {

    boolean existsAny();

    Region fetchRegionByName(FetchRegionByNameQuery query);

    FetchRegionIdsGroupByNameProjection fetchRegionIdsGroupedByName();
}
