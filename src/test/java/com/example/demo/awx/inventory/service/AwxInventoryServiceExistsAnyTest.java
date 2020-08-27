package com.example.demo.awx.inventory.service;

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
public class AwxInventoryServiceExistsAnyTest {

    @Autowired
    private IAwxInventoryService awxInventoryService;

    @Autowired
    private SampleBuilder sampleBuilder;

    @Test
    public void whenEntitiesExistThenExistsAnyReturnTrue() {

        sampleBuilder.builder()
                .awxOrganization()
                .awxInventory()
                .build();

        Assertions.assertTrue(awxInventoryService.existsAny());
    }

    @Test
    public void whenEntitiesDoesNotExistThenExistsAnyReturnFalse() {

        Assertions.assertFalse(awxInventoryService.existsAny());
    }
}
