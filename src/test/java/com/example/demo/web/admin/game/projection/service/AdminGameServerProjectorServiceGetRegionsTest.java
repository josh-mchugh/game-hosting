package com.example.demo.web.admin.game.projection.service;

import com.example.demo.framework.web.Select2Response;
import com.example.demo.game.projection.IGameProjector;
import com.example.demo.ovh.flavor.projection.IFlavorProjector;
import com.example.demo.ovh.image.projection.IImageProjector;
import com.example.demo.ovh.region.projection.IRegionProjector;
import com.example.demo.ovh.region.projection.model.AdminGameServerRegionProjection;
import com.example.demo.ovh.region.projection.model.FetchAdminGameServerRegionsQuery;
import com.example.demo.ovh.region.projection.model.FetchAdminGameServerRegionsResponse;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;

public class AdminGameServerProjectorServiceGetRegionsTest {

    @Test
    public void whenServicesGetRegionsThenReturnSelect2Regions() {

        IGameProjector gameProjector = Mockito.mock(IGameProjector.class);
        IRegionProjector regionProjector = Mockito.mock(IRegionProjector.class);
        IFlavorProjector flavorProjector = Mockito.mock(IFlavorProjector.class);
        IImageProjector imageProjector = Mockito.mock(IImageProjector.class);
        AdminGameServerProjectorService service = new AdminGameServerProjectorService(gameProjector, regionProjector, flavorProjector, imageProjector);

        FetchAdminGameServerRegionsResponse response = new FetchAdminGameServerRegionsResponse(new ArrayList<>());
        Mockito.when(regionProjector.fetchRegions(Mockito.any(FetchAdminGameServerRegionsQuery.class))).thenReturn(response);

        Select2Response<AdminGameServerRegionProjection> expected = new Select2Response<>(new ArrayList<>());

        Assertions.assertEquals(expected, service.getRegions());
    }
}
