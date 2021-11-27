package com.example.demo.web.admin.game.service;

import com.example.demo.web.admin.game.service.model.ExistsGameServerByNameQuery;
import com.example.demo.web.admin.game.service.model.FetchAdminGameServerGamesResponse;
import com.example.demo.web.admin.game.service.model.FetchAdminGameServerImagesResponse;
import com.example.demo.web.admin.game.service.model.FetchAdminGameServerRegionsQuery;
import com.example.demo.web.admin.game.service.model.FetchAdminGameServerRegionsResponse;
import com.example.demo.web.admin.game.service.model.FetchAdminGameServerTableQuery;
import com.example.demo.web.admin.game.service.model.FetchAdminGameServerTableResponse;
import com.example.demo.web.admin.game.service.model.ExistsGameServerByNameResponse;
import com.example.demo.web.admin.game.service.model.FetchAdminGameServerFlavorsQuery;
import com.example.demo.web.admin.game.service.model.FetchAdminGameServerFlavorsResponse;
import com.example.demo.web.admin.game.service.model.FetchAdminGameServerGamesQuery;
import com.example.demo.web.admin.game.service.model.FetchAdminGameServerImagesQuery;

public interface AdminGameServerService {

    ExistsGameServerByNameResponse existsByName(ExistsGameServerByNameQuery query);
    FetchAdminGameServerGamesResponse getGames(FetchAdminGameServerGamesQuery query);
    FetchAdminGameServerRegionsResponse getRegions(FetchAdminGameServerRegionsQuery query);
    FetchAdminGameServerFlavorsResponse getFlavors(FetchAdminGameServerFlavorsQuery query);
    FetchAdminGameServerImagesResponse getImages(FetchAdminGameServerImagesQuery query);
    FetchAdminGameServerTableResponse getTable(FetchAdminGameServerTableQuery request);
}
