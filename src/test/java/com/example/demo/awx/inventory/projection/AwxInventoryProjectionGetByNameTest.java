package com.example.demo.awx.inventory.projection;

import com.example.demo.awx.inventory.entity.model.AwxInventory;
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
public class AwxInventoryProjectionGetByNameTest {

    @Autowired
    private IAwxInventoryProjector awxInventoryProjector;

    @Autowired
    private SampleBuilder sampleBuilder;

    @Test
    public void whenFindByNameIsValidReturnNotNull() {

        sampleBuilder.builder()
                .awxOrganization()
                .awxInventory();

        AwxInventory awxInventory = awxInventoryProjector.findByName("Default");

        Assertions.assertNotNull(awxInventory);
    }

    @Test
    public void whenFindByNameIsNotValidReturnNull() {

        AwxInventory awxInventory = awxInventoryProjector.findByName("Default");

        Assertions.assertNull(awxInventory);
    }

    @Test
    public void whenFindByNameHasNullParamThenThrowException() {

        Assertions.assertThrows(IllegalArgumentException.class, () -> awxInventoryProjector.findByName(null));
    }
}
