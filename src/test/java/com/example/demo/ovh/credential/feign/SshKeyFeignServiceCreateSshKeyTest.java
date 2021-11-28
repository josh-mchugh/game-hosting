package com.example.demo.ovh.credential.feign;

import com.example.demo.framework.properties.OvhConfig;
import com.example.demo.ovh.credential.feign.model.SshKeyApi;
import com.example.demo.ovh.credential.feign.model.SshKeyCreateApi;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class SshKeyFeignServiceCreateSshKeyTest {

    private OvhConfig ovhConfig;
    private SshKeyClient sshKeyClient;

    @BeforeEach
    public void setup() {

        ovhConfig = new OvhConfig();
        ovhConfig.setProjectId("projectId");

        sshKeyClient = Mockito.mock(SshKeyClient.class);
    }

    @Test
    public void whenServiceCreateSshKeyThenExpectSshKeyApi() {

        SshKeyApi expected = new SshKeyApi();

        Mockito.when(sshKeyClient.createSshKey(Mockito.anyString(), Mockito.any())).thenReturn(expected);

        SshKeyFeignServiceImpl service = new SshKeyFeignServiceImpl(ovhConfig, sshKeyClient);

        Assertions.assertEquals(expected, service.createSshKey(SshKeyCreateApi.builder().build()));
    }
}
