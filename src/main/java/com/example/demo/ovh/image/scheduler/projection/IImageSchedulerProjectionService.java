package com.example.demo.ovh.image.scheduler.projection;

import com.example.demo.ovh.image.scheduler.projection.model.ExistsImageByNameAndRegionNameQuery;
import com.example.demo.ovh.image.scheduler.projection.model.ExistsImageByNameAndRegionNameResponse;
import com.example.demo.ovh.image.scheduler.projection.model.FetchImageProjectionByNameAndRegionNameQuery;
import com.example.demo.ovh.image.scheduler.projection.model.FetchImageProjectionByNameAndRegionNameResponse;
import com.example.demo.ovh.image.scheduler.projection.model.FetchRegionIdsGroupedByNameQuery;
import com.example.demo.ovh.image.scheduler.projection.model.FetchRegionIdsGroupedByNameResponse;

public interface IImageSchedulerProjectionService {

    ExistsImageByNameAndRegionNameResponse existsByNameAndRegionName(ExistsImageByNameAndRegionNameQuery query);

    FetchImageProjectionByNameAndRegionNameResponse fetchImageByNameAndRegionName(FetchImageProjectionByNameAndRegionNameQuery query);

    FetchRegionIdsGroupedByNameResponse fetchRegionIdsGroupedByName(FetchRegionIdsGroupedByNameQuery query);
}
