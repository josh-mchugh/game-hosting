package com.example.demo.ovh.flavor.projection;

import com.example.demo.ovh.flavor.entity.model.Flavor;
import com.example.demo.ovh.flavor.projection.model.FetchFlavorIdByOvhIdQuery;
import com.example.demo.ovh.flavor.projection.model.FetchFlavorIdByOvhIdProjection;

public interface IFlavorProjector {

    boolean existsAny();

    boolean existsByOvhId(String ovhId);

    FetchFlavorIdByOvhIdProjection fetchFlavorIdByOvhId(FetchFlavorIdByOvhIdQuery query);

    Flavor fetchFlavorByOvhId(String ovhId);
}
