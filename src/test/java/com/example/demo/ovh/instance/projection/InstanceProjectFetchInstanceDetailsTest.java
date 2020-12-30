package com.example.demo.ovh.instance.projection;

import com.example.demo.ovh.instance.projection.model.FetchInstanceDetailsByProjectIdProjection;
import com.example.demo.ovh.instance.projection.model.FetchInstanceDetailsByProjectIdQuery;
import com.example.demo.sample.SampleBuilder;
import com.example.demo.sample.SampleData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import javax.transaction.Transactional;

@SpringBootTest
@Transactional
@ActiveProfiles("test")
public class InstanceProjectFetchInstanceDetailsTest {

    @Autowired
    private IInstanceProjector instanceProjector;

    @Autowired
    private SampleBuilder sampleBuilder;

    @Test
    public void whenQueryIsNullThenThrowException() {

        Assertions.assertThrows(NullPointerException.class, () -> instanceProjector.fetchInstanceDetails(null));
    }

    @Test
    public void whenQueryHasNullProjectIdThenThrowException() {

        FetchInstanceDetailsByProjectIdQuery query = new FetchInstanceDetailsByProjectIdQuery(null);

        Assertions.assertThrows(IllegalArgumentException.class, () -> instanceProjector.fetchInstanceDetails(query));
    }

    @Test
    public void whenQueryHasInvalidProjectIdThenReturnNull() {

        FetchInstanceDetailsByProjectIdQuery query = new FetchInstanceDetailsByProjectIdQuery("invalidId");

        Assertions.assertNull(instanceProjector.fetchInstanceDetails(query));
    }

    @Test
    public void whenQueryIsValidThenReturnInstanceDetails() {

        SampleData data = sampleBuilder.builder()
                .region()
                .image()
                .flavor()
                .credential()
                .user()
                .game()
                .project()
                .instanceGroup()
                .instance()
                .build();

        FetchInstanceDetailsByProjectIdQuery query = new FetchInstanceDetailsByProjectIdQuery(data.getProject().getId());

        FetchInstanceDetailsByProjectIdProjection projection = instanceProjector.fetchInstanceDetails(query);

        Assertions.assertEquals(data.getInstance().getOvhId(), projection.getOvhId());
    }
}
