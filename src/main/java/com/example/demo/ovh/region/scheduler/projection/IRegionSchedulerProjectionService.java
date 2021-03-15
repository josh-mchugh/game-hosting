package com.example.demo.ovh.region.scheduler.projection;

import com.example.demo.ovh.region.scheduler.projection.model.ExistsRegionByNameQuery;
import com.example.demo.ovh.region.scheduler.projection.model.ExistsRegionByNameResponse;
import com.example.demo.ovh.region.scheduler.projection.model.FetchRegionByNameQuery;
import com.example.demo.ovh.region.scheduler.projection.model.FetchRegionByNameResponse;

public interface IRegionSchedulerProjectionService {

    ExistsRegionByNameResponse existsByName(ExistsRegionByNameQuery query);

    FetchRegionByNameResponse fetchRegionByName(FetchRegionByNameQuery query);
}
