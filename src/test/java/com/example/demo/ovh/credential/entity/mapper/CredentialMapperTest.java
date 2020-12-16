package com.example.demo.ovh.credential.entity.mapper;

import com.example.demo.ovh.credential.entity.CredentialEntity;
import com.example.demo.ovh.credential.entity.CredentialType;
import com.example.demo.ovh.credential.entity.model.Credential;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class CredentialMapperTest {

    @Test
    public void whenEntityIsNullThenReturnNull() {

        Assertions.assertNull(CredentialMapper.map(null));
    }

    @Test
    public void whenEntityIsValidThenReturnNotNull() {

        Assertions.assertNotNull(CredentialMapper.map(new CredentialEntity()));
    }

    @Test
    public void whenEntityHasIdThenReturnId() {

        CredentialEntity entity = new CredentialEntity();
        entity.setId("id");

        Credential credential = CredentialMapper.map(entity);

        Assertions.assertEquals("id", credential.getId());
    }

    @Test
    public void whenEntityHasNullIdThenReturnNull() {

        Credential credential = CredentialMapper.map(new CredentialEntity());

        Assertions.assertNull(credential.getId());
    }

    @Test
    public void whenEntityHasOvhIdThenReturnOvhId() {

        CredentialEntity entity = new CredentialEntity();
        entity.setOvhId("ovhId");

        Credential credential = CredentialMapper.map(entity);

        Assertions.assertEquals("ovhId", credential.getSshKeyId());
    }

    @Test
    public void whenEntityHasNullSshKeyIdThenReturnNull() {

        Credential credential = CredentialMapper.map(new CredentialEntity());

        Assertions.assertNull(credential.getSshKeyId());
    }

    @Test
    public void whenEntityHasNameThenReturnName() {

        CredentialEntity entity = new CredentialEntity();
        entity.setName("name");

        Credential credential = CredentialMapper.map(entity);

        Assertions.assertEquals("name", credential.getName());
    }

    @Test
    public void whenEntityHasNullNameThenReturnNull() {

        Credential credential = CredentialMapper.map(new CredentialEntity());

        Assertions.assertNull(credential.getName());
    }

    @Test
    public void whenEntityHasPublicKeyThenReturnPublicKey() {

        CredentialEntity entity = new CredentialEntity();
        entity.setPublicKey("public key");

        Credential credential = CredentialMapper.map(entity);

        Assertions.assertEquals("public key", credential.getPublicKey());
    }

    @Test
    public void whenEntityHasNullPublicKeyThenReturnNull() {

        Credential credential = CredentialMapper.map(new CredentialEntity());

        Assertions.assertNull(credential.getPublicKey());
    }

    @Test
    public void whenEntityHasTypeThenReturnType() {

        CredentialEntity entity = new CredentialEntity();
        entity.setType(CredentialType.ANSIBLE);

        Credential credential = CredentialMapper.map(entity);

        Assertions.assertEquals(CredentialType.ANSIBLE, credential.getType());
    }

    @Test
    public void whenEntityHasNullTypeThenReturnType() {

        Credential credential = CredentialMapper.map(new CredentialEntity());

        Assertions.assertNull(credential.getType());
    }
}
