package com.example.demo.framework.seed.awx.credential;

import com.example.demo.awx.credential.entity.AwxCredentialType;
import com.example.demo.awx.credential.feign.IAwxCredentialFeignService;
import com.example.demo.awx.credential.feign.model.AwxCredentialApi;
import com.example.demo.awx.feign.ListResponse;
import com.example.demo.awx.organization.entity.model.AwxOrganization;
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
import java.util.Arrays;
import java.util.Collections;
import java.util.concurrent.ExecutionException;

@SpringBootTest(properties = {
        "awx.organization.id=1",
        "awx.credentials[0].name=First Credential",
        "awx.credentials[0].type=MACHINE",
        "awx.credentials[0].private-key=privatekey",
        "awx.credentials[0].passphrase=passphrase",
        "awx.credentials[1].name=Second Credential",
        "awx.credentials[1].type=SOURCE_CONTROL",
        "awx.credentials[1].private-key=private key",
        "awx.credentials[1].passphrase=passphrase"
})
@Transactional
@ActiveProfiles("test")
public class AwxCredentialSeedServiceTest {

    @Autowired
    private AwxCredentialSeedService awxCredentialSeedService;

    @Autowired
    private SampleBuilder sampleBuilder;

    @MockBean
    private IAwxCredentialFeignService credentialClient;

    @Test
    public void whenAwxCredentialsExistsThenDataNotExistsReturnFalse() throws ExecutionException, InterruptedException {

        sampleBuilder.builder()
                .awxOrganization()
                .awxCredential()
                .build();

        Assertions.assertFalse(awxCredentialSeedService.dataNotExists());
    }

    @Test
    public void whenAwxCredentialDoesNotExistThenDataNotExistsReturnTrue() throws ExecutionException, InterruptedException {

        Assertions.assertTrue(awxCredentialSeedService.dataNotExists());
    }

    @Test
    public void whenApiDoesNotContainCredentialThenCreateNewAwxCredentialReturnList() throws ExecutionException, InterruptedException {

        AwxOrganization awxOrganization = sampleBuilder.builder()
                .awxOrganization()
                .build()
                .getAwxOrganization();

        ListResponse<AwxCredentialApi> credentialApiList = new ListResponse<>();
        credentialApiList.setResults(Collections.emptyList());

        Mockito.when(credentialClient.getCredentials()).thenReturn(credentialApiList);

        AwxCredentialApi firstCredentialApi = new AwxCredentialApi();
        firstCredentialApi.setOrganizationId(awxOrganization.getAwxId());
        firstCredentialApi.setId(1L);
        firstCredentialApi.setName("First Credential");
        firstCredentialApi.setCredentialType(AwxCredentialType.MACHINE);

        AwxCredentialApi secondCredentialApi = new AwxCredentialApi();
        secondCredentialApi.setOrganizationId(awxOrganization.getAwxId());
        secondCredentialApi.setId(2L);
        secondCredentialApi.setName("Second Credential");
        secondCredentialApi.setCredentialType(AwxCredentialType.SOURCE_CONTROL);

        Mockito.when(credentialClient.createCredential(Mockito.any()))
                .thenReturn(firstCredentialApi)
                .thenReturn(secondCredentialApi);

        ImmutableList<Object> awxCredentials = awxCredentialSeedService.initializeData();

        Assertions.assertEquals(2, awxCredentials.size());
    }

    @Test
    public void whenApiContainsCredentialThenCreateNewCredentialReturnList() throws ExecutionException, InterruptedException {

        AwxOrganization awxOrganization = sampleBuilder.builder()
                .awxOrganization()
                .build()
                .getAwxOrganization();

        AwxCredentialApi firstCredentialApi = new AwxCredentialApi();
        firstCredentialApi.setOrganizationId(awxOrganization.getAwxId());
        firstCredentialApi.setId(1L);
        firstCredentialApi.setName("First Credential");
        firstCredentialApi.setCredentialType(AwxCredentialType.MACHINE);

        AwxCredentialApi secondCredentialApi = new AwxCredentialApi();
        secondCredentialApi.setOrganizationId(awxOrganization.getAwxId());
        secondCredentialApi.setId(1L);
        secondCredentialApi.setName("Second Credential");
        secondCredentialApi.setCredentialType(AwxCredentialType.SOURCE_CONTROL);

        ListResponse<AwxCredentialApi> credentialApiList = new ListResponse<>();
        credentialApiList.setResults(Arrays.asList(firstCredentialApi, secondCredentialApi));

        Mockito.when(credentialClient.getCredentials()).thenReturn(credentialApiList);

        ImmutableList<Object> awxCredentials = awxCredentialSeedService.initializeData();

        Assertions.assertEquals(2, awxCredentials.size());
    }

    @Test
    public void whenTypeHasValueThenReturnValue() {

        Assertions.assertEquals("Awx Credential", awxCredentialSeedService.type());
    }

    @Test
    public void typeShouldNotBeNull() {

        Assertions.assertNotNull(awxCredentialSeedService.type());
    }

    @Test
    public void whenOrderHasValueReturnValue() {

        Assertions.assertEquals(8, awxCredentialSeedService.order());
    }

    @Test
    public void orderShouldNotBeNull() {

        Assertions.assertNotNull(awxCredentialSeedService.order());
    }
}
