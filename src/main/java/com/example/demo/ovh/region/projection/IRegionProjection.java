package com.example.demo.ovh.region.projection;

import com.example.demo.ovh.region.projection.model.FetchIdByNameQuery;
import com.example.demo.ovh.region.projection.model.FetchIdByNameResponse;

public interface IRegionProjection {

    boolean existsByName(String name);

    boolean existsAny();

    FetchIdByNameResponse fetchIdByName(FetchIdByNameQuery query);
}
