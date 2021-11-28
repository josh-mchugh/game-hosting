package com.example.demo.ovh.flavor.scheduler.projection;

import com.example.demo.ovh.flavor.entity.model.Flavor;
import com.example.demo.ovh.flavor.scheduler.projection.model.FetchFlavorByOvhIdQuery;
import com.example.demo.ovh.flavor.scheduler.projection.model.FetchFlavorByOvhIdResponse;
import com.example.demo.ovh.flavor.scheduler.projection.projection.FlavorProjection;
import com.example.demo.sample.SampleBuilder;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import javax.transaction.Transactional;
import java.lang.reflect.UndeclaredThrowableException;
import java.util.UUID;

@SpringBootTest
@Transactional
@ActiveProfiles("test")
public class FlavorSchedulerProjectionServiceFetchFlavorByOvhIdTest {

    @Autowired
    private FlavorSchedulerProjectionService service;

    @Autowired
    private SampleBuilder sampleBuilder;

    @Test
    public void whenParamIsNullThenExpectException() {

        Assertions.assertThrows(UndeclaredThrowableException.class, () -> service.fetchFlavorByOvhId(null));
    }

    @Test
    public void whenParamHasNullOvhIdThenExpectException() {

        FetchFlavorByOvhIdQuery query = new FetchFlavorByOvhIdQuery(null);

        Assertions.assertThrows(UndeclaredThrowableException.class, () -> service.fetchFlavorByOvhId(query));
    }

    @Test
    public void whenFlavorDoesNotExistsThenExpectNull() {

        FetchFlavorByOvhIdQuery query = new FetchFlavorByOvhIdQuery(UUID.randomUUID().toString());
        FetchFlavorByOvhIdResponse response = service.fetchFlavorByOvhId(query);

        Assertions.assertNull(response.getFlavor());
    }

    @Test
    public void whenFlavorExistsThenExpectFlavorProjection() {

        Flavor flavor = sampleBuilder.builder()
                .region()
                .flavor()
                .build()
                .getFlavor();

        FetchFlavorByOvhIdQuery query = new FetchFlavorByOvhIdQuery(flavor.getOvhId());
        FetchFlavorByOvhIdResponse response = service.fetchFlavorByOvhId(query);

        FlavorProjection expected = new FlavorProjection(flavor.getId().toString(), flavor.getName(), flavor.getType(), flavor.getAvailable(), flavor.getHourly(), flavor.getMonthly(), flavor.getQuota(), flavor.getOsType(), flavor.getVcpus(), flavor.getRam(), flavor.getDisk(), flavor.getInboundBandwidth(), flavor.getOutboundBandwidth());

        Assertions.assertEquals(expected, response.getFlavor());
    }
}
