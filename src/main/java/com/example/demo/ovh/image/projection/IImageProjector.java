package com.example.demo.ovh.image.projection;

import com.example.demo.ovh.image.entity.model.Image;
import com.example.demo.ovh.image.projection.model.ExistByNameAndRegionNameQuery;
import com.example.demo.ovh.image.projection.model.FetchAdminGameServerImagesQuery;
import com.example.demo.ovh.image.projection.model.FetchAdminGameServerImagesResponse;
import com.example.demo.ovh.image.projection.model.FetchImageByNameAndRegionNameQuery;

public interface IImageProjector {

    boolean existsAny();

    boolean existsByNameAndRegionName(ExistByNameAndRegionNameQuery query);

    Image fetchImageByNameAndRegionName(FetchImageByNameAndRegionNameQuery query);

    FetchAdminGameServerImagesResponse fetchImagesByRegionId(FetchAdminGameServerImagesQuery query);
}
