package com.example.demo.framework.seed.service;

import com.example.demo.awx.feign.ListResponse;
import com.example.demo.awx.inventory.feign.InventoryClient;
import com.example.demo.awx.inventory.feign.model.InventoryApi;
import com.example.demo.sample.SampleBuilder;
import com.google.common.collect.ImmutableList;
import feign.FeignException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;

import javax.transaction.Transactional;
import java.util.Collections;

@SpringBootTest
@Transactional
@ActiveProfiles("test")
public class AwxInventorySeedServiceTest {

    @Autowired
    private AwxInventorySeedService awxInventorySeedService;

    @Autowired
    private SampleBuilder sampleBuilder;

    @MockBean
    private InventoryClient inventoryClient;

    @Test
    public void whenEntitiesExistThenDataDoesNotExistsReturnsFalse() {

        sampleBuilder.builder()
                .awxOrganization()
                .awxInventory()
                .build();

        Assertions.assertFalse(awxInventorySeedService.dataNotExists());
    }

    @Test
    public void whenEntitiesDoNotExistThenDataDoesNotExistsReturnTrue() {

        Assertions.assertTrue(awxInventorySeedService.dataNotExists());
    }

    @Test
    public void whenInventoryApiReturnsEmptyListThenCreateNewAwxInventory() {

        sampleBuilder.builder()
                .awxOrganization()
                .build();

        Mockito.when(inventoryClient.getInventories(Mockito.anyLong())).thenReturn(new ListResponse<>());

        InventoryApi inventoryApi = new InventoryApi();
        inventoryApi.setId(1L);
        inventoryApi.setOrganizationId(1L);
        inventoryApi.setName("Default");
        inventoryApi.setDescription("Default Inventory");

        Mockito.when(inventoryClient.createInventory(Mockito.any())).thenReturn(inventoryApi);

        ImmutableList<Object> awxInventories = awxInventorySeedService.initializeData();

        Assertions.assertEquals(1L, awxInventories.size());
    }

    @Test
    public void whenInventoryApiListThrowsErrorThenThrowError() {

        Mockito.when(inventoryClient.getInventories(Mockito.anyLong())).thenThrow(FeignException.FeignClientException.class);

        Assertions.assertThrows(FeignException.FeignClientException.FeignClientException.class, () -> awxInventorySeedService.initializeData());
    }

    @Test
    public void whenInventoryApiCreateThrowsErrorThenThrowError() {

        Mockito.when(inventoryClient.getInventories(Mockito.anyLong())).thenReturn(new ListResponse<>());

        Mockito.when(inventoryClient.createInventory(Mockito.any())).thenThrow(FeignException.FeignClientException.class);

        Assertions.assertThrows(FeignException.FeignClientException.class, () -> awxInventorySeedService.initializeData());
    }

    @Test
    public void whenInventoryApiListReturnsMatchingInventoryThenOnlyCreateDatabaseRecord() {

        sampleBuilder.builder()
                .awxOrganization()
                .build();

        InventoryApi inventoryApi = new InventoryApi();
        inventoryApi.setId(1L);
        inventoryApi.setOrganizationId(1L);
        inventoryApi.setName("Default");
        inventoryApi.setDescription("Default Inventory");

        ListResponse<InventoryApi> listResponse = new ListResponse<>();
        listResponse.setResults(Collections.singletonList(inventoryApi));

        Mockito.when(inventoryClient.getInventories(Mockito.anyLong())).thenReturn(listResponse);

        ImmutableList<Object> awxInventories = awxInventorySeedService.initializeData();

        Assertions.assertEquals(1, awxInventories.size());
    }

    @Test
    public void whenTypeHasValueThenReturnValue() {

        Assertions.assertEquals("Awx Inventory", awxInventorySeedService.type());
    }

    @Test
    public void typeShouldNotBeNull() {

        Assertions.assertNotNull(awxInventorySeedService.type());
    }

    @Test
    public void whenOrderHasValueReturnValue() {

        Assertions.assertEquals(10, awxInventorySeedService.order());
    }

    @Test
    public void orderShouldNotBeNull() {

        Assertions.assertNotNull(awxInventorySeedService.order());
    }
}
