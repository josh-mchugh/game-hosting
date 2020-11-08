package com.example.demo.ovh.credential.feign.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class SshKeyApiTest {

    @Test
    public void whenApiHasIdThenReturnId() {

        SshKeyApi api = new SshKeyApi();
        api.setId("id");

        Assertions.assertEquals("id", api.getId());
    }

    @Test
    public void whenApiHasNameThenReturnName() {

        SshKeyApi api = new SshKeyApi();
        api.setName("name");

        Assertions.assertEquals("name", api.getName());
    }

    @Test
    public void whenApiHasPublicKeyThenReturnPublicKey() {

        SshKeyApi api = new SshKeyApi();
        api.setPublicKey("public-key");

        Assertions.assertEquals("public-key", api.getPublicKey());
    }

    @Test
    public void whenApiToString() {

        SshKeyApi api = api();

        String toString = "SshKeyApi(id=id, name=name, publicKey=public-key)";

        Assertions.assertEquals(toString, api.toString());
    }

    @Test
    public void whenApiHashCode() {

        SshKeyApi api = api();

        Assertions.assertEquals(2114256234, api.hashCode());
    }

    @Test
    public void whenApiEquals() {

        SshKeyApi api1 = api();
        SshKeyApi api2 = api();

        Assertions.assertEquals(api1, api2);
    }

    @Test
    public void whenApiNotEquals() {

        SshKeyApi api = api();

        Assertions.assertNotEquals(api, new SshKeyApi());
    }

    private SshKeyApi api() {

        SshKeyApi api = new SshKeyApi();
        api.setId("id");
        api.setName("name");
        api.setPublicKey("public-key");

        return api;
    }
}
