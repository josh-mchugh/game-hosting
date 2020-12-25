package com.example.demo.ovh.instance.projection;

import com.example.demo.ovh.instance.entity.model.Instance;
import com.example.demo.ovh.instance.projection.model.FetchInstanceByOvhIdsProjection;
import com.example.demo.ovh.instance.projection.model.FetchInstancesByOvhIdsQuery;
import com.example.demo.sample.SampleBuilder;
import com.google.common.collect.ImmutableList;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import javax.transaction.Transactional;

@SpringBootTest
@Transactional
@ActiveProfiles("test")
public class InstanceProjectorFetchInstanceByOvhIdsProjectionTest {

    @Autowired
    private IInstanceProjector instanceProjector;

    @Autowired
    private SampleBuilder sampleBuilder;

    @Test
    public void whenQueryIsNullThenThrowException() {

        Assertions.assertThrows(NullPointerException.class, () -> instanceProjector.fetchInstancesByIds(null));
    }

    @Test
    public void whenQueryHasNullOvhIdsThenThrowException() {

        Assertions.assertThrows(NullPointerException.class, () -> instanceProjector.fetchInstancesByIds(new FetchInstancesByOvhIdsQuery(null)));
    }

    @Test
    public void whenQueryHasInvalidOvhIsThenReturnEmptyList () {

        FetchInstancesByOvhIdsQuery query = new FetchInstancesByOvhIdsQuery(ImmutableList.of("ovhId1", "ovhId2"));
        FetchInstanceByOvhIdsProjection projection = instanceProjector.fetchInstancesByIds(query);

        Assertions.assertEquals(ImmutableList.of(), projection.getInstances());
    }

    @Test
    public void whenQueryHasValidOvhIdsThenExpectResults() {

        Instance instance = sampleBuilder.builder()
                .region()
                .flavor()
                .image()
                .credential()
                .user()
                .project()
                .instanceGroup()
                .instance()
                .build()
                .getInstance();

        FetchInstancesByOvhIdsQuery query = new FetchInstancesByOvhIdsQuery(ImmutableList.of(instance.getOvhId()));
        FetchInstanceByOvhIdsProjection projection = instanceProjector.fetchInstancesByIds(query);

        Assertions.assertEquals(ImmutableList.of(instance), projection.getInstances());
    }
}
