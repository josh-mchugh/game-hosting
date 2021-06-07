package com.example.demo.project.aggregate.saga.projection;

import com.example.demo.project.aggregate.saga.projection.model.FetchProjectCreateInstanceDetailsQuery;
import com.example.demo.project.aggregate.saga.projection.model.FetchProjectCreateInstanceDetailsResponse;
import com.example.demo.sample.SampleBuilder;
import com.example.demo.sample.SampleData;
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
public class ProjectBuildSagaServiceFetchProjectCreateInstanceDetailsTest {

    @Autowired
    private IProjectBuildProjectionService service;

    @Autowired
    private SampleBuilder sampleBuilder;

    @Test
    public void whenParamIsNullThenExpectException() {

        Assertions.assertThrows(UndeclaredThrowableException.class, () -> service.fetchProjectCreateInstanceDetails(null));
    }

    @Test
    public void whenParamHasNullIdThenThenExpectException() {

        FetchProjectCreateInstanceDetailsQuery query = new FetchProjectCreateInstanceDetailsQuery(null);

        Assertions.assertThrows(UndeclaredThrowableException.class, () -> service.fetchProjectCreateInstanceDetails(query));
    }

    @Test
    public void whenProjectExistsThenExpectNotNull() {

        SampleData data = sampleBuilder.builder()
                .project()
                .configProjectRegion()
                .configProjectServer()
                .configProjectBilling()
                .instanceGroup()
                .build();

        FetchProjectCreateInstanceDetailsQuery query = new FetchProjectCreateInstanceDetailsQuery(data.getProject().getId());

        Assertions.assertNotNull(service.fetchProjectCreateInstanceDetails(query));
    }

    @Test
    public void whenProjectHasRegionThenExpectRegionName() {

        SampleData data = sampleBuilder.builder()
                .project()
                .configProjectRegion()
                .configProjectServer()
                .configProjectBilling()
                .instanceGroup()
                .build();

        FetchProjectCreateInstanceDetailsQuery query = new FetchProjectCreateInstanceDetailsQuery(data.getProject().getId());
        FetchProjectCreateInstanceDetailsResponse response =  service.fetchProjectCreateInstanceDetails(query);

        Assertions.assertEquals(data.getRegion().getName(), response.getRegionName());
    }

    @Test
    public void whenProjectHasFlavorThenExpectFlavorId() {

        SampleData data = sampleBuilder.builder()
                .project()
                .configProjectRegion()
                .configProjectServer()
                .configProjectBilling()
                .instanceGroup()
                .build();

        FetchProjectCreateInstanceDetailsQuery query = new FetchProjectCreateInstanceDetailsQuery(data.getProject().getId());
        FetchProjectCreateInstanceDetailsResponse response =  service.fetchProjectCreateInstanceDetails(query);

        Assertions.assertEquals(data.getFlavor().getId(), response.getFlavorId());
    }

    @Test
    public void whenProjectHasServerConfiguredThenExpectFlavorOvhId() {

        SampleData data = sampleBuilder.builder()
                .project()
                .configProjectRegion()
                .configProjectServer()
                .configProjectBilling()
                .instanceGroup()
                .build();

        FetchProjectCreateInstanceDetailsQuery query = new FetchProjectCreateInstanceDetailsQuery(data.getProject().getId());
        FetchProjectCreateInstanceDetailsResponse response =  service.fetchProjectCreateInstanceDetails(query);

        Assertions.assertEquals(data.getFlavor().getOvhId(), response.getFlavorOvhId());
    }

    @Test
    public void whenProjectHasServerConfiguredThenExpectImageId() {

        SampleData data = sampleBuilder.builder()
                .project()
                .configProjectRegion()
                .configProjectServer()
                .configProjectBilling()
                .instanceGroup()
                .build();

        FetchProjectCreateInstanceDetailsQuery query = new FetchProjectCreateInstanceDetailsQuery(data.getProject().getId());
        FetchProjectCreateInstanceDetailsResponse response =  service.fetchProjectCreateInstanceDetails(query);

        Assertions.assertEquals(data.getImage().getId(), response.getImageId());
    }

    @Test
    public void whenProjectHasServerConfiguredThenExpectImageOvhId() {

        SampleData data = sampleBuilder.builder()
                .project()
                .configProjectRegion()
                .configProjectServer()
                .configProjectBilling()
                .instanceGroup()
                .build();

        FetchProjectCreateInstanceDetailsQuery query = new FetchProjectCreateInstanceDetailsQuery(data.getProject().getId());
        FetchProjectCreateInstanceDetailsResponse response =  service.fetchProjectCreateInstanceDetails(query);

        Assertions.assertEquals(data.getImage().getOvhId(), response.getImageOvhId());
    }

    @Test
    public void whenProjectHasInstanceGroupIdThenExpectInstanceGroupId() {

        SampleData data = sampleBuilder.builder()
                .project()
                .configProjectRegion()
                .configProjectServer()
                .configProjectBilling()
                .instanceGroup()
                .build();

        FetchProjectCreateInstanceDetailsQuery query = new FetchProjectCreateInstanceDetailsQuery(data.getProject().getId());
        FetchProjectCreateInstanceDetailsResponse response =  service.fetchProjectCreateInstanceDetails(query);

        Assertions.assertEquals(data.getInstanceGroup().getId(), response.getInstanceGroupId());
    }

    @Test
    public void whenProjectHasInstanceGroupOvhIdThenExpectInstanceGroupOvhId() {

        SampleData data = sampleBuilder.builder()
                .project()
                .configProjectRegion()
                .configProjectServer()
                .configProjectBilling()
                .instanceGroup()
                .build();

        FetchProjectCreateInstanceDetailsQuery query = new FetchProjectCreateInstanceDetailsQuery(data.getProject().getId());
        FetchProjectCreateInstanceDetailsResponse response =  service.fetchProjectCreateInstanceDetails(query);

        Assertions.assertEquals(data.getInstanceGroup().getOvhId(), response.getInstanceGroupOvhId());
    }

    @Test
    public void whenProjectDoesNotHaveRegionConfiguredThenExpectException() {

        SampleData data = sampleBuilder.builder()
                .project()
                .instanceGroup()
                .build();

        FetchProjectCreateInstanceDetailsQuery query = new FetchProjectCreateInstanceDetailsQuery(data.getProject().getId());

        Assertions.assertThrows(UndeclaredThrowableException.class, () -> service.fetchProjectCreateInstanceDetails(query));
    }

    @Test
    public void whenProjectDoesNotHaveServerConfiguredThenExpectException() {

        SampleData data = sampleBuilder.builder()
                .project()
                .configProjectRegion()
                .instanceGroup()
                .build();

        FetchProjectCreateInstanceDetailsQuery query = new FetchProjectCreateInstanceDetailsQuery(data.getProject().getId());

        Assertions.assertThrows(UndeclaredThrowableException.class, () -> service.fetchProjectCreateInstanceDetails(query));
    }

    @Test
    public void whenProjectDoesNotHaveInstanceGroupThenExpectException() {

        SampleData data = sampleBuilder.builder()
                .project()
                .configProjectRegion()
                .configProjectServer()
                .configProjectBilling()
                .build();

        FetchProjectCreateInstanceDetailsQuery query = new FetchProjectCreateInstanceDetailsQuery(data.getProject().getId());

        Assertions.assertThrows(UndeclaredThrowableException.class, () -> service.fetchProjectCreateInstanceDetails(query));
    }
}
