package com.example.demo.ovh.flavor.scheduler.projection;

import com.example.demo.ovh.flavor.scheduler.projection.model.ExistsFlavorByOvhIdQuery;
import com.example.demo.ovh.flavor.scheduler.projection.model.ExistsFlavorByOvhIdResponse;
import com.example.demo.ovh.flavor.scheduler.projection.model.FetchFlavorByOvhIdQuery;
import com.example.demo.ovh.flavor.scheduler.projection.model.FetchFlavorByOvhIdResponse;
import com.example.demo.ovh.flavor.scheduler.projection.model.FetchRegionIdsGroupedByNameQuery;
import com.example.demo.ovh.flavor.scheduler.projection.model.FetchRegionIdsGroupedByNameResponse;

public interface FlavorSchedulerProjectionService {

    ExistsFlavorByOvhIdResponse existsByOvhId(ExistsFlavorByOvhIdQuery query);

    FetchFlavorByOvhIdResponse fetchFlavorByOvhId(FetchFlavorByOvhIdQuery query);

    FetchRegionIdsGroupedByNameResponse fetchRegionIdsGroupedByName(FetchRegionIdsGroupedByNameQuery query);
}
