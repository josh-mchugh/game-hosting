package com.example.demo.ovh.instance.scheduler.projection;

import com.example.demo.ovh.instance.entity.model.Instance;
import com.example.demo.ovh.instance.scheduler.projection.model.FetchInstancesByOvhIdsQuery;
import com.example.demo.ovh.instance.scheduler.projection.model.FetchInstancesByOvhIdsResponse;
import com.example.demo.sample.SampleBuilder;
import com.google.common.collect.ImmutableList;
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
public class InstanceSchedulerProjectionServiceFetchInstanceByOvhIdTest {

    @Autowired
    private InstanceSchedulerProjectionService service;

    @Autowired
    private SampleBuilder sampleBuilder;

    @Test
    public void whenParamIsNullThenThrowException() {

        Assertions.assertThrows(UndeclaredThrowableException.class, () -> service.fetchInstanceByOvhId(null));
    }

    @Test
    public void whenParamHasNullOvhIdsThenExpectException() {

        FetchInstancesByOvhIdsQuery query = new FetchInstancesByOvhIdsQuery(null);

        Assertions.assertThrows(UndeclaredThrowableException.class, () -> service.fetchInstanceByOvhId(query));
    }

    @Test
    public void whenParamHasEmptyListOfOvhIdsThenReturnEmptyList() {

        FetchInstancesByOvhIdsQuery query = new FetchInstancesByOvhIdsQuery(ImmutableList.of());
        FetchInstancesByOvhIdsResponse response = service.fetchInstanceByOvhId(query);

        Assertions.assertEquals(0, response.getInstances().size());
    }

    @Test
    public void whenInstanceExistsThenReturnListOfInstances() {

        Instance instance = sampleBuilder.builder()
                .region()
                .flavor()
                .image()
                .instance()
                .build()
                .getInstance();

        FetchInstancesByOvhIdsQuery query = new FetchInstancesByOvhIdsQuery(ImmutableList.of(instance.getOvhId()));
        FetchInstancesByOvhIdsResponse response = service.fetchInstanceByOvhId(query);

        Assertions.assertEquals(1, response.getInstances().size());
    }

    @Test
    public void whenInstanceDoesNotExistsThenReturnEmptyList() {

        FetchInstancesByOvhIdsQuery query = new FetchInstancesByOvhIdsQuery(ImmutableList.of("ovhId"));
        FetchInstancesByOvhIdsResponse response = service.fetchInstanceByOvhId(query);

        Assertions.assertEquals(0, response.getInstances().size());
    }
}
