package com.example.demo.awx.inventory.feign;

import com.example.demo.awx.feign.ListResponse;
import com.example.demo.awx.inventory.feign.model.InventoryApi;
import com.example.demo.framework.properties.AwxConfig;
import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Arrays;
import java.util.List;

public class InventoryFeignServiceGetInventoriesTest {

    private AwxConfig awxConfig;
    private IInventoryClient inventoryClient;

    @BeforeEach
    public void setup() {

        AwxConfig.Organization organization = new AwxConfig.Organization();
        organization.setId(1L);

        awxConfig = new AwxConfig();
        awxConfig.setOrganization(organization);

        inventoryClient = Mockito.mock(IInventoryClient.class);
    }

    @Test
    public void whenGetInventoriesIsValidThenExpectListResponse() {

        List<InventoryApi> inventoryApis = Arrays.asList(new InventoryApi(), new InventoryApi());
        
        ListResponse<InventoryApi> expectedResponse = new ListResponse<>();
        expectedResponse.setResults(inventoryApis);
        
        Mockito.when(inventoryClient.getInventories(Mockito.anyLong())).thenReturn(expectedResponse);
        
        InventoryFeignService inventoryFeignService = new InventoryFeignService(awxConfig, inventoryClient);

        ListResponse<InventoryApi> response = inventoryFeignService.getInventories();
        
        Assertions.assertEquals(expectedResponse, response);
    }
}
