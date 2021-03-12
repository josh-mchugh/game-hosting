package com.example.demo.ovh.image.scheduler.projection.projection;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.UUID;

public class ImageProjectionTest {

    @Test
    public void whenModelHasIdThenReturnId() {

        UUID id = UUID.randomUUID();

        ImageProjection model = new ImageProjection(id.toString(), null, null, null, null, null, null, null, null, null, null, null, null, null);

        Assertions.assertEquals(id, model.getId());
    }

    @Test
    public void whenModelHasOvhIdThenReturnOvhId() {

        ImageProjection model = new ImageProjection(UUID.randomUUID().toString(), "ovhId", null, null, null, null, null, null, null, null, null, null, null, null);

        Assertions.assertEquals("ovhId", model.getOvhId());
    }

    @Test
    public void whenModelHasNameThenReturnName() {

        ImageProjection model = new ImageProjection(UUID.randomUUID().toString(), null, "name", null, null, null, null, null, null, null, null, null, null, null);

        Assertions.assertEquals("name", model.getName());
    }

    @Test
    public void whenModelHasTypeThenReturnType() {

        ImageProjection model = new ImageProjection(UUID.randomUUID().toString(), null, null, "type", null, null, null, null, null, null, null, null, null, null);

        Assertions.assertEquals("type", model.getType());
    }

    @Test
    public void whenModelHasImageCreatedDateThenReturnImageCreatedDate() {

        LocalDateTime imageCreatedDate = LocalDateTime.now();

        ImageProjection model = new ImageProjection(UUID.randomUUID().toString(), null, null, null, imageCreatedDate, null, null, null, null, null, null, null, null, null);

        Assertions.assertEquals(imageCreatedDate, model.getImageCreatedDate());
    }

    @Test
    public void whenModelHasFlavorTypeThenReturnFlavorType() {

        ImageProjection model = new ImageProjection(UUID.randomUUID().toString(), null, null, null, null, "flavorType", null, null, null, null, null, null, null, null);

        Assertions.assertEquals("flavorType", model.getFlavorType());
    }

    @Test
    public void whenModelHasHourlyThenReturnHourly() {

        ImageProjection model = new ImageProjection(UUID.randomUUID().toString(), null, null, null, null, null, "hourly", null, null, null, null, null, null, null);

        Assertions.assertEquals("hourly", model.getHourly());
    }

    @Test
    public void whenModelHasMonthlyThenReturnMonthly() {

        ImageProjection model = new ImageProjection(UUID.randomUUID().toString(), null, null, null, null, null, null, "monthly", null, null, null, null, null, null);

        Assertions.assertEquals("monthly", model.getMonthly());
    }

    @Test
    public void whenModelHasSizeThenReturnSize() {

        ImageProjection model = new ImageProjection(UUID.randomUUID().toString(), null, null, null, null, null, null, null, 1.0D, null, null, null, null, null);

        Assertions.assertEquals(1.0D, model.getSize());
    }

    @Test
    public void whenModelHasMinSizeThenReturnMinSize() {

        ImageProjection model = new ImageProjection(UUID.randomUUID().toString(), null, null, null, null, null, null, null, null, 1, null, null, null, null);

        Assertions.assertEquals(1, model.getMinRam());
    }

    @Test
    public void whenModelHasMinDiskThenReturnMinDisk() {

        ImageProjection model = new ImageProjection(UUID.randomUUID().toString(), null, null, null, null, null, null, null, null, null, 1, null, null, null);

        Assertions.assertEquals(1, model.getMinDisk());
    }

    @Test
    public void whenModelHasUsernameThenReturnUsername() {

        ImageProjection model = new ImageProjection(UUID.randomUUID().toString(), null, null, null, null, null, null, null, null, null, null, "username", null, null);

        Assertions.assertEquals("username", model.getUsername());
    }

    @Test
    public void whenModelHasStatusThenReturnStatus() {

        ImageProjection model = new ImageProjection(UUID.randomUUID().toString(), null, null, null, null, null, null, null, null, null, null, null, "status", null);

        Assertions.assertEquals("status", model.getStatus());
    }

    @Test
    public void whenModelHasVisibilityThenReturnVisibility() {

        ImageProjection model = new ImageProjection(UUID.randomUUID().toString(), null, null, null, null, null, null, null, null, null, null, null, null, "visibility");

        Assertions.assertEquals("visibility", model.getVisibility());
    }

    @Test
    public void whenModelToString() {

        ImageProjection model = model();

        String expected = "ImageProjection(id=38e9be2b-18ba-4ea2-b9b8-44fbed4efe70, ovhId=ovhId, name=name, type=type, imageCreatedDate=2021-03-11T12:20, flavorType=flavorType, hourly=hourly, monthly=monthly, size=1.0, minRam=1, minDisk=1, username=username, status=status, visibility=visibility)";

        Assertions.assertEquals(expected, model.toString());
    }

    @Test
    public void whenModelHashCode() {

        ImageProjection model = model();

        Assertions.assertEquals(1069468982, model.hashCode());
    }

    @Test
    public void whenModelEquals() {

        ImageProjection model1 = model();
        ImageProjection model2 = model();

        Assertions.assertEquals(model1, model2);
    }

    @Test
    public void whenModelNotEquals() {

        ImageProjection model = model();

        Assertions.assertNotEquals(model, new ImageProjection(UUID.randomUUID().toString(), null, null, null, null, null, null, null, null, null, null, null, null, null));
    }

    private ImageProjection model() {

        return new ImageProjection(UUID.fromString("38e9be2b-18ba-4ea2-b9b8-44fbed4efe70").toString(), "ovhId", "name", "type", LocalDateTime.of(2021, 3, 11, 12, 20), "flavorType", "hourly", "monthly", 1.0D, 1, 1, "username", "status", "visibility");
    }
}
