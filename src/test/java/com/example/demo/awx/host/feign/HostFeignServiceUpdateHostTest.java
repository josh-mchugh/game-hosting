package com.example.demo.awx.host.feign;

import com.example.demo.awx.host.feign.model.HostApi;
import com.example.demo.awx.host.feign.model.HostPatchApi;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class HostFeignServiceUpdateHostTest {

    private IHostClient hostClient;

    @BeforeEach
    public void setup() {

        hostClient = Mockito.mock(IHostClient.class);
    }

    @Test
    public void whenParamIdIsNullThenExpectResponse() {

        HostApi hostApi = hostApi();

        Mockito.when(hostClient.updateHost(Mockito.any(), Mockito.any())).thenReturn(hostApi);

        HostFeignService hostFeignService = new HostFeignService(hostClient);

        HostPatchApi body =  HostPatchApi.builder().build();

        Assertions.assertEquals(hostApi, hostFeignService.updateHost(null, body));
    }

    @Test
    public void whenParamBodyIsNullThenExpectResponse() {

        HostApi hostApi = hostApi();

        Mockito.when(hostClient.updateHost(Mockito.any(), Mockito.any())).thenReturn(hostApi);

        HostFeignService hostFeignService = new HostFeignService(hostClient);

        Assertions.assertEquals(hostApi, hostFeignService.updateHost(1L, null));
    }

    @Test
    public void whenParamsIsValidThenExpectResponse() {

        HostApi hostApi = hostApi();

        Mockito.when(hostClient.updateHost(Mockito.any(), Mockito.any())).thenReturn(hostApi);

        HostFeignService hostFeignService = new HostFeignService(hostClient);

        HostPatchApi body =  HostPatchApi.builder().build();

        Assertions.assertEquals(hostApi, hostFeignService.updateHost(1L, body));
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
