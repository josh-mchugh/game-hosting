package com.example.demo.ovh.flavor.scheduler.projection;

import com.example.demo.ovh.flavor.scheduler.projection.model.ExistsFlavorByOvhIdQuery;
import com.example.demo.ovh.flavor.scheduler.projection.model.ExistsFlavorByOvhIdResponse;

public interface IFlavorSchedulerProjectionService {

    ExistsFlavorByOvhIdResponse existsByOvhId(ExistsFlavorByOvhIdQuery query);
}
