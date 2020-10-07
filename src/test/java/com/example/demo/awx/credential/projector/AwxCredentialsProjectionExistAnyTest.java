package com.example.demo.awx.credential.projector;

import com.example.demo.awx.credential.entity.service.IAwxCredentialService;
import com.example.demo.awx.credential.projection.IAwxCredentialProjector;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import javax.transaction.Transactional;

@SpringBootTest
@Transactional
@ActiveProfiles("test")
public class AwxCredentialsProjectionExistAnyTest {

    @Autowired
    private IAwxCredentialProjector awxCredentialProjector;

    @Autowired
    private IAwxCredentialService awxCredentialService;

    @Test
    public void whenEntityDoNotExistThenExistsAnyReturnFalse() {

        boolean exists = awxCredentialProjector.existsAny();

        Assertions.assertFalse(exists);
    }

    @Test
    public void whenCreateRequestIsNullThenThrowException() {

        Assertions.assertThrows(NullPointerException.class, () -> awxCredentialService.handleCreated(null));
    }
}
