package com.example.demo.ovh.flavor.projection;

import com.example.demo.ovh.flavor.entity.model.Flavor;
import com.example.demo.ovh.flavor.projection.model.FetchAdminGameServerFlavorsQuery;
import com.example.demo.ovh.flavor.projection.model.FetchAdminGameServerFlavorsResponse;
import com.example.demo.ovh.flavor.projection.model.FetchFlavorIdByOvhIdProjection;
import com.example.demo.ovh.flavor.projection.model.FetchFlavorIdByOvhIdQuery;

public interface IFlavorProjector {

    boolean existsAny();

    boolean existsByOvhId(String ovhId);

    FetchFlavorIdByOvhIdProjection fetchFlavorIdByOvhId(FetchFlavorIdByOvhIdQuery query);

    Flavor fetchFlavorByOvhId(String ovhId);

    FetchAdminGameServerFlavorsResponse fetchFlavorsByRegionId(FetchAdminGameServerFlavorsQuery query);
}
