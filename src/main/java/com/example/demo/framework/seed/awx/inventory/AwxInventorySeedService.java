package com.example.demo.framework.seed.awx.inventory;

import com.example.demo.awx.feign.ListResponse;
import com.example.demo.awx.inventory.aggregate.command.AwxInventoryCreateCommand;
import com.example.demo.awx.inventory.feign.InventoryFeignService;
import com.example.demo.awx.inventory.feign.model.InventoryApi;
import com.example.demo.awx.inventory.feign.model.InventoryCreateApi;
import com.example.demo.framework.properties.AwxConfig;
import com.example.demo.framework.seed.SeedService;
import com.example.demo.framework.seed.awx.inventory.projection.model.ExistsAnyAwxInventoryQuery;
import com.example.demo.framework.seed.awx.inventory.projection.model.ExistsAnyAwxInventoryResponse;
import com.example.demo.framework.seed.awx.inventory.projection.model.FetchAwxOrganizationIdByAwxIdQuery;
import com.example.demo.framework.seed.awx.inventory.projection.model.FetchAwxOrganizationIdByAwxIdResponse;
import com.google.common.collect.ImmutableList;
import lombok.RequiredArgsConstructor;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.ExecutionException;

@Component
@RequiredArgsConstructor
public class AwxInventorySeedService implements SeedService<Object> {

    private final AwxConfig awxConfig;
    private final InventoryFeignService inventoryFeignService;
    private final CommandGateway commandGateway;
    private final QueryGateway queryGateway;

    @Override
    public boolean dataNotExists() throws ExecutionException, InterruptedException {

        ExistsAnyAwxInventoryQuery query = new ExistsAnyAwxInventoryQuery();
        ExistsAnyAwxInventoryResponse response = queryGateway.query(query, ExistsAnyAwxInventoryResponse.class).get();

        return !response.exists();
    }

    @Override
    public ImmutableList<Object> initializeData() throws ExecutionException, InterruptedException {

        ListResponse<InventoryApi> inventoryApiListResponse = inventoryFeignService.getInventories();

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

    private Object createNewAwxInventory() throws ExecutionException, InterruptedException {

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

    private Object createAwxInventory(InventoryApi inventoryApi) throws ExecutionException, InterruptedException {

        UUID awxOrganizationId = fetchAwxOrganizationIdByAwxId(inventoryApi.getOrganizationId());

        AwxInventoryCreateCommand event = AwxInventoryCreateCommand.builder()
                .id(UUID.randomUUID())
                .awxOrganizationId(awxOrganizationId)
                .awxId(inventoryApi.getId())
                .name(inventoryApi.getName())
                .description(inventoryApi.getDescription())
                .build();

        return commandGateway.sendAndWait(event);
    }

    private UUID fetchAwxOrganizationIdByAwxId(Long awxId) throws ExecutionException, InterruptedException {

        FetchAwxOrganizationIdByAwxIdQuery query = new FetchAwxOrganizationIdByAwxIdQuery(awxId);
        FetchAwxOrganizationIdByAwxIdResponse response = queryGateway.query(query, FetchAwxOrganizationIdByAwxIdResponse.class).get();

        return response.getId();
    }
}
