package com.example.demo.web.project.create.query;

import com.example.demo.ovh.flavor.entity.model.Flavor;
import com.example.demo.sample.SampleBuilder;
import com.example.demo.sample.SampleData;
import com.example.demo.web.project.create.query.model.FetchProjectImageIdQuery;
import com.example.demo.web.project.create.query.model.FetchProjectImageIdResponse;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import javax.transaction.Transactional;
import java.util.UUID;

@SpringBootTest
@Transactional
@ActiveProfiles("test")
public class ProjectCreateServiceFetchImageIdTest {

    @Autowired
    private ProjectCreateService service;

    @Autowired
    private SampleBuilder sampleBuilder;

    @Test
    public void whenParamIsNullThenThrowException() {

        Assertions.assertThrows(NullPointerException.class, () -> service.fetchProjectImageId(null));
    }

    @Test
    public void whenParamHasNullIdThenThrowException() {

        FetchProjectImageIdQuery query = new FetchProjectImageIdQuery(null, UUID.randomUUID().toString());

        Assertions.assertThrows(NullPointerException.class, () -> service.fetchProjectImageId(query));
    }

    @Test
    public void whenParamHasFlavorIdThenThrowException() {

        FetchProjectImageIdQuery query = new FetchProjectImageIdQuery(UUID.randomUUID(), null);

        Assertions.assertThrows(IllegalArgumentException.class, () -> service.fetchProjectImageId(query));
    }

    @Test
    public void whenProjectExistsAndParamIsValidThenExpectImageId() {

        SampleData data = sampleBuilder.builder()
                .gameServer()
                .project()
                .configProjectRegion()
                .build();

        FetchProjectImageIdQuery query = new FetchProjectImageIdQuery(data.getProject().getId(), data.getFlavor().getId().toString());
        FetchProjectImageIdResponse response = service.fetchProjectImageId(query);

        Assertions.assertEquals(data.getImage().getId(), response.getImageId());
    }

    @Test
    public void whenProjectDoestNotExistsThenExpectNull() {

        Flavor flavor = sampleBuilder.builder()
                .flavor()
                .build()
                .getFlavor();

        FetchProjectImageIdQuery query = new FetchProjectImageIdQuery(UUID.randomUUID(), flavor.getId().toString());
        FetchProjectImageIdResponse response = service.fetchProjectImageId(query);

        Assertions.assertNull(response.getImageId());
    }

    @Test
    public void whenFlavorDoestNotExistsdThenExpectNull() {

        SampleData data = sampleBuilder.builder()
                .gameServer()
                .project()
                .configProjectRegion()
                .build();

        FetchProjectImageIdQuery query = new FetchProjectImageIdQuery(data.getProject().getId(), UUID.randomUUID().toString());
        FetchProjectImageIdResponse response = service.fetchProjectImageId(query);

        Assertions.assertNull(response.getImageId());
    }
}
