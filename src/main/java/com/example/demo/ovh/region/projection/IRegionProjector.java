package com.example.demo.ovh.region.projection;

import com.example.demo.ovh.region.projection.model.FetchRegionIdByNameProjection;
import com.example.demo.ovh.region.projection.model.FetchRegionIdByNameQuery;
import com.example.demo.ovh.region.projection.model.FetchRegionIdsGroupByNameProjection;

public interface IRegionProjector {

    boolean existsByName(String name);

    boolean existsAny();

    FetchRegionIdByNameProjection fetchIdByName(FetchRegionIdByNameQuery query);

    FetchRegionIdsGroupByNameProjection fetchRegionIdsGroupedByName();
}
