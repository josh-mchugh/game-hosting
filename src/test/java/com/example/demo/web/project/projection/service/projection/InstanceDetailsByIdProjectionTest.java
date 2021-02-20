package com.example.demo.web.project.projection.service.projection;

import com.example.demo.ovh.instance.entity.InstanceStatus;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class InstanceDetailsByIdProjectionTest {

    @Test
    public void whenModelHasOvhIdThenReturnOvhId() {

        InstanceDetailsByIdProjection model = new InstanceDetailsByIdProjection("ovhId", null, null);

        Assertions.assertEquals("ovhId", model.getOvhId());
    }

    @Test
    public void whenModelHasInstanceStatusThenReturnInstanceStatus() {

        InstanceDetailsByIdProjection model = new InstanceDetailsByIdProjection(null, InstanceStatus.ACTIVE, null);

        Assertions.assertEquals(InstanceStatus.ACTIVE, model.getInstanceStatus());
    }

    @Test
    public void whenModelHasIpAddress4ThenReturnIp4Address() {

        InstanceDetailsByIdProjection model = new InstanceDetailsByIdProjection(null, null, "ip4Address");

        Assertions.assertEquals("ip4Address", model.getIpAddress());
    }

    @Test
    public void whenModelToString() {

        InstanceDetailsByIdProjection model = model();

        String expected = "InstanceDetailsByIdProjection(ovhId=ovhId, instanceStatus=ACTIVE, ipAddress=ip4Address)";

        Assertions.assertEquals(expected, model.toString());
    }

    @Test
    public void whenModelHashCode() {

        InstanceDetailsByIdProjection model = new InstanceDetailsByIdProjection("ovhId", null, "ip4Address");

        Assertions.assertEquals(1284512367, model.hashCode());
    }

    @Test
    public void whenModelEquals() {

        InstanceDetailsByIdProjection model1 = model();
        InstanceDetailsByIdProjection model2 = model();

        Assertions.assertEquals(model1, model2);
    }

    @Test
    public void whenModelNotEquals() {

        InstanceDetailsByIdProjection model = model();

        Assertions.assertNotEquals(model, new InstanceDetailsByIdProjection(null, null, null));
    }

    private InstanceDetailsByIdProjection model() {

        return new InstanceDetailsByIdProjection("ovhId", InstanceStatus.ACTIVE, "ip4Address");
    }
}
