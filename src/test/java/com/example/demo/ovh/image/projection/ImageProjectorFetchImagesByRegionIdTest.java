package com.example.demo.ovh.image.projection;

import com.example.demo.ovh.image.projection.model.FetchAdminGameServerImagesQuery;
import com.example.demo.ovh.image.projection.model.FetchAdminGameServerImagesResponse;
import com.example.demo.sample.SampleBuilder;
import com.example.demo.sample.SampleData;
import org.apache.commons.collections4.CollectionUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import javax.transaction.Transactional;

@SpringBootTest
@Transactional
@ActiveProfiles("test")
public class ImageProjectorFetchImagesByRegionIdTest {

    @Autowired
    private IImageProjector imageProjector;

    @Autowired
    private SampleBuilder sampleBuilder;

    @Test
    public void whenQueryIsNullThenThrowException() {

        Assertions.assertThrows(NullPointerException.class, () -> imageProjector.fetchImagesByRegionId(null));
    }

    @Test
    public void whenQueryHasNullRegionIdThenThrowException() {

        FetchAdminGameServerImagesQuery query = new FetchAdminGameServerImagesQuery(null, null);

        Assertions.assertThrows(NullPointerException.class, () -> imageProjector.fetchImagesByRegionId(query));
    }

    @Test
    public void whenQueryIsValidAndHasNoEntitiesThenExpectEmptyList() {

        SampleData sampleData = sampleBuilder.builder()
                .region()
                .build();

        FetchAdminGameServerImagesQuery query = new FetchAdminGameServerImagesQuery(null, sampleData.getRegion().getId().toString());

        FetchAdminGameServerImagesResponse response = imageProjector.fetchImagesByRegionId(query);

        Assertions.assertTrue(CollectionUtils.isEmpty(response.getImages()));
    }

    @Test
    public void whenQueryIsValidAndHasEntitiesThenExpectList() {

        SampleData sampleData = sampleBuilder.builder()
                .region()
                .image()
                .build();

        FetchAdminGameServerImagesQuery query = new FetchAdminGameServerImagesQuery(null, sampleData.getRegion().getId().toString());

        FetchAdminGameServerImagesResponse response = imageProjector.fetchImagesByRegionId(query);

        Assertions.assertTrue(CollectionUtils.isNotEmpty(response.getImages()));
    }

    @Test
    public void whenQueryHasSearchAndMatchesThenExpectList() {

        SampleData sampleData = sampleBuilder.builder()
                .region()
                .image()
                .build();

        FetchAdminGameServerImagesQuery query = new FetchAdminGameServerImagesQuery("20", sampleData.getRegion().getId().toString());

        FetchAdminGameServerImagesResponse response = imageProjector.fetchImagesByRegionId(query);

        Assertions.assertTrue(CollectionUtils.isNotEmpty(response.getImages()));
    }

    @Test
    public void whenQueryHasSearchAndNoMatchesThenExpectEmptyList() {

        SampleData sampleData = sampleBuilder.builder()
                .region()
                .image()
                .build();

        FetchAdminGameServerImagesQuery query = new FetchAdminGameServerImagesQuery("10", sampleData.getRegion().getId().toString());

        FetchAdminGameServerImagesResponse response = imageProjector.fetchImagesByRegionId(query);

        Assertions.assertTrue(CollectionUtils.isEmpty(response.getImages()));
    }
}
