package com.example.demo.framework.seed.service;

import com.example.demo.awx.feign.common.ListResponse;
import com.example.demo.awx.feign.inventory.InventoryClient;
import com.example.demo.awx.feign.inventory.model.InventoryApi;
import com.example.demo.awx.feign.inventory.model.InventoryCreateApi;
import com.example.demo.awx.inventory.model.AwxInventory;
import com.example.demo.awx.inventory.service.AwxInventoryService;
import com.example.demo.awx.inventory.service.model.AwxInventoryCreateRequest;
import com.example.demo.framework.properties.AwxConfig;
import com.example.demo.framework.seed.ISeedService;
import com.google.common.collect.ImmutableList;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class AwxInventorySeedService implements ISeedService<AwxInventory> {

    private final AwxConfig awxConfig;
    private final AwxInventoryService awxInventoryService;
    private final InventoryClient inventoryClient;

    @Override
    public boolean dataNotExists() {

        return !awxInventoryService.existsAny();
    }

    @Override
    public ImmutableList<AwxInventory> initializeData() {

        ListResponse<InventoryApi> inventoryApiListResponse = inventoryClient.getInventories(awxConfig.getOrganization().getId());

        Optional<InventoryApi> inventoryApiResult = inventoryApiListResponse.getResults().stream()
                .filter(inventory -> inventory.getName().equals(awxConfig.getInventory().getName()))
                .findFirst();

        if (inventoryApiResult.isPresent()) {

            return ImmutableList.of(createAwxInventory(inventoryApiResult.get()));

        }

        return ImmutableList.of(createNewAwxInventory());
    }

    @Override
    public String type() {

        return "Awx Inventory";
    }

    @Override
    public Integer order() {

        return 10;
    }

    private AwxInventory createNewAwxInventory() {

        InventoryApi inventoryApi = createInventoryApi();

        return createAwxInventory(inventoryApi);
    }

    private InventoryApi createInventoryApi() {

        InventoryCreateApi inventoryCreateApi = InventoryCreateApi.builder()
                .organizationId(awxConfig.getOrganization().getId())
                .name(awxConfig.getInventory().getName())
                .description(awxConfig.getInventory().getDescription())
                .build();

        return inventoryClient.createInventory(inventoryCreateApi);
    }

    private AwxInventory createAwxInventory(InventoryApi inventoryApi) {

        AwxInventoryCreateRequest request = AwxInventoryCreateRequest.builder()
                .organizationId(inventoryApi.getOrganizationId())
                .name(inventoryApi.getName())
                .description(inventoryApi.getDescription())
                .inventoryId(inventoryApi.getId())
                .build();

        return awxInventoryService.handleCreateRequest(request);
    }
}
