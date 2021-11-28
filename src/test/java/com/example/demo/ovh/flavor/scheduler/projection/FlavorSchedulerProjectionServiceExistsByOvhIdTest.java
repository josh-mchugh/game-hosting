package com.example.demo.ovh.flavor.scheduler.projection;

import com.example.demo.ovh.flavor.entity.model.Flavor;
import com.example.demo.ovh.flavor.scheduler.projection.model.ExistsFlavorByOvhIdQuery;
import com.example.demo.ovh.flavor.scheduler.projection.model.ExistsFlavorByOvhIdResponse;
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
public class FlavorSchedulerProjectionServiceExistsByOvhIdTest {

    @Autowired
    private FlavorSchedulerProjectionService service;

    @Autowired
    private SampleBuilder sampleBuilder;

    @Test
    public void whenParamIsNullThenExpectException() {

        Assertions.assertThrows(UndeclaredThrowableException.class, () -> service.existsByOvhId(null));
    }

    @Test
    public void whenParamHasNullOvhIdThenExpectException() {

        ExistsFlavorByOvhIdQuery query = new ExistsFlavorByOvhIdQuery(null);

        Assertions.assertThrows(UndeclaredThrowableException.class, () -> service.existsByOvhId(query));
    }

    @Test
    public void whenFlavorExistsThenExpectTrue() {

        Flavor flavor = sampleBuilder.builder()
                .region()
                .flavor()
                .build()
                .getFlavor();

        ExistsFlavorByOvhIdQuery query = new ExistsFlavorByOvhIdQuery(flavor.getOvhId());
        ExistsFlavorByOvhIdResponse response = service.existsByOvhId(query);

        Assertions.assertTrue(response.exists());
    }

    @Test
    public void whenFlavorDoesNotExistThenExpectFalse() {

        ExistsFlavorByOvhIdQuery query = new ExistsFlavorByOvhIdQuery("ovhId");
        ExistsFlavorByOvhIdResponse response = service.existsByOvhId(query);

        Assertions.assertFalse(response.exists());
    }
}
