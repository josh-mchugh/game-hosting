package com.example.demo.ovh.region.projection;

import com.example.demo.ovh.region.projection.model.FetchRegionIdsGroupByNameProjection;

public interface IRegionProjector {

    boolean existsAny();

    FetchRegionIdsGroupByNameProjection fetchRegionIdsGroupedByName();
}
