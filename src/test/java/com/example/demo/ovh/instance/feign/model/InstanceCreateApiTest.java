package com.example.demo.ovh.instance.feign.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class InstanceCreateApiTest {

    @Test
    public void whenModelHasNameThenReturnName() {

        InstanceCreateApi model = InstanceCreateApi.builder()
                .name("name")
                .build();

        Assertions.assertEquals("name", model.getName());
    }

    @Test
    public void whenModelHasFlavorIdThenReturnFlavorId() {

        InstanceCreateApi model = InstanceCreateApi.builder()
                .flavorId("flavorId")
                .build();

        Assertions.assertEquals("flavorId", model.getFlavorId());
    }

    @Test
    public void whenModelHasRegionThenReturnRegion() {

        InstanceCreateApi model = InstanceCreateApi.builder()
                .region("region")
                .build();

        Assertions.assertEquals("region", model.getRegion());
    }

    @Test
    public void whenModelHasImageIdThenReturnImageId() {

        InstanceCreateApi model = InstanceCreateApi.builder()
                .imageId("imageId")
                .build();

        Assertions.assertEquals("imageId", model.getImageId());
    }

    @Test
    public void whenModelHasGroupIdThenGroupId() {

        InstanceCreateApi model = InstanceCreateApi.builder()
                .groupId("groupId")
                .build();

        Assertions.assertEquals("groupId", model.getGroupId());
    }

    @Test
    public void whenModelHasSshKeyThenSshKey() {

        InstanceCreateApi model = InstanceCreateApi.builder()
                .sshKeyId("sshKeyId")
                .build();

        Assertions.assertEquals("sshKeyId", model.getSshKeyId());
    }

    @Test
    public void whenModelHasMonthlyBillingThenReturnMonthlyBilling() {

        InstanceCreateApi model = InstanceCreateApi.builder()
                .monthlyBilling(false)
                .build();

        Assertions.assertFalse(model.getMonthlyBilling());
    }

    @Test
    public void whenModelToString() {

        InstanceCreateApi model = model();

        String expected = "InstanceCreateApi(name=name, flavorId=flavorId, region=region, imageId=imageId, groupId=groupId, sshKeyId=sshKeyId, monthlyBilling=true)";

        Assertions.assertEquals(expected, model.toString());
    }

    @Test
    public void whenModelHashCode() {

        InstanceCreateApi model = model();

        Assertions.assertEquals(-641009730, model.hashCode());
    }

    @Test
    public void whenModelEquals() {

        InstanceCreateApi model1 = model();
        InstanceCreateApi model2 = model();

        Assertions.assertEquals(model1, model2);
    }

    @Test
    public void whenModelNotEquals() {

        InstanceCreateApi model = model();

        Assertions.assertNotEquals(model, InstanceCreateApi.builder().build());
    }

    private InstanceCreateApi model() {

        return InstanceCreateApi.builder()
                .name("name")
                .flavorId("flavorId")
                .region("region")
                .imageId("imageId")
                .groupId("groupId")
                .sshKeyId("sshKeyId")
                .monthlyBilling(true)
                .build();
    }
}
