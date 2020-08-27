package com.example.demo.awx.host.service;

import com.example.demo.sample.SampleBuilder;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import javax.transaction.Transactional;

@SpringBootTest
@Transactional
@ActiveProfiles("test")
public class AwxHostServiceExistsAnyTest {

    @Autowired
    private IAwxHostService awxHostService;

    @Autowired
    private SampleBuilder sampleBuilder;

    @Test
    public void whenEntityExistsThenExistsAnyReturnTrue() {

        sampleBuilder.builder()
                .user()
                .region()
                .flavor()
                .image()
                .project()
                .credential()
                .instanceGroup()
                .instance()
                .awxOrganization()
                .awxInventory()
                .awxHost()
                .build();

        Assertions.assertTrue(awxHostService.existsAny());
    }

    @Test
    public void whenEntityDoesNotExistsThenExistsAnyReturnFalse() {

        Assertions.assertFalse(awxHostService.existsAny());
    }
}
