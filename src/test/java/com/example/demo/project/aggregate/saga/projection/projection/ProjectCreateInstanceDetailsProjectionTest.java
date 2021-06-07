package com.example.demo.project.aggregate.saga.projection.projection;

import com.example.demo.project.aggregate.saga.projection.project.ProjectCreateInstanceDetailsProjection;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.UUID;

public class ProjectCreateInstanceDetailsProjectionTest {

    @Test
    public void whenModelHasRegionNameThenReturnRegionName() {

        ProjectCreateInstanceDetailsProjection model = new ProjectCreateInstanceDetailsProjection("regionName", UUID.randomUUID().toString(), null, UUID.randomUUID().toString(), null, UUID.randomUUID().toString(), null);

        Assertions.assertEquals("regionName", model.getRegionName());
    }

    @Test
    public void whenModelHasFlavorIdThenReturnFlavorId() {

        UUID id = UUID.randomUUID();

        ProjectCreateInstanceDetailsProjection model = new ProjectCreateInstanceDetailsProjection(null, id.toString(), null, UUID.randomUUID().toString(), null, UUID.randomUUID().toString(), null);

        Assertions.assertEquals(id, model.getFlavorId());
    }

    @Test
    public void whenModelHasFlavorOvhIdThenReturnFlavorOvhId() {

        ProjectCreateInstanceDetailsProjection model = new ProjectCreateInstanceDetailsProjection(null, UUID.randomUUID().toString(), "flavorOvhId", UUID.randomUUID().toString(), null, UUID.randomUUID().toString(), null);

        Assertions.assertEquals("flavorOvhId", model.getFlavorOvhId());
    }

    @Test
    public void whenModelHasImageIdThenReturnImageId() {

        UUID id = UUID.randomUUID();

        ProjectCreateInstanceDetailsProjection model = new ProjectCreateInstanceDetailsProjection(null, UUID.randomUUID().toString(), null, id.toString(), null, UUID.randomUUID().toString(), null);

        Assertions.assertEquals(id, model.getImageId());
    }

    @Test
    public void whenModelHasImageOvhIdThenReturnImageOvhId() {

        ProjectCreateInstanceDetailsProjection model = new ProjectCreateInstanceDetailsProjection(null, UUID.randomUUID().toString(), null, UUID.randomUUID().toString(), "imageOvhId", UUID.randomUUID().toString(), null);

        Assertions.assertEquals("imageOvhId", model.getImageOvhId());
    }

    @Test
    public void whenModelHasInstanceGroupIdThenReturnInstanceGroupId() {

        UUID id = UUID.randomUUID();

        ProjectCreateInstanceDetailsProjection model = new ProjectCreateInstanceDetailsProjection(null, UUID.randomUUID().toString(), null, UUID.randomUUID().toString(), null, id.toString(), null);

        Assertions.assertEquals(id, model.getInstanceGroupId());
    }

    @Test
    public void whenModelHasInstanceGroupOvhIdThenReturnInstanceGroupOvhId() {

        ProjectCreateInstanceDetailsProjection model = new ProjectCreateInstanceDetailsProjection(null, UUID.randomUUID().toString(), null, UUID.randomUUID().toString(), null, UUID.randomUUID().toString(), "instanceGroupOvhId");

        Assertions.assertEquals("instanceGroupOvhId", model.getInstanceGroupOvhId());
    }

    @Test
    public void whenModelToString() {

        ProjectCreateInstanceDetailsProjection model = model();

        String expected = "ProjectCreateInstanceDetailsProjection(regionName=regionName, flavorId=c3650b97-a7d0-46af-92b9-9201de24c318, flavorOvhId=flavorOvhId, imageId=1eaa02f2-e2ea-4351-9a2a-c5fe5f0ff1d8, imageOvhId=imageOvhId, instanceGroupId=4f88bfaa-e6ce-42c4-a564-7032be5c1727, instanceGroupOvhId=instanceGroupOvhId)";

        Assertions.assertEquals(expected, model.toString());
    }

    @Test
    public void whenModelHashCode() {

        ProjectCreateInstanceDetailsProjection model = model();

        Assertions.assertEquals(-1611209562, model.hashCode());
    }

    @Test
    public void whenModelEquals() {

        ProjectCreateInstanceDetailsProjection model1 = model();
        ProjectCreateInstanceDetailsProjection model2 = model();

        Assertions.assertEquals(model1, model2);
    }

    @Test
    public void whenModelNotEquals() {

        ProjectCreateInstanceDetailsProjection model = model();

        Assertions.assertNotEquals(model, new ProjectCreateInstanceDetailsProjection(null, UUID.randomUUID().toString(), null, UUID.randomUUID().toString(), null, UUID.randomUUID().toString(), null));
    }

    private ProjectCreateInstanceDetailsProjection model() {

        UUID flavorId = UUID.fromString("c3650b97-a7d0-46af-92b9-9201de24c318");
        UUID imageId = UUID.fromString("1eaa02f2-e2ea-4351-9a2a-c5fe5f0ff1d8");
        UUID instanceGroupId = UUID.fromString("4f88bfaa-e6ce-42c4-a564-7032be5c1727");

        return new ProjectCreateInstanceDetailsProjection("regionName", flavorId.toString(), "flavorOvhId", imageId.toString(), "imageOvhId", instanceGroupId.toString(), "instanceGroupOvhId");
    }
}
