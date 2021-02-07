package com.example.demo.web.admin.game.projection.service;

import com.example.demo.framework.web.Select2Response;
import com.example.demo.game.projection.IGameProjector;
import com.example.demo.game.projection.model.AdminGameServerGameProjection;
import com.example.demo.game.projection.model.FetchAdminGameServerGamesQuery;
import com.example.demo.game.projection.model.FetchAdminGameServerGamesResponse;
import com.example.demo.ovh.flavor.projection.IFlavorProjector;
import com.example.demo.ovh.flavor.projection.model.AdminGameServerFlavorProjection;
import com.example.demo.ovh.flavor.projection.model.FetchAdminGameServerFlavorsQuery;
import com.example.demo.ovh.flavor.projection.model.FetchAdminGameServerFlavorsResponse;
import com.example.demo.ovh.image.projection.IImageProjector;
import com.example.demo.ovh.image.projection.model.AdminGameServerImageProjection;
import com.example.demo.ovh.image.projection.model.FetchAdminGameServerImagesQuery;
import com.example.demo.ovh.image.projection.model.FetchAdminGameServerImagesResponse;
import com.example.demo.ovh.region.projection.IRegionProjector;
import com.example.demo.ovh.region.projection.model.AdminGameServerRegionProjection;
import com.example.demo.ovh.region.projection.model.FetchAdminGameServerRegionsQuery;
import com.example.demo.ovh.region.projection.model.FetchAdminGameServerRegionsResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AdminGameServerProjectorService implements IAdminGameServerProjectorService {

    private final IGameProjector gameProjection;
    private final IRegionProjector regionProjector;
    private final IFlavorProjector flavorProjector;
    private final IImageProjector imageProjector;

    @Override
    public Select2Response<AdminGameServerGameProjection> getGames() {

        FetchAdminGameServerGamesResponse response = gameProjection.fetchGames(new FetchAdminGameServerGamesQuery());

        return new Select2Response<>(response.getGames());
    }

    @Override
    public Select2Response<AdminGameServerRegionProjection> getRegions() {

        FetchAdminGameServerRegionsResponse response = regionProjector.fetchRegions(new FetchAdminGameServerRegionsQuery());

        return new Select2Response<>(response.getRegions());
    }

    @Override
    public Select2Response<AdminGameServerFlavorProjection> getFlavors(String search, String regionId) {

        FetchAdminGameServerFlavorsQuery query = new FetchAdminGameServerFlavorsQuery(search, regionId);
        FetchAdminGameServerFlavorsResponse response = flavorProjector.fetchFlavorsByRegionId(query);

        return new Select2Response<>(response.getFlavors());
    }

    @Override
    public Select2Response<AdminGameServerImageProjection> getImages(String search, String regionId) {

        FetchAdminGameServerImagesQuery query = new FetchAdminGameServerImagesQuery(search, regionId);
        FetchAdminGameServerImagesResponse response = imageProjector.fetchImagesByRegionId(query);

        return new Select2Response<>(response.getImages());
    }
}
