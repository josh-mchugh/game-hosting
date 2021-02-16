package com.example.demo.web.admin.game.projection.service;

import com.example.demo.framework.web.Select2Response;
import com.example.demo.ovh.flavor.projection.model.AdminGameServerFlavorProjection;
import com.example.demo.ovh.image.projection.model.AdminGameServerImageProjection;
import com.example.demo.web.admin.game.projection.model.AdminGameServerPageRequest;
import com.example.demo.web.admin.game.projection.service.model.FetchAdminGameServerGamesQuery;
import com.example.demo.web.admin.game.projection.service.model.FetchAdminGameServerGamesResponse;
import com.example.demo.web.admin.game.projection.service.model.FetchAdminGameServerRegionsQuery;
import com.example.demo.web.admin.game.projection.service.model.FetchAdminGameServerRegionsResponse;
import com.example.demo.web.admin.game.projection.service.projection.AdminGameServerTableProjection;
import org.springframework.data.domain.Page;

public interface IAdminGameServerProjectorService {

    FetchAdminGameServerGamesResponse getGames(FetchAdminGameServerGamesQuery query);
    FetchAdminGameServerRegionsResponse getRegions(FetchAdminGameServerRegionsQuery query);
    Select2Response<AdminGameServerFlavorProjection> getFlavors(String search, String regionId);
    Select2Response<AdminGameServerImageProjection> getImages(String search, String regionId);
    Page<AdminGameServerTableProjection> getPage(AdminGameServerPageRequest request);
}
