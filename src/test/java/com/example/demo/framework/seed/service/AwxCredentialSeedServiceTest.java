package com.example.demo.framework.seed.service;

import com.example.demo.awx.credential.entity.AwxCredentialType;
import com.example.demo.awx.feign.ListResponse;
import com.example.demo.awx.credential.feign.CredentialClient;
import com.example.demo.awx.credential.feign.model.CredentialApi;
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

@SpringBootTest(properties = {
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
    private CredentialClient credentialClient;

    @Test
    public void whenAwxCredentialsExistsThenDataNotExistsReturnFalse() {

        sampleBuilder.builder()
                .awxOrganization()
                .awxCredential()
                .build();

        Assertions.assertFalse(awxCredentialSeedService.dataNotExists());
    }

    @Test
    public void whenAwxCredentialDoesNotExistThenDataNotExistsReturnTrue() {

        Assertions.assertTrue(awxCredentialSeedService.dataNotExists());
    }

    @Test
    public void whenApiDoesNotContainCredentialThenCreateNewAwxCredentialReturnList() {

        AwxOrganization awxOrganization = sampleBuilder.builder()
                .awxOrganization()
                .build()
                .getAwxOrganization();

        ListResponse<CredentialApi> credentialApiList = new ListResponse<>();
        credentialApiList.setResults(Collections.emptyList());

        Mockito.when(credentialClient.getCredentials(Mockito.anyLong())).thenReturn(credentialApiList);

        CredentialApi firstCredentialApi = new CredentialApi();
        firstCredentialApi.setOrganizationId(awxOrganization.getOrganizationId());
        firstCredentialApi.setId(1L);
        firstCredentialApi.setName("First Credential");
        firstCredentialApi.setCredentialType(AwxCredentialType.MACHINE);

        CredentialApi secondCredentialApi = new CredentialApi();
        secondCredentialApi.setOrganizationId(awxOrganization.getOrganizationId());
        secondCredentialApi.setId(1L);
        secondCredentialApi.setName("Second Credential");
        secondCredentialApi.setCredentialType(AwxCredentialType.SOURCE_CONTROL);

        Mockito.when(credentialClient.createCredential(Mockito.anyLong(), Mockito.any()))
                .thenReturn(firstCredentialApi)
                .thenReturn(secondCredentialApi);

        ImmutableList<Object> awxCredentials = awxCredentialSeedService.initializeData();

        Assertions.assertEquals(2, awxCredentials.size());
    }

    @Test
    public void whenApiContainsCredentialThenCreateNewCredentialReturnList() {

        AwxOrganization awxOrganization = sampleBuilder.builder()
                .awxOrganization()
                .build()
                .getAwxOrganization();

        CredentialApi firstCredentialApi = new CredentialApi();
        firstCredentialApi.setOrganizationId(awxOrganization.getOrganizationId());
        firstCredentialApi.setId(1L);
        firstCredentialApi.setName("First Credential");
        firstCredentialApi.setCredentialType(AwxCredentialType.MACHINE);

        CredentialApi secondCredentialApi = new CredentialApi();
        secondCredentialApi.setOrganizationId(awxOrganization.getOrganizationId());
        secondCredentialApi.setId(1L);
        secondCredentialApi.setName("Second Credential");
        secondCredentialApi.setCredentialType(AwxCredentialType.SOURCE_CONTROL);

        ListResponse<CredentialApi> credentialApiList = new ListResponse<>();
        credentialApiList.setResults(Arrays.asList(firstCredentialApi, secondCredentialApi));

        Mockito.when(credentialClient.getCredentials(Mockito.anyLong())).thenReturn(credentialApiList);

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
