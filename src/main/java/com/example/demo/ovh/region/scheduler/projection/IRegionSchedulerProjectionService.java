package com.example.demo.ovh.region.scheduler.projection;

import com.example.demo.ovh.region.scheduler.projection.model.ExistsRegionByNameQuery;
import com.example.demo.ovh.region.scheduler.projection.model.ExistsRegionByNameResponse;

public interface IRegionSchedulerProjectionService {

    ExistsRegionByNameResponse existsByName(ExistsRegionByNameQuery query);
}
