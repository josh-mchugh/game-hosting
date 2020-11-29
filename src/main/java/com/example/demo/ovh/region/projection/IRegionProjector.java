package com.example.demo.ovh.region.projection;

import com.example.demo.ovh.region.projection.model.FetchRegionIdByNameQuery;
import com.example.demo.ovh.region.projection.model.FetchRegionIdByNameResponse;

public interface IRegionProjector {

    boolean existsByName(String name);

    boolean existsAny();

    FetchRegionIdByNameResponse fetchIdByName(FetchRegionIdByNameQuery query);
}