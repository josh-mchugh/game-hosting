package com.example.demo.awx.host.feign;

import com.example.demo.awx.host.feign.model.HostApi;
import com.example.demo.awx.host.feign.model.HostCreateApi;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class HostFeignServiceCreateHostTest {

    private HostClient hostClient;

    @BeforeEach
    public void setup() {

        hostClient = Mockito.mock(HostClient.class);
    }

    @Test
    public void whenParaIsNullThenExpectResponse() {

        HostApi hostApi = hostApi();

        Mockito.when(hostClient.createHost(Mockito.any())).thenReturn(hostApi);

        HostFeignServiceImpl hostFeignService = new HostFeignServiceImpl(hostClient);

        Assertions.assertEquals(hostApi, hostFeignService.createHost( null));
    }

    @Test
    public void whenParamIsValidThenExpectResponse() {

        HostApi hostApi = hostApi();

        Mockito.when(hostClient.createHost(Mockito.any())).thenReturn(hostApi);

        HostFeignServiceImpl hostFeignService = new HostFeignServiceImpl(hostClient);

        Assertions.assertEquals(hostApi, hostFeignService.createHost(HostCreateApi.builder().build()));
    }

    private HostApi hostApi() {

        HostApi hostApi = new HostApi();
        hostApi.setId(1L);
        hostApi.setInventoryId(1L);
        hostApi.setName("name");
        hostApi.setDescription("description");

        return hostApi;
    }
}
