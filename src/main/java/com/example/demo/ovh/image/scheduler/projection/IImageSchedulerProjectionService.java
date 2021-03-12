package com.example.demo.ovh.image.scheduler.projection;

import com.example.demo.ovh.image.scheduler.projection.model.ExistsImageByNameAndRegionNameQuery;
import com.example.demo.ovh.image.scheduler.projection.model.ExistsImageByNameAndRegionNameResponse;
import com.example.demo.ovh.image.scheduler.projection.model.FetchImageProjectionByNameAndRegionNameQuery;
import com.example.demo.ovh.image.scheduler.projection.model.FetchImageProjectionByNameAndRegionNameResponse;

public interface IImageSchedulerProjectionService {

    ExistsImageByNameAndRegionNameResponse existsByNameAndRegionName(ExistsImageByNameAndRegionNameQuery query);

    FetchImageProjectionByNameAndRegionNameResponse fetchImageByNameAndRegionName(FetchImageProjectionByNameAndRegionNameQuery query);
}
