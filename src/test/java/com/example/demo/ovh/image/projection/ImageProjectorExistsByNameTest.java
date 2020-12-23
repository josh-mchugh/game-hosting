package com.example.demo.ovh.image.projection;

import com.example.demo.ovh.image.aggregate.event.ImageCreatedEvent;
import com.example.demo.ovh.image.entity.service.IImageService;
import com.example.demo.ovh.image.projection.model.ExistByNameAndRegionNameQuery;
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
public class ImageProjectorExistsByNameTest {

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
    public void whenImageExistsThenReturnTrue() {

        ImageCreatedEvent event = ImageCreatedEvent.builder()
                .id(UUID.randomUUID())
                .regionId(region.getId())
                .ovhId("ovhId")
                .name("Test Name")
                .build();

        imageService.handleCreated(event);

        ExistByNameAndRegionNameQuery query = ExistByNameAndRegionNameQuery.builder()
                .name("Test Name")
                .regionName(region.getName())
                .build();

        boolean exists = imageProjector.existsByNameAndRegionName(query);

        Assertions.assertTrue(exists);
    }

    @Test
    public void whenImageDoesNotExistsThenReturnFalse() {

        ExistByNameAndRegionNameQuery query = ExistByNameAndRegionNameQuery.builder()
                .name("Test Name")
                .regionName(region.getName())
                .build();

        boolean exists = imageProjector.existsByNameAndRegionName(query);

        Assertions.assertFalse(exists);
    }
}
