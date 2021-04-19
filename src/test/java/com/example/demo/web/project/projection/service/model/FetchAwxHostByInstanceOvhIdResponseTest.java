package com.example.demo.web.project.projection.service.model;

import com.example.demo.web.project.projection.service.projection.AwxHostProjection;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.UUID;

public class FetchAwxHostByInstanceOvhIdResponseTest {

    @Test
    public void whenModelHasAwxHostThenReturnAwxHost() {

        String id = UUID.randomUUID().toString();

        AwxHostProjection awxHost = new AwxHostProjection(id, 1L);
        FetchAwxHostByInstanceOvhIdResponse model = new FetchAwxHostByInstanceOvhIdResponse(awxHost);

        Assertions.assertEquals(new AwxHostProjection(id, 1L), model.getAwxHost());
    }

    @Test
    public void whenModelToString() {

        FetchAwxHostByInstanceOvhIdResponse model = model();

        String expected = "FetchAwxHostByInstanceOvhIdResponse(awxHost=AwxHostProjection(id=9dc3f8d2-165f-42cb-8b7b-9af22827a99b, awxId=1))";

        Assertions.assertEquals(expected, model.toString());
    }

    @Test
    public void whenModelHashCode() {

        FetchAwxHostByInstanceOvhIdResponse model = model();

        Assertions.assertEquals(683710335, model.hashCode());
    }

    @Test
    public void whenModelEquals() {

        FetchAwxHostByInstanceOvhIdResponse model1 = model();
        FetchAwxHostByInstanceOvhIdResponse model2 = model();

        Assertions.assertEquals(model1, model2);
    }

    @Test
    public void whenModelNotEquals() {

        FetchAwxHostByInstanceOvhIdResponse model = model();

        Assertions.assertNotEquals(model, new FetchAwxHostByInstanceOvhIdResponse(null));
    }

    private FetchAwxHostByInstanceOvhIdResponse model() {

        return new FetchAwxHostByInstanceOvhIdResponse(new AwxHostProjection(UUID.fromString("9dc3f8d2-165f-42cb-8b7b-9af22827a99b").toString(), 1L));
    }
}
