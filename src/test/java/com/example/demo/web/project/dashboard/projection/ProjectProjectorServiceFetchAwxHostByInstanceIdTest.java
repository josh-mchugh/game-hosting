package com.example.demo.web.project.dashboard.projection;

import com.example.demo.sample.SampleBuilder;
import com.example.demo.sample.SampleData;
import com.example.demo.web.project.dashboard.projection.model.FetchAwxHostByInstanceOvhIdQuery;
import com.example.demo.web.project.dashboard.projection.model.FetchAwxHostByInstanceOvhIdResponse;
import com.example.demo.web.project.dashboard.projection.projection.AwxHostProjection;
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
public class ProjectProjectorServiceFetchAwxHostByInstanceIdTest {

    @Autowired
    private ProjectProjectorService service;

    @Autowired
    private SampleBuilder sampleBuilder;

    @Test
    public void whenParamIsNullThenExpectException() {

        Assertions.assertThrows(UndeclaredThrowableException.class, () -> service.fetchAwxHostByInstanceId(null));
    }

    @Test
    public void whenParamHasNullInstanceOvhIdThenExpectException() {

        FetchAwxHostByInstanceOvhIdQuery query = new FetchAwxHostByInstanceOvhIdQuery(null);

        Assertions.assertThrows(UndeclaredThrowableException.class, () -> service.fetchAwxHostByInstanceId(query));
    }

    @Test
    public void whenAwxHostExistsByInstanceOvhIdThenReturnProjection() {

        SampleData data = sampleBuilder.builder()
                .awxHost()
                .build();

        FetchAwxHostByInstanceOvhIdQuery query = new FetchAwxHostByInstanceOvhIdQuery(data.getInstance().getOvhId());
        FetchAwxHostByInstanceOvhIdResponse response = service.fetchAwxHostByInstanceId(query);

        AwxHostProjection expected = new AwxHostProjection(data.getAwxHost().getId().toString(), data.getAwxHost().getAwxId());

        Assertions.assertEquals(expected, response.getAwxHost());
    }

    @Test
    public void whenAwxHostDoesNotExistsThenExpectNull() {

        FetchAwxHostByInstanceOvhIdQuery query = new FetchAwxHostByInstanceOvhIdQuery("instanceOvhId");
        FetchAwxHostByInstanceOvhIdResponse response = service.fetchAwxHostByInstanceId(query);

        Assertions.assertNull(response.getAwxHost());
    }
}
