package com.example.demo.ovh.region.scheduler.projection;

import com.example.demo.ovh.region.entity.model.Region;
import com.example.demo.ovh.region.scheduler.projection.model.FetchRegionByNameQuery;
import com.example.demo.ovh.region.scheduler.projection.model.FetchRegionByNameResponse;
import com.example.demo.sample.SampleBuilder;
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
public class RegionSchedulerProjectionServiceFetchRegionByNameTest {

    @Autowired
    private IRegionSchedulerProjectionService service;

    @Autowired
    private SampleBuilder sampleBuilder;

    @Test
    public void whenParamIsNullThenExpectException() {

        Assertions.assertThrows(UndeclaredThrowableException.class, () -> service.fetchRegionByName(null));
    }

    @Test
    public void whenParamHasNullNameThenExpectException() {

        FetchRegionByNameQuery query = new FetchRegionByNameQuery(null);

        Assertions.assertThrows(UndeclaredThrowableException.class, () -> service.fetchRegionByName(query));
    }

    @Test
    public void whenRegionExistsByNameThenReturnProjection() {

        Region region = sampleBuilder.builder().region().build().getRegion();

        FetchRegionByNameQuery query = new FetchRegionByNameQuery(region.getName());
        FetchRegionByNameResponse response = service.fetchRegionByName(query);

        Assertions.assertEquals(region.getId(), region.getId());
    }

    @Test
    public void whenRegionDoesNotExistThenExpectNull() {

        FetchRegionByNameQuery query = new FetchRegionByNameQuery("name");
        FetchRegionByNameResponse response = service.fetchRegionByName(query);

        Assertions.assertNull(response.getRegion());
    }
}
