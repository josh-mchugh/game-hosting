package com.example.demo.awx.host.entity.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class AwxHostTest {

    @Test
    public void whenModelHasIdThenReturnId() {

        AwxHost model = AwxHost.builder()
                .id("id")
                .build();

        Assertions.assertEquals("id", model.getId());
    }

    @Test
    public void whenModelHasAwxIdThenReturnAwxId() {

        AwxHost model = AwxHost.builder()
                .awxId(1L)
                .build();

        Assertions.assertEquals(1L, model.getAwxId());
    }

    @Test
    public void whenModelHasHostnameThenReturnHostname() {

        AwxHost model = AwxHost.builder()
                .hostname("hostname")
                .build();

        Assertions.assertEquals("hostname", model.getHostname());
    }

    @Test
    public void whenModelHasDescriptionThenReturnDescription() {

        AwxHost model = AwxHost.builder()
                .description("description")
                .build();

        Assertions.assertEquals("description", model.getDescription());
    }

    @Test
    public void whenModelIsEnabledThenReturnTrue() {

        AwxHost model = AwxHost.builder()
                .enabled(true)
                .build();

        Assertions.assertTrue(model.getEnabled());
    }

    @Test
    public void whenModelToString() {

        AwxHost model = model();

        String expected = "AwxHost(id=id, awxId=1, hostname=hostname, description=description, enabled=true)";

        Assertions.assertEquals(expected, model.toString());
    }

    @Test
    public void whenModelHashCode()  {

        AwxHost model = model();

        Assertions.assertEquals(-189066089, model.hashCode());
    }

    @Test
    public void whenModelEquals() {

        AwxHost model1 = model();
        AwxHost model2 = model();

        Assertions.assertEquals(model1, model2);
    }

    @Test
    public void whenModelNotEquals() {

        AwxHost model = model();

        Assertions.assertNotEquals(model, AwxHost.builder().build());
    }

    private AwxHost model() {

        return AwxHost.builder()
                .id("id")
                .awxId(1L)
                .hostname("hostname")
                .description("description")
                .enabled(true)
                .build();
    }

}
