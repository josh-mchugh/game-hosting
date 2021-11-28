package com.example.demo.ovh.image.scheduler.projection;

import com.example.demo.ovh.image.entity.model.Image;
import com.example.demo.ovh.image.scheduler.projection.model.ExistsImageByNameAndRegionNameQuery;
import com.example.demo.ovh.image.scheduler.projection.model.ExistsImageByNameAndRegionNameResponse;
import com.example.demo.ovh.region.entity.model.Region;
import com.example.demo.sample.SampleBuilder;
import com.example.demo.sample.SampleData;
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
public class ImageSchedulerProjectionServiceExistsByImageNameAndRegionNameTest {

    @Autowired
    private ImageSchedulerProjectionService service;

    @Autowired
    private SampleBuilder sampleBuilder;

    @Test
    public void whenParamsIsNullThenExpectException() {

        Assertions.assertThrows(UndeclaredThrowableException.class, () -> service.existsByNameAndRegionName(null));
    }

    @Test
    public void whenParamHasNullNameThenExpectException() {

        ExistsImageByNameAndRegionNameQuery query = new ExistsImageByNameAndRegionNameQuery(null, "regionName");

        Assertions.assertThrows(UndeclaredThrowableException.class, () -> service.existsByNameAndRegionName(query));
    }

    @Test
    public void whenParamHasNullRegionNameThenExpectException() {

        ExistsImageByNameAndRegionNameQuery query = new ExistsImageByNameAndRegionNameQuery("name", null);

        Assertions.assertThrows(UndeclaredThrowableException.class, () -> service.existsByNameAndRegionName(query));
    }

    @Test
    public void whenImageExistsByNameAndRegionNameThenExpectTrue() {

        SampleData sampleData = sampleBuilder.builder()
                .region()
                .image()
                .build();

        ExistsImageByNameAndRegionNameQuery query = new ExistsImageByNameAndRegionNameQuery(sampleData.getImage().getName(), sampleData.getRegion().getName());
        ExistsImageByNameAndRegionNameResponse response = service.existsByNameAndRegionName(query);

        Assertions.assertTrue(response.exists());
    }

    @Test
    public void whenImageDoesNotExistsByNameAndRegionNameThenExpectFalse() {

        ExistsImageByNameAndRegionNameQuery query = new ExistsImageByNameAndRegionNameQuery("name", "regionName");
        ExistsImageByNameAndRegionNameResponse response = service.existsByNameAndRegionName(query);

        Assertions.assertFalse(response.exists());
    }

    @Test
    public void whenImageDoesNotExistsButRegionNameDoesThenExpectFalse() {

        Region region = sampleBuilder.builder()
                .region()
                .build()
                .getRegion();

        ExistsImageByNameAndRegionNameQuery query = new ExistsImageByNameAndRegionNameQuery("name", region.getName());
        ExistsImageByNameAndRegionNameResponse response = service.existsByNameAndRegionName(query);

        Assertions.assertFalse(response.exists());
    }

    @Test
    public void whenImageNameExistsByRegionNameDoesNotThenExpectFalse() {

        Image image = sampleBuilder.builder()
                .image()
                .build()
                .getImage();

        ExistsImageByNameAndRegionNameQuery query = new ExistsImageByNameAndRegionNameQuery(image.getName(), "regionName");
        ExistsImageByNameAndRegionNameResponse response = service.existsByNameAndRegionName(query);

        Assertions.assertFalse(response.exists());
    }
}
