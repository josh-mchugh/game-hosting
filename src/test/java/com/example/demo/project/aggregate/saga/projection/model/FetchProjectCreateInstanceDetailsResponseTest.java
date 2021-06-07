package com.example.demo.project.aggregate.saga.projection.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.UUID;

public class FetchProjectCreateInstanceDetailsResponseTest {

    @Test
    public void whenModelHasRegionNameThenReturnRegionName() {

        FetchProjectCreateInstanceDetailsResponse model = FetchProjectCreateInstanceDetailsResponse.builder()
                .regionName("regionName")
                .build();

        Assertions.assertEquals("regionName", model.getRegionName());
    }

    @Test
    public void whenModelHasFlavorIdThenReturnFlavorId() {

        UUID id = UUID.randomUUID();

        FetchProjectCreateInstanceDetailsResponse model = FetchProjectCreateInstanceDetailsResponse.builder()
                .flavorId(id)
                .build();

        Assertions.assertEquals(id, model.getFlavorId());
    }

    @Test
    public void whenModelHasFlavorOvhIdThenReturnFlavorOvhId() {

        FetchProjectCreateInstanceDetailsResponse model = FetchProjectCreateInstanceDetailsResponse.builder()
                .flavorOvhId("flavorOvhId")
                .build();

        Assertions.assertEquals("flavorOvhId", model.getFlavorOvhId());
    }

    @Test
    public void whenModelHasImageIdThenReturnImageId() {

        UUID id = UUID.randomUUID();

        FetchProjectCreateInstanceDetailsResponse model = FetchProjectCreateInstanceDetailsResponse.builder()
                .imageId(id)
                .build();

        Assertions.assertEquals(id, model.getImageId());
    }

    @Test
    public void whenModelHasImageOvhIdThenReturnImageOvhId() {

        FetchProjectCreateInstanceDetailsResponse model = FetchProjectCreateInstanceDetailsResponse.builder()
                .imageOvhId("imageOvhId")
                .build();

        Assertions.assertEquals("imageOvhId", model.getImageOvhId());
    }

    @Test
    public void whenModelHasInstanceGroupIdThenReturnInstanceGroupId() {

        UUID id = UUID.randomUUID();

        FetchProjectCreateInstanceDetailsResponse model = FetchProjectCreateInstanceDetailsResponse.builder()
                .instanceGroupId(id)
                .build();

        Assertions.assertEquals(id, model.getInstanceGroupId());
    }

    @Test
    public void whenModelHasInstanceGroupOvhIdThenReturnInstanceGroupOvhId() {

        FetchProjectCreateInstanceDetailsResponse model = FetchProjectCreateInstanceDetailsResponse.builder()
                .instanceGroupOvhId("instanceGroupOvhId")
                .build();

        Assertions.assertEquals("instanceGroupOvhId", model.getInstanceGroupOvhId());
    }

    @Test
    public void whenModelToString() {

        FetchProjectCreateInstanceDetailsResponse model = model();

        String expected = "FetchProjectCreateInstanceDetailsResponse(regionName=regionName, flavorId=34e1d2f6-d798-4691-968a-f72ccb8e76bb, flavorOvhId=flavorOvhId, imageId=819e609d-5b5a-48b8-9bd7-218ed7db4037, imageOvhId=imageOvhId, instanceGroupId=ba28a83e-8060-498c-a240-13847cb6a35a, instanceGroupOvhId=instanceGroupOvhId)";

        Assertions.assertEquals(expected, model.toString());
    }

    @Test
    public void whenModelHashCode() {

        FetchProjectCreateInstanceDetailsResponse model = model();

        Assertions.assertEquals(-1756542693, model.hashCode());
    }

    @Test
    public void whenModelEquals() {

        FetchProjectCreateInstanceDetailsResponse model1 = model();
        FetchProjectCreateInstanceDetailsResponse model2 = model();

        Assertions.assertEquals(model1, model2);
    }

    @Test
    public void whenModelNotEquals() {

        FetchProjectCreateInstanceDetailsResponse model = model();

        Assertions.assertNotEquals(model, FetchProjectCreateInstanceDetailsResponse.builder().build());
    }

    private FetchProjectCreateInstanceDetailsResponse model() {

        return FetchProjectCreateInstanceDetailsResponse.builder()
                .regionName("regionName")
                .flavorId(UUID.fromString("34e1d2f6-d798-4691-968a-f72ccb8e76bb"))
                .flavorOvhId("flavorOvhId")
                .imageId(UUID.fromString("819e609d-5b5a-48b8-9bd7-218ed7db4037"))
                .imageOvhId("imageOvhId")
                .instanceGroupId(UUID.fromString("ba28a83e-8060-498c-a240-13847cb6a35a"))
                .instanceGroupOvhId("instanceGroupOvhId")
                .build();
    }
}
