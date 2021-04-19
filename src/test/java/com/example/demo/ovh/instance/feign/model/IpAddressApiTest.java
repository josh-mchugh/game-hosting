package com.example.demo.ovh.instance.feign.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class IpAddressApiTest {

    @Test
    public void whenModelHasIpThenReturnIp() {

        IpAddressApi model = new IpAddressApi();
        model.setIp("ip");

        Assertions.assertEquals("ip", model.getIp());
    }

    @Test
    public void whenModelHasVersionThenReturnVersion() {

        IpAddressApi model = new IpAddressApi();
        model.setVersion(1);

        Assertions.assertEquals(1, model.getVersion());
    }

    @Test
    public void whenModelToString() {

        IpAddressApi model = model();

        String expected = "IpAddressApi(ip=ip, version=1)";

        Assertions.assertEquals(expected, model.toString());
    }

    @Test
    public void whenModelHashCode() {

        IpAddressApi model = model();

        Assertions.assertEquals(6907, model.hashCode());
    }

    @Test
    public void whenModelEquals() {

        IpAddressApi model1 = model();
        IpAddressApi model2 = model();

        Assertions.assertEquals(model1, model2);
    }

    @Test
    public void whenModelNotEquals() {

        IpAddressApi model = model();

        Assertions.assertNotEquals(model, new IpAddressApi());
    }

    private IpAddressApi model() {

        IpAddressApi model = new IpAddressApi();
        model.setIp("ip");
        model.setVersion(1);

        return model;
    }
}
