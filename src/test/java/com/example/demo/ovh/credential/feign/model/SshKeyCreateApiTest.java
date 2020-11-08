package com.example.demo.ovh.credential.feign.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class SshKeyCreateApiTest {

    @Test
    public void whenApiHasNameThenReturnName() {

        SshKeyCreateApi api = SshKeyCreateApi.builder()
                .name("name")
                .build();

        Assertions.assertEquals("name", api.getName());
    }

    @Test
    public void whenApiHasPublicKeyThenReturnPublicKey() {

        SshKeyCreateApi api = SshKeyCreateApi.builder()
                .publicKey("public-key")
                .build();

        Assertions.assertEquals("public-key", api.getPublicKey());
    }

    @Test
    public void whenApiHasRegionThenReturnRegion() {

        SshKeyCreateApi api = SshKeyCreateApi.builder()
                .region("region")
                .build();

        Assertions.assertEquals("region", api.getRegion());
    }

    @Test
    public void whenApiToString() {

        SshKeyCreateApi api = api();

        String toString = "SshKeyCreateApi(name=name, publicKey=public-key, region=region)";

        Assertions.assertEquals(toString, api.toString());
    }

    @Test
    public void whenApiHashCode() {

        SshKeyCreateApi api = api();

        Assertions.assertEquals(-1448687837, api.hashCode());
    }

    @Test
    public void whenApiEquals() {

        SshKeyCreateApi api1 = api();
        SshKeyCreateApi api2 = api();

        Assertions.assertEquals(api1, api2);
    }

    @Test
    public void whenApiNotEquals() {

        SshKeyCreateApi api = api();

        Assertions.assertNotEquals(api, SshKeyCreateApi.builder().build());
    }

    private SshKeyCreateApi api() {

        return SshKeyCreateApi.builder()
                .name("name")
                .publicKey("public-key")
                .region("region")
                .build();
    }
}
