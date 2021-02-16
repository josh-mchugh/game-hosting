package com.example.demo.web.admin.game.projection.service;

import com.example.demo.framework.web.Select2Response;
import com.example.demo.ovh.image.projection.IImageProjector;
import com.example.demo.ovh.image.projection.model.AdminGameServerImageProjection;
import com.example.demo.ovh.image.projection.model.FetchAdminGameServerImagesQuery;
import com.example.demo.ovh.image.projection.model.FetchAdminGameServerImagesResponse;
import com.querydsl.jpa.JPQLQueryFactory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;

public class AdminGameServerProjectorServiceGetImagesTest {

    @Test
    public void whenServicesGetImagesThenReturnSelect2Images() {

        IImageProjector imageProjector = Mockito.mock(IImageProjector.class);
        JPQLQueryFactory queryFactory = Mockito.mock(JPQLQueryFactory.class);

        AdminGameServerProjectorService service = new AdminGameServerProjectorService(imageProjector, queryFactory);

        FetchAdminGameServerImagesResponse response = new FetchAdminGameServerImagesResponse(new ArrayList<>());
        Mockito.when(imageProjector.fetchImagesByRegionId(Mockito.any(FetchAdminGameServerImagesQuery.class))).thenReturn(response);

        Select2Response<AdminGameServerImageProjection> expected = new Select2Response<>(new ArrayList<>());

        Assertions.assertEquals(expected, service.getImages("search", "regionId"));
    }
}
