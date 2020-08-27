package com.example.demo.awx.credential.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import javax.transaction.Transactional;

@SpringBootTest
@Transactional
@ActiveProfiles("test")
public class AwxCredentialsServiceExistAnyTest {

    @Autowired
    private IAwxCredentialService awxCredentialService;

    @Test
    public void whenEntityDoNotExistThenExistsAnyReturnFalse() {

        boolean exists = awxCredentialService.existsAny();

        Assertions.assertFalse(exists);
    }

    @Test
    public void whenCreateRequestIsNullThenThrowException() {

        Assertions.assertThrows(NullPointerException.class, () ->awxCredentialService.handleAwxCredentialCreate(null));
    }
}
