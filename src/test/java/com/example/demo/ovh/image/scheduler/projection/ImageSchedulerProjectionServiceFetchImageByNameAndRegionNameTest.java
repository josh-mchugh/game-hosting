package com.example.demo.ovh.image.scheduler.projection;

import com.example.demo.ovh.image.entity.model.Image;
import com.example.demo.ovh.image.scheduler.projection.model.FetchImageProjectionByNameAndRegionNameQuery;
import com.example.demo.ovh.image.scheduler.projection.model.FetchImageProjectionByNameAndRegionNameResponse;
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
public class ImageSchedulerProjectionServiceFetchImageByNameAndRegionNameTest {

    @Autowired
    private ImageSchedulerProjectionService service;

    @Autowired
    private SampleBuilder sampleBuilder;

    @Test
    public void whenParamsIsNullThenExpectException() {

        Assertions.assertThrows(UndeclaredThrowableException.class, () -> service.fetchImageByNameAndRegionName(null));
    }

    @Test
    public void whenParamHasNullNameThenExpectException() {

        FetchImageProjectionByNameAndRegionNameQuery query = new FetchImageProjectionByNameAndRegionNameQuery(null, "regionName");

        Assertions.assertThrows(UndeclaredThrowableException.class, () -> service.fetchImageByNameAndRegionName(query));
    }

    @Test
    public void whenParamHasNullRegionNameThenExpectException() {

        FetchImageProjectionByNameAndRegionNameQuery query = new FetchImageProjectionByNameAndRegionNameQuery("name", null);

        Assertions.assertThrows(UndeclaredThrowableException.class, () -> service.fetchImageByNameAndRegionName(query));
    }

    @Test
    public void whenImageExistsByNameAndRegionNameThenExpectImageNotNull() {

        SampleData sampleData = sampleBuilder.builder()
                .region()
                .image()
                .build();

        FetchImageProjectionByNameAndRegionNameQuery query = new FetchImageProjectionByNameAndRegionNameQuery(sampleData.getImage().getName(), sampleData.getRegion().getName());
        FetchImageProjectionByNameAndRegionNameResponse response = service.fetchImageByNameAndRegionName(query);

        Assertions.assertNotNull(response.getImage());
    }

    @Test
    public void whenImageDoesNotExistsByNameAndRegionNameThenExpectImageNull() {

        FetchImageProjectionByNameAndRegionNameQuery query = new FetchImageProjectionByNameAndRegionNameQuery("name", "regionName");
        FetchImageProjectionByNameAndRegionNameResponse response = service.fetchImageByNameAndRegionName(query);

        Assertions.assertNull(response.getImage());
    }

    @Test
    public void whenImageDoesNotExistsButRegionNameDoesThenExpectImageNull() {

        Region region = sampleBuilder.builder()
                .region()
                .build()
                .getRegion();

        FetchImageProjectionByNameAndRegionNameQuery query = new FetchImageProjectionByNameAndRegionNameQuery("name", region.getName());
        FetchImageProjectionByNameAndRegionNameResponse response = service.fetchImageByNameAndRegionName(query);

        Assertions.assertNull(response.getImage());
    }

    @Test
    public void whenImageNameExistsByRegionNameDoesNotThenExpectImageNull() {

        Image image = sampleBuilder.builder()
                .image()
                .build()
                .getImage();

        FetchImageProjectionByNameAndRegionNameQuery query = new FetchImageProjectionByNameAndRegionNameQuery(image.getName(), "regionName");
        FetchImageProjectionByNameAndRegionNameResponse response = service.fetchImageByNameAndRegionName(query);

        Assertions.assertNull(response.getImage());
    }
}
