package com.example.demo.framework.seed.service;

import com.example.demo.awx.feign.common.ListResponse;
import com.example.demo.awx.feign.inventory.InventoryClient;
import com.example.demo.awx.feign.inventory.model.InventoryApi;
import com.example.demo.awx.feign.inventory.model.InventoryCreateApi;
import com.example.demo.awx.inventory.model.AwxInventory;
import com.example.demo.awx.inventory.service.AwxInventoryService;
import com.example.demo.awx.inventory.service.model.AwxInventoryCreateRequest;
import com.example.demo.framework.properties.AppConfig;
import com.example.demo.framework.seed.ISeedService;
import com.google.common.collect.ImmutableList;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class AwxInventorySeedService implements ISeedService<AwxInventory> {

    private final AppConfig appConfig;
    private final AwxInventoryService awxInventoryService;
    private final InventoryClient inventoryClient;

    @Override
    public boolean dataNotExists() {

        return !awxInventoryService.existsAny();
    }

    @Override
    public ImmutableList<AwxInventory> initializeData() {

        ListResponse<InventoryApi> inventoryApiListResponse = inventoryClient.getInventories(appConfig.getAwx().getOrganization().getId());

        Optional<InventoryApi> inventoryApiResult = inventoryApiListResponse.getResults().stream()
                .filter(inventory -> inventory.getName().equals(appConfig.getAwx().getInventory().getName()))
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

        return 12;
    }

    private AwxInventory createNewAwxInventory() {

        InventoryApi inventoryApi = createInventoryApi();

        return createAwxInventory(inventoryApi);
    }

    private InventoryApi createInventoryApi() {

        InventoryCreateApi inventoryCreateApi = InventoryCreateApi.builder()
                .organizationId(appConfig.getAwx().getOrganization().getId())
                .name(appConfig.getAwx().getInventory().getName())
                .description(appConfig.getAwx().getInventory().getDescription())
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
