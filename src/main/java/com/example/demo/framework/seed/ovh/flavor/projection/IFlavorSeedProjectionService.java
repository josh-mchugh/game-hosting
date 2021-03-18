package com.example.demo.framework.seed.ovh.flavor.projection;

import com.example.demo.framework.seed.ovh.flavor.projection.model.ExistsAnyFlavorQuery;
import com.example.demo.framework.seed.ovh.flavor.projection.model.ExistsAnyFlavorResponse;
import com.example.demo.framework.seed.ovh.flavor.projection.model.FetchRegionIdsGroupedByNameQuery;
import com.example.demo.framework.seed.ovh.flavor.projection.model.FetchRegionIdsGroupedByNameResponse;

public interface IFlavorSeedProjectionService {

    ExistsAnyFlavorResponse existsAny(ExistsAnyFlavorQuery query);

    FetchRegionIdsGroupedByNameResponse fetchRegionIdsGroupedByName(FetchRegionIdsGroupedByNameQuery query);
}
