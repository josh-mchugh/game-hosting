package com.example.demo.ovh.flavor.projection;

import com.example.demo.ovh.flavor.projection.model.FetchFlavorIdByFlavorIdQuery;
import com.example.demo.ovh.flavor.projection.model.FetchFlavorIdByFlavorIdResponse;

public interface IFlavorProjector {

    boolean existsAny();

    boolean existsByFlavorId(String flavorId);

    FetchFlavorIdByFlavorIdResponse fetchFlavorIdByFlavorId(FetchFlavorIdByFlavorIdQuery query);
}
