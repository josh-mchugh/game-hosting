package com.example.demo.framework.seed.service;

import com.example.demo.ovh.credential.model.Credential;
import com.example.demo.ovh.feign.ssh.SshKeyClient;
import com.example.demo.ovh.feign.ssh.model.SshKeyApi;
import com.example.demo.sample.SampleBuilder;
import com.google.common.collect.ImmutableList;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;

import javax.transaction.Transactional;
import java.util.Collections;

@SpringBootTest(properties = {
        "ovh.ssh-key-configs[0].name=name",
        "ovh.ssh-key-configs[0].type=ANSIBLE",
        "ovh.ssh-key-configs[0].public-key=public key",
        "ovh.ssh-key-configs[0].private-key=private key"
})
@Transactional
@ActiveProfiles("test")
public class CredentialSeedServiceTest {

    @Autowired
    private CredentialSeedService credentialSeedService;

    @Autowired
    private SampleBuilder sampleBuilder;

    @MockBean
    private SshKeyClient sshKeyClient;

    @Test
    public void whenSeedDataDoesNotExistThenReturnTrue() {

        boolean doesNotExists = credentialSeedService.dataNotExists();

        Assertions.assertTrue(doesNotExists);
    }

    @Test
    public void whenSeedDataDoesExistsThenReturnFalse() {

       sampleBuilder.builder()
                .credential()
                .build();

        boolean doesNotExists = credentialSeedService.dataNotExists();

        Assertions.assertFalse(doesNotExists);
    }

    @Test
    public void whenApiDoesNotContainSshKeyThenCreateNewCredentialReturnList() {

        Mockito.when(sshKeyClient.getSshKeys(Mockito.anyString())).thenReturn(Collections.emptyList());

        SshKeyApi sshKeyApi = new SshKeyApi();
        sshKeyApi.setId("ssh key id");
        sshKeyApi.setName("name");
        sshKeyApi.setPublicKey("public key");

        Mockito.when(sshKeyClient.createSshKey(Mockito.any(), Mockito.any())).thenReturn(sshKeyApi);

        ImmutableList<Credential> credentials = credentialSeedService.initializeData();

        Assertions.assertEquals(1, credentials.size());
    }

    @Test
    public void whenApiContainsSshKeyThenCreateNewCredentialReturnList() {

        SshKeyApi sshKeyApi = new SshKeyApi();
        sshKeyApi.setId("ssh key id");
        sshKeyApi.setName("name");
        sshKeyApi.setPublicKey("public key");

        Mockito.when(sshKeyClient.getSshKeys(Mockito.any())).thenReturn(Collections.singletonList(sshKeyApi));

        ImmutableList<Credential> credentials = credentialSeedService.initializeData();

        Assertions.assertEquals(1, credentials.size());
    }

    @Test
    public void whenTypeHasValueThenReturnValue() {

        Assertions.assertEquals("Credential", credentialSeedService.type());
    }

    @Test
    public void typeShouldNotBeNull() {

        Assertions.assertNotNull(credentialSeedService.type());
    }

    @Test
    public void whenOrderHasValueReturnValue() {

        Assertions.assertEquals(6, credentialSeedService.order());
    }

    @Test
    public void orderShouldNotBeNull() {

        Assertions.assertNotNull(credentialSeedService.order());
    }
}
