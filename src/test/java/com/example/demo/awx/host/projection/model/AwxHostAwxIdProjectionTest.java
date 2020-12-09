package com.example.demo.awx.host.projection.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class AwxHostAwxIdProjectionTest {

    @Test
    public void whenProjectHasAwxHostIdThenReturnAwxHostId() {

        AwxHostAwxIdProjection projection = new AwxHostAwxIdProjection("id", null);

        Assertions.assertEquals("id", projection.getAwxHostId());
    }

    @Test
    public void whenProjectionHasAwxIdThenReturnAwxId() {

        AwxHostAwxIdProjection projection = new AwxHostAwxIdProjection(null, 1L);

        Assertions.assertEquals(6019, projection.hashCode());
    }

    @Test
    public void whenProjectionToString() {

        AwxHostAwxIdProjection projection = projection();

        String expected= "AwxHostAwxIdProjection(awxHostId=id, awxId=1)";

        Assertions.assertEquals(expected, projection.toString());
    }

    @Test
    public void whenProjectionHashCode() {

        AwxHostAwxIdProjection projection = projection();

        Assertions.assertEquals(201427, projection.hashCode());
    }

    @Test
    public void whenProjectionEquals() {

        AwxHostAwxIdProjection projection1 = projection();
        AwxHostAwxIdProjection projection2 = projection();

        Assertions.assertEquals(projection1, projection2);
    }

    @Test
    public void whenProjectionNotEquals() {

        AwxHostAwxIdProjection projection = projection();

        Assertions.assertNotEquals(projection, new AwxHostAwxIdProjection("idk", 2L));
    }

    private AwxHostAwxIdProjection projection() {

        return new AwxHostAwxIdProjection("id", 1L);
    }
}
