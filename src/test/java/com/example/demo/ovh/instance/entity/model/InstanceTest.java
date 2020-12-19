package com.example.demo.ovh.instance.entity.model;

import com.example.demo.ovh.instance.entity.InstanceStatus;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

public class InstanceTest {

    @Test
    public void whenModelHasIdThenReturnId() {

        Instance model = Instance.builder()
                .id("id")
                .build();

        Assertions.assertEquals("id", model.getId());
    }

    @Test
    public void whenModelHasOvhIdThenReturnOvhId() {

        Instance model = Instance.builder()
                .ovhId("ovhId")
                .build();

        Assertions.assertEquals("ovhId", model.getOvhId());
    }

    @Test
    public void whenModelHasNameThenReturnName() {

        Instance model = Instance.builder()
                .name("name")
                .build();

        Assertions.assertEquals("name", model.getName());
    }

    @Test
    public void whenModelHasThenReturnStatus() {

        Instance model = Instance.builder()
                .status(InstanceStatus.ACTIVE)
                .build();

        Assertions.assertEquals(InstanceStatus.ACTIVE, model.getStatus());
    }

    @Test
    public void whenModelHasInstanceCreatedDateThenReturnInstanceCreatedDate() {

        LocalDateTime instanceCreatedDate = LocalDateTime.now();

        Instance model = Instance.builder()
                .instanceCreatedDate(instanceCreatedDate)
                .build();

        Assertions.assertEquals(instanceCreatedDate, model.getInstanceCreatedDate());
    }

    @Test
    public void whenModelHasIp4AddressThenReturnIp4Address() {

        Instance model = Instance.builder()
                .ip4Address("ip4Address")
                .build();

        Assertions.assertEquals("ip4Address", model.getIp4Address());
    }

    @Test
    public void whenModelHasIp6addressThenReturnIp6Address() {

        Instance model = Instance.builder()
                .ip6Address("ip6Address")
                .build();

        Assertions.assertEquals("ip6Address", model.getIp6Address());
    }

    @Test
    public void whenModelToString() {

        Instance model = Instance.builder()
                .id("id")
                .ovhId("ovhId")
                .name("name")
                .status(InstanceStatus.ACTIVE)
                .instanceCreatedDate(LocalDateTime.of(2020, 11, 27, 18,19))
                .ip4Address("ip4Address")
                .ip6Address("ip6Address")
                .build();

        String expected = "Instance(id=id, ovhId=ovhId, name=name, status=ACTIVE, instanceCreatedDate=2020-11-27T18:19, ip4Address=ip4Address, ip6Address=ip6Address)";

        Assertions.assertEquals(expected, model.toString());
    }

    @Test
    public void whenModelHasCode() {

        Instance model = model();

        Assertions.assertEquals(1062244323, model.hashCode());
    }

    @Test
    public void whenModelEquals() {

        Instance model1 = model();
        Instance model2 = model();

        Assertions.assertEquals(model1, model2);
    }

    @Test
    public void whenModelNotEquals() {

        Instance model = model();

        Assertions.assertNotEquals(model, Instance.builder().build());
    }

    private Instance model() {

        return Instance.builder()
                .id("id")
                .ovhId("ovhId")
                .name("name")
                .instanceCreatedDate(LocalDateTime.of(2020, 11, 27, 18,19))
                .ip4Address("ip4Address")
                .ip6Address("ip6Address")
                .build();
    }
}
