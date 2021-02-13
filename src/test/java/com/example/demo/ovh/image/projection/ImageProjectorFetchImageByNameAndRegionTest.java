package com.example.demo.ovh.image.projection;

import com.example.demo.ovh.image.aggregate.event.ImageCreatedEvent;
import com.example.demo.ovh.image.entity.model.Image;
import com.example.demo.ovh.image.entity.service.IImageService;
import com.example.demo.ovh.image.projection.model.FetchImageByNameAndRegionNameQuery;
import com.example.demo.ovh.region.entity.model.Region;
import com.example.demo.sample.SampleBuilder;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import javax.transaction.Transactional;
import java.util.UUID;

@SpringBootTest
@Transactional
@ActiveProfiles("test")
public class ImageProjectorFetchImageByNameAndRegionTest {

    @Autowired
    private IImageService imageService;

    @Autowired
    private SampleBuilder sampleBuilder;

    @Autowired
    private IImageProjector imageProjector;

    private Region region;

    @BeforeEach
    public void setup() {

        region = sampleBuilder.builder()
                .region()
                .build()
                .getRegion();
    }

    @Test
    public void whenParamIsNullThenExpectException() {

        Assertions.assertThrows(NullPointerException.class, () -> imageProjector.fetchImageByNameAndRegionName(null));
    }

    @Test
    public void whenParamHasNullNameThenExpectException() {

        FetchImageByNameAndRegionNameQuery query = FetchImageByNameAndRegionNameQuery.builder()
                .name(null)
                .regionName("regionName")
                .build();

        Assertions.assertThrows(IllegalArgumentException.class, () -> imageProjector.fetchImageByNameAndRegionName(query));
    }

    @Test
    public void whenParamHasNullRegionNameThenExpectException() {

        FetchImageByNameAndRegionNameQuery query = FetchImageByNameAndRegionNameQuery.builder()
                .name("")
                .regionName(null)
                .build();

        Assertions.assertThrows(IllegalArgumentException.class, () -> imageProjector.fetchImageByNameAndRegionName(query));
    }

    @Test
    public void whenImageDoesNotExistThenReturnNull() {

        FetchImageByNameAndRegionNameQuery query = FetchImageByNameAndRegionNameQuery.builder()
                .name("name")
                .regionName("regionName")
                .build();

        Assertions.assertNull(imageProjector.fetchImageByNameAndRegionName(query));
    }

    @Test
    public void whenImageExistsThenReturnImage() {

        UUID id = UUID.randomUUID();

        ImageCreatedEvent event = ImageCreatedEvent.builder()
                .id(id)
                .ovhId("ovhId")
                .name("name")
                .regionId(region.getId())
                .build();

        imageService.handleCreated(event);

        FetchImageByNameAndRegionNameQuery query = FetchImageByNameAndRegionNameQuery.builder()
                .name("name")
                .regionName(region.getName())
                .build();

        Image image = imageProjector.fetchImageByNameAndRegionName(query);

        Assertions.assertEquals(id, image.getId());
    }
}
