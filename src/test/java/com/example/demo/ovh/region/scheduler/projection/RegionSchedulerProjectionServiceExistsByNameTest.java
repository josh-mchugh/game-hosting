package com.example.demo.ovh.region.scheduler.projection;

import com.example.demo.ovh.region.entity.model.Region;
import com.example.demo.ovh.region.scheduler.projection.model.ExistsRegionByNameQuery;
import com.example.demo.ovh.region.scheduler.projection.model.ExistsRegionByNameResponse;
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
public class RegionSchedulerProjectionServiceExistsByNameTest {

    @Autowired
    private RegionSchedulerProjectionService service;

    @Autowired
    private SampleBuilder sampleBuilder;

    @Test
    public void whenParamIsNullThenExpectException() {

        Assertions.assertThrows(UndeclaredThrowableException.class, () -> service.existsByName(null));
    }

    @Test
    public void whenParamHasNullNameThenExpectException() {

        ExistsRegionByNameQuery query = new ExistsRegionByNameQuery(null);

        Assertions.assertThrows(UndeclaredThrowableException.class, () -> service.existsByName(query));
    }

    @Test
    public void whenRegionExistsByNameThenExpectTrue() {

        Region region = sampleBuilder.builder()
                .region()
                .build()
                .getRegion();

        ExistsRegionByNameQuery query = new ExistsRegionByNameQuery(region.getName());
        ExistsRegionByNameResponse response = service.existsByName(query);

        Assertions.assertTrue(response.exists());
    }

    @Test
    public void whenRegionDoesNotExistsByNameThenReturnFalse() {

        ExistsRegionByNameQuery query = new ExistsRegionByNameQuery("regionName");
        ExistsRegionByNameResponse response = service.existsByName(query);

        Assertions.assertFalse(response.exists());
    }
}
