package com.example.demo.ovh.instance.feign.model;

import com.example.demo.ovh.feign.PlanCodeApi;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

public class ImageApiTest {

    @Test
    public void whenModelHasImageIdThenReturnImageId() {

        ImageApi model = new ImageApi();
        model.setImageId("id");

        Assertions.assertEquals("id", model.getImageId());
    }

    @Test
    public void whenModelHasNameThenReturnName() {

        ImageApi model = new ImageApi();
        model.setName("name");

        Assertions.assertEquals("name", model.getName());
    }

    @Test
    public void whenModelHasCreationDateThenReturnCreationDate() {

        LocalDateTime creationDate = LocalDateTime.now();

        ImageApi model = new ImageApi();
        model.setCreationDate(creationDate);

        Assertions.assertEquals(creationDate, model.getCreationDate());
    }

    @Test
    public void whenModelHasFlavorTypeThenReturnFlavorType() {

        ImageApi model = new ImageApi();
        model.setFlavorType("flavorType");

        Assertions.assertEquals("flavorType", model.getFlavorType());
    }

    @Test
    public void whenModelHasMinDiskThenReturnMinDisk() {

        ImageApi model = new ImageApi();
        model.setMinDisk(1);

        Assertions.assertEquals(1, model.getMinDisk());
    }

    @Test
    public void whenModelHasMinRamThenReturnMinRam() {

        ImageApi model = new ImageApi();
        model.setMinRam(1);

        Assertions.assertEquals(1, model.getMinRam());
    }

    @Test
    public void whenModelHasPlanCodeThenReturnPlanCode() {

        PlanCodeApi planCode = new PlanCodeApi();

        ImageApi model = new ImageApi();
        model.setPlanCode(planCode);

        Assertions.assertEquals(planCode, model.getPlanCode());
    }

    @Test
    public void whenModelHasRegionNameThenReturnRegionName() {

        ImageApi model = new ImageApi();
        model.setRegionName("regionName");

        Assertions.assertEquals("regionName", model.getRegionName());
    }

    @Test
    public void whenModelHasSizeThenReturnSize() {

        ImageApi model = new ImageApi();
        model.setSize(1.0D);

        Assertions.assertEquals(1.0D, model.getSize());
    }


    @Test
    public void whenModelHasStatusThenReturnStatus() {

        ImageApi model = new ImageApi();
        model.setStatus("status");

        Assertions.assertEquals("status", model.getStatus());
    }

    @Test
    public void whenModelHasTypeThenReturnType() {

        ImageApi model = new ImageApi();
        model.setType("type");

        Assertions.assertEquals("type", model.getType());
    }

    @Test
    public void whenModelHasUserThenReturnUser() {

        ImageApi model = new ImageApi();
        model.setUser("user");

        Assertions.assertEquals("user", model.getUser());
    }

    @Test
    public void whenModelHasVisibilityThenReturnVisibility() {

        ImageApi model = new ImageApi();
        model.setVisibility("visibility");

        Assertions.assertEquals("visibility", model.getVisibility());
    }

    @Test
    public void whenModelToString() {

        ImageApi model = model();

        String expected = "ImageApi(imageId=id, name=name, creationDate=2020-12-02T22:42, flavorType=flavorType, minDisk=1, minRam=1, planCode=PlanCodeApi(hourly=null, monthly=null), regionName=regionName, size=1.0, status=status, type=type, user=user, visibility=visibility)";

        Assertions.assertEquals(expected, model.toString());
    }

    @Test
    public void whenModelHashCode() {

        ImageApi model = model();

        Assertions.assertEquals(-1753056216, model.hashCode());
    }

    @Test
    public void whenModelEquals() {

        ImageApi model1 = model();
        ImageApi model2 = model();

        Assertions.assertEquals(model1, model2);
    }

    @Test
    public void whenModelNotEquals() {

        ImageApi model = model();

        Assertions.assertNotEquals(model, new ImageApi());
    }

    private ImageApi model() {

        ImageApi model = new ImageApi();
        model.setImageId("id");
        model.setName("name");
        model.setCreationDate(LocalDateTime.of(2020, 12, 2, 22, 42));
        model.setFlavorType("flavorType");
        model.setMinDisk(1);
        model.setMinRam(1);
        model.setPlanCode(new PlanCodeApi());
        model.setRegionName("regionName");
        model.setSize(1.0D);
        model.setStatus("status");
        model.setType("type");
        model.setUser("user");
        model.setVisibility("visibility");

        return model;
    }
}
