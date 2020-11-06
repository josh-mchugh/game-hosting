package com.example.demo.ovh.image.projection;

import com.example.demo.ovh.image.entity.model.Image;
import com.example.demo.ovh.image.entity.service.IImageService;
import com.example.demo.ovh.image.projection.model.FetchImageAndRegionIdProjection;
import com.example.demo.ovh.image.projection.model.FetchImageIdAndRegionIdQuery;
import com.example.demo.ovh.region.entity.model.Region;
import com.example.demo.sample.SampleBuilder;
import com.example.demo.sample.SampleData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import javax.transaction.Transactional;

@SpringBootTest
@Transactional
@ActiveProfiles("test")
public class ImageProjectorFetchImageIdAndRegionIdTest {

    @Autowired
    private IImageService imageService;

    @Autowired
    private SampleBuilder sampleBuilder;

    @Autowired
    private IImageProjector imageProjector;

    private Region region;
    private Image image;

    @BeforeEach
    public void setup() {

        SampleData data = sampleBuilder.builder()
                .region()
                .image()
                .build();

        region = data.getRegion();
        image = data.getImage();
    }

    @Test
    public void whenImageAndRegionExistThenReturnProjection() {

        FetchImageIdAndRegionIdQuery query = FetchImageIdAndRegionIdQuery.builder()
                .regionName(region.getName())
                .imageName(image.getName())
                .build();

        FetchImageAndRegionIdProjection projection = imageProjector.fetchImageIdAndRegionIdQuery(query);

        FetchImageAndRegionIdProjection expected = new FetchImageAndRegionIdProjection(image.getId(), region.getId());

        Assertions.assertEquals(expected, projection);
    }

    @Test
    public void whenImageDoesThenExistThenReturnNull() {

        FetchImageIdAndRegionIdQuery query = FetchImageIdAndRegionIdQuery.builder()
                .regionName(region.getName())
                .imageName("invalid-name")
                .build();

        FetchImageAndRegionIdProjection projection = imageProjector.fetchImageIdAndRegionIdQuery(query);

        Assertions.assertNull(projection);
    }

    @Test
    public void whenRegionDoesNotExistThenReturnNull() {

        FetchImageIdAndRegionIdQuery query = FetchImageIdAndRegionIdQuery.builder()
                .regionName("invalid-name")
                .imageName(image.getName())
                .build();

        FetchImageAndRegionIdProjection projection = imageProjector.fetchImageIdAndRegionIdQuery(query);

        Assertions.assertNull(projection);
    }
}
