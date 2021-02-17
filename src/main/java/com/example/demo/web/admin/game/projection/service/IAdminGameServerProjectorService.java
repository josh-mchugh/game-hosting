package com.example.demo.web.admin.game.projection.service;

import com.example.demo.web.admin.game.projection.service.model.FetchAdminGameServerFlavorsQuery;
import com.example.demo.web.admin.game.projection.service.model.FetchAdminGameServerFlavorsResponse;
import com.example.demo.web.admin.game.projection.service.model.FetchAdminGameServerGamesQuery;
import com.example.demo.web.admin.game.projection.service.model.FetchAdminGameServerGamesResponse;
import com.example.demo.web.admin.game.projection.service.model.FetchAdminGameServerImagesQuery;
import com.example.demo.web.admin.game.projection.service.model.FetchAdminGameServerImagesResponse;
import com.example.demo.web.admin.game.projection.service.model.FetchAdminGameServerRegionsQuery;
import com.example.demo.web.admin.game.projection.service.model.FetchAdminGameServerRegionsResponse;
import com.example.demo.web.admin.game.projection.service.model.FetchAdminGameServerTableQuery;
import com.example.demo.web.admin.game.projection.service.model.FetchAdminGameServerTableResponse;

public interface IAdminGameServerProjectorService {

    FetchAdminGameServerGamesResponse getGames(FetchAdminGameServerGamesQuery query);
    FetchAdminGameServerRegionsResponse getRegions(FetchAdminGameServerRegionsQuery query);
    FetchAdminGameServerFlavorsResponse getFlavors(FetchAdminGameServerFlavorsQuery query);
    FetchAdminGameServerImagesResponse getImages(FetchAdminGameServerImagesQuery query);
    FetchAdminGameServerTableResponse getTable(FetchAdminGameServerTableQuery request);
}
