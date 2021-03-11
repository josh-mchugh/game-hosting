package com.example.demo.ovh.image.scheduler.projection;

import com.example.demo.ovh.image.scheduler.projection.model.ExistsImageByNameAndRegionNameQuery;
import com.example.demo.ovh.image.scheduler.projection.model.ExistsImageByNameAndRegionNameResponse;

public interface IImageSchedulerProjectionService {

    ExistsImageByNameAndRegionNameResponse existsByNameAndRegionName(ExistsImageByNameAndRegionNameQuery query);
}
