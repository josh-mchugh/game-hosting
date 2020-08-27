package com.example.demo.awx.credential.mapper;

import com.example.demo.awx.credential.entity.AwxCredentialEntity;
import com.example.demo.awx.credential.entity.AwxCredentialType;
import com.example.demo.awx.credential.model.AwxCredential;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class AwxCredentialMapperTest {

    @Test
    public void whenEntityIsNullThenReturnNull() {

        Assertions.assertNull(AwxCredentialMapper.map(null));
    }

    @Test
    public void whenEntityIsValueThenReturnNotNull() {

        Assertions.assertNotNull(AwxCredentialMapper.map(new AwxCredentialEntity()));
    }

    @Test
    public void whenEntityHasIdThenReturnId() {

        AwxCredentialEntity entity = new AwxCredentialEntity();
        entity.setId("id");

        AwxCredential awxCredential = AwxCredentialMapper.map(entity);

        Assertions.assertEquals("id", awxCredential.getId());
    }

    @Test
    public void whenEntityHasNullIdThenReturnNull() {

        AwxCredential awxCredential = AwxCredentialMapper.map(new AwxCredentialEntity());

        Assertions.assertNull(awxCredential.getId());
    }

    @Test
    public void whenEntityHasCredentialIdThenReturnCredentialId() {

        AwxCredentialEntity entity = new AwxCredentialEntity();
        entity.setCredentialId(1L);

        AwxCredential awxCredential = AwxCredentialMapper.map(entity);

        Assertions.assertEquals(1L, awxCredential.getCredentialId());
    }

    @Test
    public void whenEntityHasNullCredentialIdThenReturnNull() {

        AwxCredential awxCredential = AwxCredentialMapper.map(new AwxCredentialEntity());

        Assertions.assertNull(awxCredential.getCredentialId());
    }

    @Test
    public void whenEntityHasNameThenReturnName() {

        AwxCredentialEntity entity = new AwxCredentialEntity();
        entity.setName("name");

        AwxCredential awxCredential = AwxCredentialMapper.map(entity);

        Assertions.assertEquals("name", awxCredential.getName());
    }

    @Test
    public void whenEntityHasNullNameThenReturnNull(){

        AwxCredential awxCredential = AwxCredentialMapper.map(new AwxCredentialEntity());

        Assertions.assertNull(awxCredential.getName());
    }

    @Test
    public void whenEntityHasdescriptionThenReturnDescription() {

        AwxCredentialEntity entity = new AwxCredentialEntity();
        entity.setDescription("description");

        AwxCredential awxCredential = AwxCredentialMapper.map(entity);

        Assertions.assertEquals("description", awxCredential.getDescription());
    }

    @Test
    public void whenEntityHasNullDescriptionThenReturnNull() {

        AwxCredential awxCredential = AwxCredentialMapper.map(new AwxCredentialEntity());

        Assertions.assertNull(awxCredential.getDescription());
    }

    @Test
    public void whenEntityHasPrivateKeyThenReturnPrivateKey() {

        AwxCredentialEntity entity = new AwxCredentialEntity();
        entity.setPrivateKey("private key");

        AwxCredential awxCredential = AwxCredentialMapper.map(entity);

        Assertions.assertEquals("private key", awxCredential.getPrivateKey());
    }

    @Test
    public void whenEntityHasNullPrivateKeyThenReturnNull() {

        AwxCredential awxCredential = AwxCredentialMapper.map(new AwxCredentialEntity());

        Assertions.assertNull(awxCredential.getPrivateKey());
    }

    @Test
    public void whenEntityHasPassphraseThenReturnPassphrase() {

        AwxCredentialEntity entity = new AwxCredentialEntity();
        entity.setPassphrase("passphrase");

        AwxCredential awxCredential = AwxCredentialMapper.map(entity);

        Assertions.assertEquals("passphrase", awxCredential.getPassphrase());
    }

    @Test
    public void whenEntityHasNullPassphraseThenReturnPassphrase() {

        AwxCredential awxCredential = AwxCredentialMapper.map(new AwxCredentialEntity());

        Assertions.assertNull(awxCredential.getPassphrase());
    }

    @Test
    public void whenEntityHasTypeThenReturnType() {

        AwxCredentialEntity entity = new AwxCredentialEntity();
        entity.setType(AwxCredentialType.MACHINE);

        AwxCredential awxCredential = AwxCredentialMapper.map(entity);

        Assertions.assertEquals(AwxCredentialType.MACHINE, awxCredential.getType());
    }

    @Test
    public void whenEntityHasNullTypeThenReturnNull() {

        AwxCredential awxCredential = AwxCredentialMapper.map(new AwxCredentialEntity());

        Assertions.assertNull(awxCredential.getType());
    }
}
