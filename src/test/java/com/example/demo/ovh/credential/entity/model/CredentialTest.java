package com.example.demo.ovh.credential.entity.model;

import com.example.demo.ovh.credential.entity.CredentialType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class CredentialTest {

    @Test
    public void whenCredentialThenReturnId() {

        Credential credential = Credential.builder()
                .id("id")
                .build();

        Assertions.assertEquals("id", credential.getId());
    }

    @Test
    public void whenCredentialHasSshKeyIdThenReturnSshKeyId() {

        Credential credential = Credential.builder()
                .sshKeyId("ssh-key-id")
                .build();

        Assertions.assertEquals("ssh-key-id", credential.getSshKeyId());
    }

    @Test
    public void whenCredentialHasNameThenReturnName() {

        Credential credential = Credential.builder()
                .name("name")
                .build();

        Assertions.assertEquals("name", credential.getName());
    }

    @Test
    public void whenCredentialHasPublicKeyThenReturnPublicKey() {

        Credential credential = Credential.builder()
                .publicKey("public-key")
                .build();

        Assertions.assertEquals("public-key", credential.getPublicKey());
    }

    @Test
    public void whenCredentialHasCredentialTypeThenReturnCredentialType() {

        Credential credential = Credential.builder()
                .type(CredentialType.ANSIBLE)
                .build();

        Assertions.assertEquals(CredentialType.ANSIBLE, credential.getType());
    }

    @Test
    public void whenCredentialToString() {

        Credential credential = credential();

        String toString = "Credential(id=id, sshKeyId=ssh-key-id, name=name, publicKey=public-key, type=ANSIBLE)";

        Assertions.assertEquals(toString, credential.toString());
    }

    @Test
    public void whenCredentialHashCode() {

        Credential credential = Credential.builder()
                .id("id")
                .sshKeyId("ssh-key-id")
                .name("name")
                .publicKey("public-key")
                .build();

        Assertions.assertEquals(1699653271, credential.hashCode());
    }

    @Test
    public void whenCredentialEquals() {

        Credential credential1 = credential();
        Credential credential2 = credential();

        Assertions.assertEquals(credential1, credential2);
    }

    @Test
    public void whenCredentialNotEquals() {

        Credential credential = credential();

        Assertions.assertNotEquals(credential, Credential.builder().build());
    }

    private Credential credential() {

        return Credential.builder()
                .id("id")
                .sshKeyId("ssh-key-id")
                .name("name")
                .publicKey("public-key")
                .type(CredentialType.ANSIBLE)
                .build();
    }
}
