package com.example.demo.ovh.instance.scheduler.projection;

import com.example.demo.ovh.instance.scheduler.projection.model.FetchInstancesByOvhIdsQuery;
import com.example.demo.ovh.instance.scheduler.projection.model.FetchInstancesByOvhIdsResponse;

public interface InstanceSchedulerProjectionService {

    FetchInstancesByOvhIdsResponse fetchInstanceByOvhId(FetchInstancesByOvhIdsQuery query);
}
