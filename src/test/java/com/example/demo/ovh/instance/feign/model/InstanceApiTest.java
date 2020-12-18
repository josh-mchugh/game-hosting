package com.example.demo.ovh.instance.feign.model;

import com.example.demo.ovh.flavor.feign.model.FlavorApi;
import com.example.demo.ovh.instance.entity.InstanceStatus;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class InstanceApiTest {

    @Test
    public void whenModelHasIdThenReturnId() {

        InstanceApi model = new InstanceApi();
        model.setId("id");

        Assertions.assertEquals("id", model.getId());
    }

    @Test
    public void whenModelHasNameThenReturnName() {

        InstanceApi model = new InstanceApi();
        model.setName("name");

        Assertions.assertEquals("name", model.getName());
    }

    @Test
    public void whenModelHasIpAddressesThenReturnIpAddresses() {

        List<IpAddressApi> ipAddresses = Arrays.asList(new IpAddressApi(), new IpAddressApi());

        InstanceApi model = new InstanceApi();
        model.setIpAddresses(ipAddresses);

        Assertions.assertEquals(ipAddresses, model.getIpAddresses());
    }

    @Test
    public void whenModelHasStatusThenReturnStatus() {

        InstanceApi model = new InstanceApi();
        model.setStatus(InstanceStatus.ACTIVE);

        Assertions.assertEquals(InstanceStatus.ACTIVE, model.getStatus());
    }

    @Test
    public void whenModelHasCreatedDateThenReturnCreatedDate() {

        LocalDateTime createdDate = LocalDateTime.now();

        InstanceApi model = new InstanceApi();
        model.setCreatedDate(createdDate);

        Assertions.assertEquals(createdDate, model.getCreatedDate());
    }

    @Test
    public void whenModelHasRegionThenReturnRegion() {

        InstanceApi model = new InstanceApi();
        model.setRegion("region");

        Assertions.assertEquals("region", model.getRegion());
    }

    @Test
    public void whenModelHasFlavorThenReturnFlavor() {

        FlavorApi flavor = new FlavorApi();

        InstanceApi model = new InstanceApi();
        model.setFlavor(flavor);

        Assertions.assertEquals(flavor, model.getFlavor());
    }

    @Test
    public void whenModelHasImageThenReturnImage() {

        ImageApi image = new ImageApi();

        InstanceApi model = new InstanceApi();
        model.setImage(image);

        Assertions.assertEquals(image, model.getImage());
    }

    @Test
    public void whenGetIp4AddressIsNullThenReturnNull() {

        InstanceApi model = new InstanceApi();

        Assertions.assertNull(model.getIp4Address());
    }

    @Test
    public void whenGetIp4AddressIsValidThenReturnIpAddress() {

        IpAddressApi ipAddress = new IpAddressApi();
        ipAddress.setVersion(4);
        ipAddress.setIp("ip");

        List<IpAddressApi> ipAddresses = Collections.singletonList(ipAddress);

        InstanceApi model = new InstanceApi();
        model.setIpAddresses(ipAddresses);

        Assertions.assertEquals("ip", model.getIp4Address());
    }

    @Test
    public void whenGetIp6AddressIsNullThenReturnNull() {

        InstanceApi model = new InstanceApi();

        Assertions.assertNull(model.getIp6Address());
    }

    @Test
    public void whenGetIp6AddressIsValidThenReturnIpAddress() {

        IpAddressApi ipAddress = new IpAddressApi();
        ipAddress.setVersion(6);
        ipAddress.setIp("ip");

        List<IpAddressApi> ipAddresses = Collections.singletonList(ipAddress);

        InstanceApi model = new InstanceApi();
        model.setIpAddresses(ipAddresses);

        Assertions.assertEquals("ip", model.getIp6Address());
    }

    @Test
    public void whenGetIpAddressIsEmptyListThenReturnNull() {

        InstanceApi model = new InstanceApi();
        model.setIpAddresses(new ArrayList<>());

        Assertions.assertNull(model.getIp4Address());
    }

    @Test
    public void whenModelToString() {

        InstanceApi model = model();

        String expected = "InstanceApi(id=id, name=name, ipAddresses=[], status=ACTIVE, createdDate=2020-12-02T22:20, region=region, flavor=FlavorApi(id=null, name=null, available=false, disk=null, inboundBandwidth=null, osType=null, outboundBandwidth=null, planCodes=null, quota=null, ram=null, regionName=null, type=null, vcpus=null), image=ImageApi(imageId=null, name=null, creationDate=null, flavorType=null, minDisk=null, minRam=null, planCode=null, regionName=null, size=null, status=null, type=null, user=null, visibility=null))";

        Assertions.assertEquals(expected, model.toString());
    }

    @Test
    public void whenModelHashCode() {

        InstanceApi model = new InstanceApi();
        model.setId("id");
        model.setName("name");
        model.setIpAddresses(new ArrayList<>());
        model.setCreatedDate(LocalDateTime.of(2020, 12, 2, 22, 20));
        model.setRegion("region");
        model.setFlavor(new FlavorApi());
        model.setImage(new ImageApi());

        Assertions.assertEquals(106907680, model.hashCode());
    }

    @Test
    public void whenModelEquals() {

        InstanceApi model1 = model();
        InstanceApi model2 = model();

        Assertions.assertEquals(model1, model2);
    }

    @Test
    public void whenModelNotEquals() {

        InstanceApi model = model();

        Assertions.assertNotEquals(model, new InstanceApi());
    }

    private InstanceApi model() {

        InstanceApi model = new InstanceApi();
        model.setId("id");
        model.setName("name");
        model.setStatus(InstanceStatus.ACTIVE);
        model.setIpAddresses(new ArrayList<>());
        model.setCreatedDate(LocalDateTime.of(2020, 12, 2, 22, 20));
        model.setRegion("region");
        model.setFlavor(new FlavorApi());
        model.setImage(new ImageApi());

        return model;
    }
}
