package com.example.demo.ovh.region.projection;

import com.example.demo.ovh.region.projection.model.FetchRegionIdByNameProjection;
import com.example.demo.ovh.region.projection.model.FetchRegionIdByNameQuery;

public interface IRegionProjector {

    boolean existsByName(String name);

    boolean existsAny();

    FetchRegionIdByNameProjection fetchIdByName(FetchRegionIdByNameQuery query);
}
