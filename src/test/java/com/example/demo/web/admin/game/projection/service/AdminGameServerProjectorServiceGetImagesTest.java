package com.example.demo.web.admin.game.projection.service;

import com.example.demo.web.admin.game.projection.service.model.FetchAdminGameServerImagesQuery;
import com.example.demo.web.admin.game.projection.service.model.FetchAdminGameServerImagesResponse;
import com.example.demo.sample.SampleBuilder;
import com.example.demo.sample.SampleData;
import org.apache.commons.collections4.CollectionUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import javax.transaction.Transactional;
import java.lang.reflect.UndeclaredThrowableException;

@SpringBootTest
@Transactional
@ActiveProfiles("test")
public class AdminGameServerProjectorServiceGetImagesTest {

    @Autowired
    private IAdminGameServerProjectorService service;

    @Autowired
    private SampleBuilder sampleBuilder;

    @Test
    public void whenQueryIsNullThenThrowException() {

        Assertions.assertThrows(UndeclaredThrowableException.class, () -> service.getImages(null));
    }

    @Test
    public void whenQueryHasNullRegionIdThenThrowException() {

        FetchAdminGameServerImagesQuery query = new FetchAdminGameServerImagesQuery(null, null);

        Assertions.assertThrows(UndeclaredThrowableException.class, () -> service.getImages(query));
    }

    @Test
    public void whenQueryIsValidAndHasNoEntitiesThenExpectEmptyList() {

        SampleData sampleData = sampleBuilder.builder()
                .region()
                .build();

        FetchAdminGameServerImagesQuery query = new FetchAdminGameServerImagesQuery(null, sampleData.getRegion().getId().toString());

        FetchAdminGameServerImagesResponse response = service.getImages(query);

        Assertions.assertTrue(CollectionUtils.isEmpty(response.getImages()));
    }

    @Test
    public void whenQueryIsValidAndHasEntitiesThenExpectList() {

        SampleData sampleData = sampleBuilder.builder()
                .region()
                .image()
                .build();

        FetchAdminGameServerImagesQuery query = new FetchAdminGameServerImagesQuery(null, sampleData.getRegion().getId().toString());

        FetchAdminGameServerImagesResponse response = service.getImages(query);

        Assertions.assertTrue(CollectionUtils.isNotEmpty(response.getImages()));
    }

    @Test
    public void whenQueryHasSearchAndMatchesThenExpectList() {

        SampleData sampleData = sampleBuilder.builder()
                .region()
                .image()
                .build();

        FetchAdminGameServerImagesQuery query = new FetchAdminGameServerImagesQuery("20", sampleData.getRegion().getId().toString());

        FetchAdminGameServerImagesResponse response = service.getImages(query);

        Assertions.assertTrue(CollectionUtils.isNotEmpty(response.getImages()));
    }

    @Test
    public void whenQueryHasSearchAndNoMatchesThenExpectEmptyList() {

        SampleData sampleData = sampleBuilder.builder()
                .region()
                .image()
                .build();

        FetchAdminGameServerImagesQuery query = new FetchAdminGameServerImagesQuery("10", sampleData.getRegion().getId().toString());

        FetchAdminGameServerImagesResponse response = service.getImages(query);

        Assertions.assertTrue(CollectionUtils.isEmpty(response.getImages()));
    }
}
