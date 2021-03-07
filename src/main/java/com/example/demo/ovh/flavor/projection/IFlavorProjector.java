package com.example.demo.ovh.flavor.projection;

import com.example.demo.ovh.flavor.entity.model.Flavor;
import com.example.demo.ovh.flavor.projection.model.FetchFlavorIdByOvhIdProjection;
import com.example.demo.ovh.flavor.projection.model.FetchFlavorIdByOvhIdQuery;

public interface IFlavorProjector {

    FetchFlavorIdByOvhIdProjection fetchFlavorIdByOvhId(FetchFlavorIdByOvhIdQuery query);

    Flavor fetchFlavorByOvhId(String ovhId);
}
