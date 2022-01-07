package com.example.demo.framework.seed.awx.inventory;

import com.example.demo.awx.feign.ListResponse;
import com.example.demo.awx.inventory.entity.model.AwxInventory;
import com.example.demo.awx.inventory.feign.InventoryFeignService;
import com.example.demo.awx.inventory.feign.model.InventoryApi;
import com.example.demo.awx.inventory.feign.model.InventoryCreateApi;
import com.example.demo.awx.inventory.service.AwxInventoryService;
import com.example.demo.awx.inventory.service.model.AwxInventoryCreateRequest;
import com.example.demo.framework.properties.AwxConfig;
import com.example.demo.framework.seed.SeedService;
import com.example.demo.framework.seed.awx.inventory.projection.model.ExistsAnyAwxInventoryQuery;
import com.example.demo.framework.seed.awx.inventory.projection.model.ExistsAnyAwxInventoryResponse;
import com.example.demo.framework.seed.awx.inventory.projection.model.FetchAwxOrganizationIdByAwxIdQuery;
import com.example.demo.framework.seed.awx.inventory.projection.model.FetchAwxOrganizationIdByAwxIdResponse;
import com.google.common.collect.ImmutableList;
import lombok.RequiredArgsConstructor;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.ExecutionException;

@Component
@RequiredArgsConstructor
public class AwxInventorySeedService implements SeedService<String> {

    private final AwxConfig awxConfig;
    private final InventoryFeignService inventoryFeignService;
    private final QueryGateway queryGateway;
    private final AwxInventoryService awxInventoryService;

    @Override
    public boolean dataNotExists() throws ExecutionException, InterruptedException {

        ExistsAnyAwxInventoryQuery query = new ExistsAnyAwxInventoryQuery();
        ExistsAnyAwxInventoryResponse response = queryGateway.query(query, ExistsAnyAwxInventoryResponse.class).get();

        return !response.exists();
    }

    @Override
    public ImmutableList<String> initializeData() throws ExecutionException, InterruptedException {

        ListResponse<InventoryApi> inventoryApiListResponse = inventoryFeignService.getInventories();

        Optional<InventoryApi> inventoryApiResult = inventoryApiListResponse.getResults().stream()
                .filter(inventory -> inventory.getName().equals(awxConfig.getInventory().getName()))
                .findFirst();

        if (inventoryApiResult.isPresent()) {

            AwxInventory awxInventory = createAwxInventory(inventoryApiResult.get());

            return ImmutableList.of(awxInventory.getId());

        }

        return ImmutableList.of(createNewAwxInventory().getId());
    }

    @Override
    public String type() {

        return "Awx Inventory";
    }

    @Override
    public Integer order() {

        return 10;
    }

    private AwxInventory createNewAwxInventory() throws ExecutionException, InterruptedException {

        InventoryApi inventoryApi = createInventoryApi();

        return createAwxInventory(inventoryApi);
    }

    private InventoryApi createInventoryApi() {

        InventoryCreateApi inventoryCreateApi = InventoryCreateApi.builder()
                .organizationId(awxConfig.getOrganization().getId())
                .name(awxConfig.getInventory().getName())
                .description(awxConfig.getInventory().getDescription())
                .build();

        return inventoryFeignService.createInventory(inventoryCreateApi);
    }

    private AwxInventory createAwxInventory(InventoryApi inventoryApi) throws ExecutionException, InterruptedException {

        UUID awxOrganizationId = fetchAwxOrganizationIdByAwxId(inventoryApi.getOrganizationId());

        AwxInventoryCreateRequest request = AwxInventoryCreateRequest.builder()
                .awxOrganizationId(awxOrganizationId)
                .awxId(inventoryApi.getId())
                .name(inventoryApi.getName())
                .description(inventoryApi.getDescription())
                .build();

        return awxInventoryService.handleCreate(request);
    }

    private UUID fetchAwxOrganizationIdByAwxId(Long awxId) throws ExecutionException, InterruptedException {

        FetchAwxOrganizationIdByAwxIdQuery query = new FetchAwxOrganizationIdByAwxIdQuery(awxId);
        FetchAwxOrganizationIdByAwxIdResponse response = queryGateway.query(query, FetchAwxOrganizationIdByAwxIdResponse.class).get();

        return response.getId();
    }
}
