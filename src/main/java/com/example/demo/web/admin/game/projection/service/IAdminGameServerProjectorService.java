package com.example.demo.web.admin.game.projection.service;

import com.example.demo.framework.web.Select2Response;
import com.example.demo.ovh.image.projection.model.AdminGameServerImageProjection;
import com.example.demo.web.admin.game.projection.model.AdminGameServerPageRequest;
import com.example.demo.web.admin.game.projection.service.model.FetchAdminGameServerFlavorsQuery;
import com.example.demo.web.admin.game.projection.service.model.FetchAdminGameServerFlavorsResponse;
import com.example.demo.web.admin.game.projection.service.model.FetchAdminGameServerGamesQuery;
import com.example.demo.web.admin.game.projection.service.model.FetchAdminGameServerGamesResponse;
import com.example.demo.web.admin.game.projection.service.model.FetchAdminGameServerRegionsQuery;
import com.example.demo.web.admin.game.projection.service.model.FetchAdminGameServerRegionsResponse;
import com.example.demo.web.admin.game.projection.service.projection.AdminGameServerTableProjection;
import org.springframework.data.domain.Page;

public interface IAdminGameServerProjectorService {

    FetchAdminGameServerGamesResponse getGames(FetchAdminGameServerGamesQuery query);
    FetchAdminGameServerRegionsResponse getRegions(FetchAdminGameServerRegionsQuery query);
    FetchAdminGameServerFlavorsResponse getFlavors(FetchAdminGameServerFlavorsQuery query);
    Select2Response<AdminGameServerImageProjection> getImages(String search, String regionId);
    Page<AdminGameServerTableProjection> getPage(AdminGameServerPageRequest request);
}
