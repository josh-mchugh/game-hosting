package com.example.demo.framework.seed.ovh.credential;

import com.example.demo.framework.seed.ovh.credential.projection.model.ExistsAnyCredentialQuery;
import com.example.demo.framework.seed.ovh.credential.projection.model.ExistsAnyCredentialResponse;
import com.example.demo.ovh.credential.feign.ISshKeyFeignService;
import com.example.demo.ovh.credential.feign.model.SshKeyApi;
import com.example.demo.sample.SampleBuilder;
import com.google.common.collect.ImmutableList;
import org.axonframework.queryhandling.QueryGateway;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;

import javax.transaction.Transactional;
import java.util.Collections;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

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
    private ISshKeyFeignService sshKeyFeignService;

    @MockBean
    private QueryGateway queryGateway;

    @Test
    public void whenSeedDataDoesNotExistThenReturnTrue() throws ExecutionException, InterruptedException {

        Mockito.when(queryGateway.query(new ExistsAnyCredentialQuery(), ExistsAnyCredentialResponse.class))
                .thenReturn(CompletableFuture.completedFuture(new ExistsAnyCredentialResponse(false)));

        boolean doesNotExists = credentialSeedService.dataNotExists();

        Assertions.assertTrue(doesNotExists);
    }

    @Test
    public void whenSeedDataDoesExistsThenReturnFalse() throws ExecutionException, InterruptedException {

        Mockito.when(queryGateway.query(new ExistsAnyCredentialQuery(), ExistsAnyCredentialResponse.class))
                .thenReturn(CompletableFuture.completedFuture(new ExistsAnyCredentialResponse(true)));

        boolean doesNotExists = credentialSeedService.dataNotExists();

        Assertions.assertFalse(doesNotExists);
    }

    @Test
    public void whenApiDoesNotContainSshKeyThenCreateNewCredentialReturnList() {

        Mockito.when(sshKeyFeignService.getSshKeys()).thenReturn(Collections.emptyList());

        SshKeyApi sshKeyApi = new SshKeyApi();
        sshKeyApi.setId("ssh key id");
        sshKeyApi.setName("name");
        sshKeyApi.setPublicKey("public key");

        Mockito.when(sshKeyFeignService.createSshKey(Mockito.any())).thenReturn(sshKeyApi);

        ImmutableList<Object> credentials = credentialSeedService.initializeData();

        Assertions.assertEquals(1, credentials.size());
    }

    @Test
    public void whenApiContainsSshKeyThenCreateNewCredentialReturnList() {

        SshKeyApi sshKeyApi = new SshKeyApi();
        sshKeyApi.setId("ssh key id");
        sshKeyApi.setName("name");
        sshKeyApi.setPublicKey("public key");

        Mockito.when(sshKeyFeignService.getSshKeys()).thenReturn(Collections.singletonList(sshKeyApi));

        ImmutableList<Object> credentials = credentialSeedService.initializeData();

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
