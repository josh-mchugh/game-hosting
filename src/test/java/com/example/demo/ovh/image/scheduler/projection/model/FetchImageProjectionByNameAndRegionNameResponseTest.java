package com.example.demo.ovh.image.scheduler.projection.model;

import com.example.demo.ovh.image.scheduler.projection.projection.ImageProjection;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.UUID;

public class FetchImageProjectionByNameAndRegionNameResponseTest {

    @Test
    public void whenModelHasImageThenExpectImage() {

        FetchImageProjectionByNameAndRegionNameResponse model = new FetchImageProjectionByNameAndRegionNameResponse(image());

        ImageProjection expected = image();

        Assertions.assertEquals(expected, model.getImage());
    }

    @Test
    public void whenModelToString() {

        FetchImageProjectionByNameAndRegionNameResponse model = model();

        String expected = "FetchImageProjectionByNameAndRegionNameResponse(image=ImageProjection(id=38e9be2b-18ba-4ea2-b9b8-44fbed4efe70, ovhId=ovhId, name=name, type=type, imageCreatedDate=2021-03-11T12:20, flavorType=flavorType, hourly=hourly, monthly=monthly, size=1.0, minRam=1, minDisk=1, username=username, status=status, visibility=visibility))";

        Assertions.assertEquals(expected, model.toString());
    }

    @Test
    public void whenModelHashCode() {

        FetchImageProjectionByNameAndRegionNameResponse model = model();

        Assertions.assertEquals(1069469041, model.hashCode());
    }

    @Test
    public void whenModelEquals() {

        FetchImageProjectionByNameAndRegionNameResponse model1 = model();
        FetchImageProjectionByNameAndRegionNameResponse model2 = model();

        Assertions.assertEquals(model1, model2);
    }

    @Test
    public void whenModelNotEquals() {

        FetchImageProjectionByNameAndRegionNameResponse model = model();

        Assertions.assertNotEquals(model, new FetchImageProjectionByNameAndRegionNameResponse(null));
    }

    private FetchImageProjectionByNameAndRegionNameResponse model() {

        return new FetchImageProjectionByNameAndRegionNameResponse(image());
    }

    private ImageProjection image() {

        return new ImageProjection(UUID.fromString("38e9be2b-18ba-4ea2-b9b8-44fbed4efe70").toString(), "ovhId", "name", "type", LocalDateTime.of(2021, 3, 11, 12, 20), "flavorType", "hourly", "monthly", 1.0D, 1, 1, "username", "status", "visibility");
    }
}
