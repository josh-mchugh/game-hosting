package com.example.demo.awx.inventory.service;

import com.example.demo.awx.inventory.model.AwxInventory;
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
public class AwxInventoryServiceGetByNameTest {

    @Autowired
    private IAwxInventoryService awxInventoryService;

    @Autowired
    private SampleBuilder sampleBuilder;

    @Test
    public void whenFindByNameIsValidReturnNotNull() {

        sampleBuilder.builder()
                .awxOrganization()
                .awxInventory();

        AwxInventory awxInventory = awxInventoryService.findByName("Default");

        Assertions.assertNotNull(awxInventory);
    }

    @Test
    public void whenFindByNameIsNotValidReturnNull() {

        AwxInventory awxInventory = awxInventoryService.findByName("Default");

        Assertions.assertNull(awxInventory);
    }

    @Test
    public void whenFindByNameHasNullParamThenThrowException() {

        Assertions.assertThrows(IllegalArgumentException.class, () -> awxInventoryService.findByName(null));
    }
}
