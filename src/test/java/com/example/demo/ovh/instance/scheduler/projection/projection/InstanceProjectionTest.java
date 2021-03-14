package com.example.demo.ovh.instance.scheduler.projection.projection;

import com.example.demo.ovh.instance.entity.InstanceStatus;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.UUID;

public class InstanceProjectionTest {

    @Test
    public void whenModelHasIdThenReturnId() {

        UUID id = UUID.randomUUID();

        InstanceProjection model = new InstanceProjection(id.toString(), null, null, null, null, null, null);

        Assertions.assertEquals(id, model.getId());
    }

    @Test
    public void whenModelHasOvhIdThenOvhId() {

        InstanceProjection model = new InstanceProjection(UUID.randomUUID().toString(), "ovhId", null, null, null, null, null);

        Assertions.assertEquals("ovhId", model.getOvhId());
    }

    @Test
    public void whenModelHasNameThenReturnName() {

        InstanceProjection model = new InstanceProjection(UUID.randomUUID().toString(), null, "name", null, null, null, null);

        Assertions.assertEquals("name", model.getName());
    }

    @Test
    public void whenModelHasInstanceCreatedDateThenReturnInstanceCreatedDate() {

        LocalDateTime instanceCreatedDate = LocalDateTime.now();

        InstanceProjection model = new InstanceProjection(UUID.randomUUID().toString(), null, null, instanceCreatedDate, null, null, null);

        Assertions.assertEquals(instanceCreatedDate, model.getInstanceCreatedDate());
    }

    @Test
    public void whenModelHasStatusThenReturnStatus() {

        InstanceProjection model = new InstanceProjection(UUID.randomUUID().toString(), null, null, null, InstanceStatus.ACTIVE, null, null);

        Assertions.assertEquals(InstanceStatus.ACTIVE, model.getStatus());
    }

    @Test
    public void whenModelHasIp4AddressThenIp6Address() {

        InstanceProjection model = new InstanceProjection(UUID.randomUUID().toString(), null, null, null, null, "ip4Address", null);

        Assertions.assertEquals("ip4Address", model.getIp4Address());
    }

    @Test
    public void whenModelHasIp6AddressThenIp6Address() {

        InstanceProjection model = new InstanceProjection(UUID.randomUUID().toString(), null, null, null, null, null, "ip6Address");

        Assertions.assertEquals("ip6Address", model.getIp6Address());
    }

    @Test
    public void whenModelToString() {

        InstanceProjection model = model();

        String expected = "InstanceProjection(id=948ccd74-c90d-4f96-b28d-a440e5c1da76, ovhId=ovhId, name=name, instanceCreatedDate=2021-03-13T18:22, status=ACTIVE, ip4Address=ip4Address, ip6Address=ip6Address)";

        Assertions.assertEquals(expected, model.toString());
    }

    @Test
    public void whenModelHashCode() {

        InstanceProjection model = new InstanceProjection(UUID.fromString("948ccd74-c90d-4f96-b28d-a440e5c1da76").toString(), "ovhId", "name", LocalDateTime.of(2021, 3, 13, 18, 22), null, "ip4Address", "ip6Address");

        Assertions.assertEquals(1963432440, model.hashCode());
    }

    @Test
    public void whenModelEquals() {

        InstanceProjection model1 = model();
        InstanceProjection model2 = model();

        Assertions.assertEquals(model1, model2);
    }

    @Test
    public void whenModelNotEquals() {

        InstanceProjection model = model();

        Assertions.assertNotEquals(model, new InstanceProjection(UUID.randomUUID().toString(), null, null, null, null, null, null));
    }

    private InstanceProjection model() {

        return new InstanceProjection(UUID.fromString("948ccd74-c90d-4f96-b28d-a440e5c1da76").toString(), "ovhId", "name", LocalDateTime.of(2021, 3, 13, 18, 22), InstanceStatus.ACTIVE, "ip4Address", "ip6Address");
    }
}
