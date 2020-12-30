package com.example.demo.ovh.instance.projection.model;

import com.example.demo.ovh.instance.entity.InstanceStatus;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class FetchInstanceDetailsByProjectIdProjectionTest {

    @Test
    public void whenModelHasOvhIdThenReturnOvhId() {

        FetchInstanceDetailsByProjectIdProjection model = new FetchInstanceDetailsByProjectIdProjection("ovhId", null, null);

        Assertions.assertEquals("ovhId", model.getOvhId());
    }

    @Test
    public void whenModelHasInstanceStatusThenReturnInstanceStatus() {

        FetchInstanceDetailsByProjectIdProjection model = new FetchInstanceDetailsByProjectIdProjection(null, InstanceStatus.ACTIVE, null);

        Assertions.assertEquals(InstanceStatus.ACTIVE, model.getInstanceStatus());
    }

    @Test
    public void whenModelHasIpAddress4ThenReturnIp4Address() {

        FetchInstanceDetailsByProjectIdProjection model = new FetchInstanceDetailsByProjectIdProjection(null, null, "ip4Address");

        Assertions.assertEquals("ip4Address", model.getIpAddress());
    }

    @Test
    public void whenModelToString() {

        FetchInstanceDetailsByProjectIdProjection model = model();

        String expected = "FetchInstanceDetailsByProjectIdProjection(ovhId=ovhId, instanceStatus=ACTIVE, ipAddress=ip4Address)";

        Assertions.assertEquals(expected, model.toString());
    }

    @Test
    public void whenModelHashCode() {

        FetchInstanceDetailsByProjectIdProjection model = new FetchInstanceDetailsByProjectIdProjection("ovhId", null, "ip4Address");

        Assertions.assertEquals(1284512367, model.hashCode());
    }

    @Test
    public void whenModelEquals() {

        FetchInstanceDetailsByProjectIdProjection model1 = model();
        FetchInstanceDetailsByProjectIdProjection model2 = model();

        Assertions.assertEquals(model1, model2);
    }

    @Test
    public void whenModelNotEquals() {

        FetchInstanceDetailsByProjectIdProjection model = model();

        Assertions.assertNotEquals(model, new FetchInstanceDetailsByProjectIdProjection(null, null, null));
    }

    private FetchInstanceDetailsByProjectIdProjection model() {

        return new FetchInstanceDetailsByProjectIdProjection("ovhId", InstanceStatus.ACTIVE, "ip4Address");
    }
}
