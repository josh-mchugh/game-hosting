package com.example.demo.framework.seed.awx.inventory.projection;

import com.example.demo.framework.seed.awx.inventory.projection.model.ExistsAnyAwxInventoryQuery;
import com.example.demo.framework.seed.awx.inventory.projection.model.ExistsAnyAwxInventoryResponse;
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
public class AwxInventorySeedProjectionServiceExistsAnyTest {

    @Autowired
    private AwxInventorySeedProjectionService service;

    @Autowired
    private SampleBuilder sampleBuilder;

    @Test
    public void whenParamIsNullThenExpectNoException() {

        Assertions.assertDoesNotThrow(() -> service.existsAny(null));
    }

    @Test
    public void whenAwxInventoryExistsThenReturnTrue() {

        sampleBuilder.builder().awxInventory().build();

        ExistsAnyAwxInventoryQuery query = new ExistsAnyAwxInventoryQuery();
        ExistsAnyAwxInventoryResponse response = service.existsAny(query);

        Assertions.assertTrue(response.exists());
    }

    @Test
    public void whenAwxInventoryDoesNotExistsThenReturnFalse() {

        ExistsAnyAwxInventoryQuery query = new ExistsAnyAwxInventoryQuery();
        ExistsAnyAwxInventoryResponse response = service.existsAny(query);

        Assertions.assertFalse(response.exists());
    }
}
