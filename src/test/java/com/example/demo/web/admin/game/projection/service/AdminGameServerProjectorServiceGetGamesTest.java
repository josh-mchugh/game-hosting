package com.example.demo.web.admin.game.projection.service;

import com.example.demo.framework.web.Select2Response;
import com.example.demo.game.projection.IGameProjector;
import com.example.demo.game.projection.model.AdminGameServerGameProjection;
import com.example.demo.game.projection.model.FetchAdminGameServerGamesQuery;
import com.example.demo.game.projection.model.FetchAdminGameServerGamesResponse;
import com.example.demo.ovh.flavor.projection.IFlavorProjector;
import com.example.demo.ovh.image.projection.IImageProjector;
import com.example.demo.ovh.region.projection.IRegionProjector;
import com.querydsl.jpa.JPQLQueryFactory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;

public class AdminGameServerProjectorServiceGetGamesTest {

    @Test
    public void whenServicesGetsGamesThenReturnSelect2Games() {

        IGameProjector gameProjector = Mockito.mock(IGameProjector.class);
        IRegionProjector regionProjector = Mockito.mock(IRegionProjector.class);
        IFlavorProjector flavorProjector = Mockito.mock(IFlavorProjector.class);
        IImageProjector imageProjector = Mockito.mock(IImageProjector.class);
        JPQLQueryFactory queryFactory = Mockito.mock(JPQLQueryFactory.class);

        AdminGameServerProjectorService service = new AdminGameServerProjectorService(gameProjector, regionProjector, flavorProjector, imageProjector, queryFactory);

        FetchAdminGameServerGamesResponse response = new FetchAdminGameServerGamesResponse(new ArrayList<>());
        Mockito.when(gameProjector.fetchGames(Mockito.any(FetchAdminGameServerGamesQuery.class))).thenReturn(response);

        Select2Response<AdminGameServerGameProjection> expected = new Select2Response<>(new ArrayList<>());

        Assertions.assertEquals(expected, service.getGames());
    }
}
