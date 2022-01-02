package com.example.demo.awx.credential.service.model;

import com.example.demo.awx.credential.entity.AwxCredentialType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.UUID;

public class AwxCredentialCreateRequestTest {

    @Test
    public void whenRequestHasOrganizationIdThenReturnOrganizationId() {

        UUID awxOrganizationId = UUID.randomUUID();

        AwxCredentialCreateRequest request = AwxCredentialCreateRequest.builder()
                .awxOrganizationId(awxOrganizationId)
                .build();

        Assertions.assertEquals(awxOrganizationId, request.getAwxOrganizationId());
    }

    @Test
    public void whenRequestHasCredentialIdThenReturnCredentialId() {

        AwxCredentialCreateRequest request = AwxCredentialCreateRequest.builder()
                .awxId(1L)
                .build();

        Assertions.assertEquals(1L, request.getAwxId());
    }

    @Test
    public void whenRequestHasNameThenReturnName() {

        AwxCredentialCreateRequest request = AwxCredentialCreateRequest.builder()
                .name("name")
                .build();

        Assertions.assertEquals("name", request.getName());
    }

    @Test
    public void whenRequestHasDescriptionThenReturnDescription() {

        AwxCredentialCreateRequest request = AwxCredentialCreateRequest.builder()
                .description("description")
                .build();

        Assertions.assertEquals("description", request.getDescription());
    }

    @Test
    public void whenRequestHasPrivateKeyThenReturnPrivateKey() {

        AwxCredentialCreateRequest request = AwxCredentialCreateRequest.builder()
                .privateKey("privateKey")
                .build();

        Assertions.assertEquals("privateKey", request.getPrivateKey());
    }

    @Test
    public void whenRequestHasPassPhraseThenReturnPassPhrase() {

        AwxCredentialCreateRequest request = AwxCredentialCreateRequest.builder()
                .passphrase("passPhrase")
                .build();

        Assertions.assertEquals("passPhrase", request.getPassphrase());
    }

    @Test
    public void whenRequestHasTypeThenReturnType() {

        AwxCredentialCreateRequest request = AwxCredentialCreateRequest.builder()
                .type(AwxCredentialType.MACHINE)
                .build();

        Assertions.assertEquals(AwxCredentialType.MACHINE, request.getType());
    }

    @Test
    public void whenRequestToString() {

        AwxCredentialCreateRequest request = request();

        String expected = "AwxCredentialCreateRequest(awxOrganizationId=4d5d2786-4755-447f-9816-2f5fdb7d557a, awxId=1, name=name, description=description, privateKey=privateKey, passphrase=passPhrase, type=MACHINE)";

        Assertions.assertEquals(expected, request.toString());
    }

    @Test
    public void whenRequestHashCode() {

        AwxCredentialCreateRequest request = AwxCredentialCreateRequest.builder()
                .awxOrganizationId(UUID.fromString("4d5d2786-4755-447f-9816-2f5fdb7d557a"))
                .awxId(1L)
                .name("name")
                .description("description")
                .privateKey("privateKey")
                .passphrase("passPhrase")
                .build();

        Assertions.assertEquals(551935540, request.hashCode());
    }

    @Test
    public void whenRequestEquals() {

        AwxCredentialCreateRequest request1 = request();
        AwxCredentialCreateRequest request2 = request();

        Assertions.assertEquals(request1, request2);
    }

    @Test
    public void whenRequestNotEquals() {

        AwxCredentialCreateRequest request = request();

        Assertions.assertNotEquals(request, AwxCredentialCreateRequest.builder().build());
    }

    private AwxCredentialCreateRequest request() {

        return AwxCredentialCreateRequest.builder()
                .awxOrganizationId(UUID.fromString("4d5d2786-4755-447f-9816-2f5fdb7d557a"))
                .awxId(1L)
                .name("name")
                .description("description")
                .privateKey("privateKey")
                .passphrase("passPhrase")
                .type(AwxCredentialType.MACHINE)
                .build();
    }
}
