package com.example.demo.ovh.image.projection;

import com.example.demo.ovh.image.projection.model.ExistImageNameAndRegionNameQuery;
import com.example.demo.ovh.image.projection.model.FetchImageAndRegionIdProjection;
import com.example.demo.ovh.image.projection.model.FetchImageIdAndRegionIdQuery;

public interface IImageProjector {

    boolean existsAny();

    boolean existsByNameAndRegionName(ExistImageNameAndRegionNameQuery query);

    FetchImageAndRegionIdProjection fetchImageIdAndRegionIdQuery(FetchImageIdAndRegionIdQuery query);
}
