package com.example.demo.ovh.credential.feign;

import com.example.demo.framework.properties.OvhConfig;
import com.example.demo.ovh.credential.feign.model.SshKeyApi;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Arrays;
import java.util.List;

public class SshKeyFeignServiceGetSshKeysTest {

    private OvhConfig ovhConfig;
    private ISshKeyClient sshKeyClient;

    @BeforeEach
    public void setup() {

        ovhConfig = new OvhConfig();
        ovhConfig.setProjectId("projectId");

        sshKeyClient = Mockito.mock(ISshKeyClient.class);
    }

    @Test
    public void whenServiceGetSshKeysThenReturnSshKeys() {

        List<SshKeyApi> expected = Arrays.asList(new SshKeyApi(), new SshKeyApi());

        Mockito.when(sshKeyClient.getSshKeys(Mockito.anyString())).thenReturn(expected);

        SshKeyFeignService service = new SshKeyFeignService(ovhConfig, sshKeyClient);

        Assertions.assertEquals(expected, service.getSshKeys());
    }
}
