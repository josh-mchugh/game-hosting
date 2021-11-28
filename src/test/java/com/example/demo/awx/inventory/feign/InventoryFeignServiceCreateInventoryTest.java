package com.example.demo.awx.inventory.feign;

import com.example.demo.awx.inventory.feign.model.InventoryApi;
import com.example.demo.awx.inventory.feign.model.InventoryCreateApi;
import com.example.demo.framework.properties.AwxConfig;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class InventoryFeignServiceCreateInventoryTest {

    private AwxConfig awxConfig;
    private InventoryClient inventoryClient;

    @BeforeEach
    public void setup() {

        AwxConfig.Organization organization = new AwxConfig.Organization();
        organization.setId(1L);

        awxConfig = new AwxConfig();
        awxConfig.setOrganization(organization);

        inventoryClient = Mockito.mock(InventoryClient.class);
    }

    @Test
    public void whenGetInventoriesIsValidThenExpectListResponse() {

       InventoryApi expectedResponse = new InventoryApi();

        Mockito.when(inventoryClient.createInventory(Mockito.any())).thenReturn(expectedResponse);

        InventoryFeignServiceImpl inventoryFeignService = new InventoryFeignServiceImpl(awxConfig, inventoryClient);

       InventoryApi response = inventoryFeignService.createInventory(InventoryCreateApi.builder().build());

        Assertions.assertEquals(expectedResponse, response);
    }
}
