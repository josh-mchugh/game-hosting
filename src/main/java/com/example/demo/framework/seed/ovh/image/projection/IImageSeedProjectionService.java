package com.example.demo.framework.seed.ovh.image.projection;

import com.example.demo.framework.seed.ovh.image.projection.model.ExistsAnyImageQuery;
import com.example.demo.framework.seed.ovh.image.projection.model.ExistsAnyImageResponse;

public interface IImageSeedProjectionService {

    ExistsAnyImageResponse existsAny(ExistsAnyImageQuery query);
}
