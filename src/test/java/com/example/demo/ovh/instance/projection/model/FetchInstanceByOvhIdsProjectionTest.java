package com.example.demo.ovh.instance.projection.model;

import com.example.demo.ovh.instance.entity.model.Instance;
import com.google.common.collect.ImmutableList;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class FetchInstanceByOvhIdsProjectionTest {

    @Test
    public void whenProjectionHasInstanceThenReturnInstances() {

        Instance instance1 = Instance.builder().build();
        Instance instance2 = Instance.builder().build();

        FetchInstanceByOvhIdsProjection projection = new FetchInstanceByOvhIdsProjection(ImmutableList.of(instance1, instance2));

        Assertions.assertEquals(ImmutableList.of(instance1, instance2), projection.getInstances());
    }

    @Test
    public void whenProjectionToString() {

        FetchInstanceByOvhIdsProjection projection = projection();

        String expected = "FetchInstanceByOvhIdsProjection(instances=[Instance(id=null, ovhId=null, name=null, status=null, instanceCreatedDate=null, ip4Address=null, ip6Address=null)])";

        Assertions.assertEquals(expected, projection.toString());
    }

    @Test
    public void whenProjectionHashCode() {

        FetchInstanceByOvhIdsProjection projection = projection();

        Assertions.assertEquals(64204748, projection.hashCode());
    }

    @Test
    public void whenProjectionEquals() {

        FetchInstanceByOvhIdsProjection projection1 = projection();
        FetchInstanceByOvhIdsProjection projection2 = projection();

        Assertions.assertEquals(projection1, projection2);
    }

    @Test
    public void whenProjectionNotEquals() {

        FetchInstanceByOvhIdsProjection projection = projection();

        Assertions.assertNotEquals(projection, new FetchInstanceByOvhIdsProjection(ImmutableList.of()));
    }

    private FetchInstanceByOvhIdsProjection projection() {

        return new FetchInstanceByOvhIdsProjection(ImmutableList.of(Instance.builder().build()));
    }
}
