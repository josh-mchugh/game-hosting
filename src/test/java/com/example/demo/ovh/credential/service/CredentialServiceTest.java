package com.example.demo.ovh.credential.service;

import com.example.demo.ovh.credential.entity.CredentialType;
import com.example.demo.ovh.credential.model.Credential;
import com.example.demo.ovh.credential.service.model.CredentialCreateRequest;
import com.example.demo.sample.TestCredentialUtil;
import org.jasypt.encryption.StringEncryptor;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import javax.persistence.PersistenceException;
import javax.transaction.Transactional;

@SpringBootTest
@Transactional
@ActiveProfiles("test")
public class CredentialServiceTest {

    @Autowired
    private ICredentialService credentialService;

    @Autowired
    private StringEncryptor encryptor;

    @Test
    public void whenEntitiesDoNotExistThenExistsAllReturnsFalse() {

        boolean exists = credentialService.existsAny();

        Assertions.assertFalse(exists);
    }

    @Test
    public void whenEntitiesDoExistThenExistsAllReturnTrue() {

        CredentialCreateRequest request = TestCredentialUtil.createDefault();
        credentialService.handleSshKeyCreate(request);

        boolean exists = credentialService.existsAny();

        Assertions.assertTrue(exists);
    }

    @Test
    public void whenCreateRequestIsValidThenReturnCredential() {

        CredentialCreateRequest request = TestCredentialUtil.createDefault();
        Credential credential = credentialService.handleSshKeyCreate(request);

        Assertions.assertNotNull(credential);
    }

    @Test
    public void whenCreateRequestIsNullThenThrowException() {

        Assertions.assertThrows(NullPointerException.class, () -> credentialService.handleSshKeyCreate(null));
    }

    @Test
    public void whenCreateRequestIsValidThenReturnId() {

        CredentialCreateRequest request = TestCredentialUtil.createDefault();
        Credential credential = credentialService.handleSshKeyCreate(request);

        Assertions.assertNotNull(credential.getId());
    }

    @Test
    public void whenCreateRequestHasSshKeyIdThenReturnSshKeyId() {

        CredentialCreateRequest request = TestCredentialUtil.builder()
                .sshKeyId("ssh key id")
                .build();
        Credential credential = credentialService.handleSshKeyCreate(request);

        Assertions.assertEquals("ssh key id", credential.getSshKeyId());
    }

    @Test
    public void whenCreateRequestHasNullSshKeyThenThrowException() {

        CredentialCreateRequest request = TestCredentialUtil.builder()
                .sshKeyId(null)
                .build();

        Assertions.assertThrows(PersistenceException.class, () -> credentialService.handleSshKeyCreate(request));
    }

    @Test
    public void whenCreateRequestHasNameThenReturnName() {

        CredentialCreateRequest request = TestCredentialUtil.builder()
                .name("name")
                .build();
        Credential credential = credentialService.handleSshKeyCreate(request);

        Assertions.assertEquals("name", credential.getName());
    }

    @Test
    public void whenCreateRequestHasNullNameThenReturnNull() {

        CredentialCreateRequest request = TestCredentialUtil.builder()
                .name(null)
                .build();
        Credential credential = credentialService.handleSshKeyCreate(request);

        Assertions.assertNull(credential.getName());
    }

    @Test
    public void whenCreateRequestHasPublicKeyThenReturnEncryptedPublicKey() {

        CredentialCreateRequest request = TestCredentialUtil.builder()
                .publicKey("public key")
                .build();
        Credential credential = credentialService.handleSshKeyCreate(request);

        Assertions.assertEquals("public key", encryptor.decrypt(credential.getPublicKey()));
    }

    @Test
    public void whenCreateRequestHasNullPublicKeyThenReturnNull() {

        CredentialCreateRequest request = TestCredentialUtil.builder()
                .publicKey(null)
                .build();
        Credential credential = credentialService.handleSshKeyCreate(request);

        Assertions.assertNull(credential.getPublicKey());
    }

    @Test
    public void whenCreateRequestHasPrivateKeyThenReturnEncryptedPrivateKey() {

        CredentialCreateRequest request = TestCredentialUtil.builder()
                .privateKey("private key")
                .build();
        Credential credential = credentialService.handleSshKeyCreate(request);

        Assertions.assertEquals("private key", encryptor.decrypt(credential.getPrivateKey()));
    }

    @Test
    public void whenCreateRequestHasNullPrivateKeyThenReturnNull() {

        CredentialCreateRequest request = TestCredentialUtil.builder()
                .privateKey(null)
                .build();
        Credential credential = credentialService.handleSshKeyCreate(request);

        Assertions.assertNull(credential.getPrivateKey());
    }

    @Test
    public void whenCreateRequestHasTypeThenReturnType() {

        CredentialCreateRequest request = TestCredentialUtil.builder()
                .type(CredentialType.ANSIBLE)
                .build();
        Credential credential = credentialService.handleSshKeyCreate(request);

        Assertions.assertEquals(CredentialType.ANSIBLE, credential.getType());
    }

    @Test
    public void whenCreateRequestHasNullTypeThenReturnType() {

        CredentialCreateRequest request = TestCredentialUtil.builder()
                .type(null)
                .build();
        Credential credential = credentialService.handleSshKeyCreate(request);

        Assertions.assertNull(credential.getType());
    }
}
