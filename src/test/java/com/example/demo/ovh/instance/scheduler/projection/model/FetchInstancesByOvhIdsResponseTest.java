package com.example.demo.ovh.instance.scheduler.projection.model;

import com.example.demo.ovh.instance.entity.InstanceStatus;
import com.example.demo.ovh.instance.scheduler.projection.projection.InstanceProjection;
import com.google.common.collect.ImmutableList;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.UUID;

public class FetchInstancesByOvhIdsResponseTest {

    @Test
    public void whenModelHasInstanceThenReturnInstances() {

        FetchInstancesByOvhIdsResponse model = new FetchInstancesByOvhIdsResponse(ImmutableList.of(projection()));

        Assertions.assertEquals(ImmutableList.of(projection()), model.getInstances());
    }

    @Test
    public void whenModelToString() {

        FetchInstancesByOvhIdsResponse model = model();

        String expected = "FetchInstancesByOvhIdsResponse(instances=[InstanceProjection(id=948ccd74-c90d-4f96-b28d-a440e5c1da76, ovhId=ovhId, name=name, instanceCreatedDate=2021-03-13T18:22, status=ACTIVE, ip4Address=ip4Address, ip6Address=ip6Address)])";

        Assertions.assertEquals(expected, model.toString());
    }

    @Test
    public void whenModelHashCode() {

        InstanceProjection projection = new InstanceProjection(UUID.fromString("948ccd74-c90d-4f96-b28d-a440e5c1da76").toString(), "ovhId", "name", LocalDateTime.of(2021, 3, 13, 18, 22), null, "ip4Address", "ip6Address");
        FetchInstancesByOvhIdsResponse model = new FetchInstancesByOvhIdsResponse(ImmutableList.of(projection));

        Assertions.assertEquals(1963432530, model.hashCode());
    }

    @Test
    public void whenModelEquals() {

        FetchInstancesByOvhIdsResponse model1 = model();
        FetchInstancesByOvhIdsResponse model2 = model();

        Assertions.assertEquals(model1, model2);
    }

    @Test
    public void whenModelNotEquals() {

        FetchInstancesByOvhIdsResponse model = model();

        Assertions.assertNotEquals(model, new FetchInstancesByOvhIdsResponse(null));
    }

    private FetchInstancesByOvhIdsResponse model() {

        return new FetchInstancesByOvhIdsResponse(ImmutableList.of(projection()));
    }

    private InstanceProjection projection() {

        return new InstanceProjection(UUID.fromString("948ccd74-c90d-4f96-b28d-a440e5c1da76").toString(), "ovhId", "name", LocalDateTime.of(2021, 3, 13, 18, 22), InstanceStatus.ACTIVE, "ip4Address", "ip6Address");
    }
}
