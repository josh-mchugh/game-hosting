package com.example.demo.framework.seed.ovh.image.projection;

import com.example.demo.framework.seed.ovh.image.projection.model.ExistsAnyImageQuery;
import com.example.demo.framework.seed.ovh.image.projection.model.ExistsAnyImageResponse;
import com.example.demo.framework.seed.ovh.image.projection.model.FetchRegionIdsGroupedByNameQuery;
import com.example.demo.framework.seed.ovh.image.projection.model.FetchRegionIdsGroupedByNameResponse;

public interface ImageSeedProjectionService {

    ExistsAnyImageResponse existsAny(ExistsAnyImageQuery query);

    FetchRegionIdsGroupedByNameResponse fetchRegionIdsGroupedByName(FetchRegionIdsGroupedByNameQuery query);
}
