package com.example.demo.framework.seed.ovh.region.projection;

import com.example.demo.framework.seed.ovh.region.projection.model.ExistsAnyRegionQuery;
import com.example.demo.framework.seed.ovh.region.projection.model.ExistsAnyRegionResponse;
import com.example.demo.sample.SampleBuilder;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import javax.transaction.Transactional;

@SpringBootTest
@Transactional
@ActiveProfiles("test")
public class RegionProjectionServiceExistsAnyTest {

    @Autowired
    private RegionSeedProjectionService service;

    @Autowired
    private SampleBuilder sampleBuilder;

    @Test
    public void whenParamIsNullThenExpectNoException() {

        Assertions.assertDoesNotThrow(() -> service.existsAny(null));
    }

    @Test
    public void whenRegionExistsThenReturnTrue() {

        sampleBuilder.builder().region().build();

        ExistsAnyRegionQuery query = new ExistsAnyRegionQuery();
        ExistsAnyRegionResponse response = service.existsAny(query);

        Assertions.assertTrue(response.exists());
    }

    @Test
    public void whenRegionDoesNotExistsThenReturnTrue() {

        ExistsAnyRegionQuery query = new ExistsAnyRegionQuery();
        ExistsAnyRegionResponse response = service.existsAny(query);

        Assertions.assertFalse(response.exists());
    }
}
