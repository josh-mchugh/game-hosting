package com.example.demo.ovh.image.projection;

import com.example.demo.ovh.image.entity.model.Image;
import com.example.demo.ovh.image.projection.model.FetchImageByNameAndRegionNameQuery;

public interface IImageProjector {

    Image fetchImageByNameAndRegionName(FetchImageByNameAndRegionNameQuery query);
}
