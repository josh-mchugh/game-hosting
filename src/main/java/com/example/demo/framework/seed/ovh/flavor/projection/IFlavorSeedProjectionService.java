package com.example.demo.framework.seed.ovh.flavor.projection;

import com.example.demo.framework.seed.ovh.flavor.projection.model.ExistsAnyFlavorQuery;
import com.example.demo.framework.seed.ovh.flavor.projection.model.ExistsAnyFlavorResponse;

public interface IFlavorSeedProjectionService {

    ExistsAnyFlavorResponse existsAny(ExistsAnyFlavorQuery query);
}
