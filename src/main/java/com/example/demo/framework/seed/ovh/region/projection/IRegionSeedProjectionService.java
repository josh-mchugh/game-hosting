package com.example.demo.framework.seed.ovh.region.projection;

import com.example.demo.framework.seed.ovh.region.projection.model.ExistsAnyRegionQuery;
import com.example.demo.framework.seed.ovh.region.projection.model.ExistsAnyRegionResponse;

public interface IRegionSeedProjectionService {

    ExistsAnyRegionResponse existsAny(ExistsAnyRegionQuery query);
}
