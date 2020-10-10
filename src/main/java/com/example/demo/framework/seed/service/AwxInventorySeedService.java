package com.example.demo.framework.seed.service;

import com.example.demo.awx.feign.ListResponse;
import com.example.demo.awx.inventory.feign.InventoryClient;
import com.example.demo.awx.inventory.feign.model.InventoryApi;
import com.example.demo.awx.inventory.feign.model.InventoryCreateApi;
import com.example.demo.awx.inventory.aggregate.command.AwxInventoryCreateCommand;
import com.example.demo.awx.inventory.projection.IAwxInventoryProjector;
import com.example.demo.framework.properties.AwxConfig;
import com.example.demo.framework.seed.ISeedService;
import com.google.common.collect.ImmutableList;
import lombok.RequiredArgsConstructor;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class AwxInventorySeedService implements ISeedService<Object> {

    private final AwxConfig awxConfig;
    private final IAwxInventoryProjector awxInventoryProjector;
    private final InventoryClient inventoryClient;
    private final CommandGateway commandGateway;

    @Override
    public boolean dataNotExists() {

        return !awxInventoryProjector.existsAny();
    }

    @Override
    public ImmutableList<Object> initializeData() {

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

    private Object createNewAwxInventory() {

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

    private Object createAwxInventory(InventoryApi inventoryApi) {

        AwxInventoryCreateCommand event = AwxInventoryCreateCommand.builder()
                .id(UUID.randomUUID())
                .organizationId(inventoryApi.getOrganizationId())
                .inventoryId(inventoryApi.getId())
                .name(inventoryApi.getName())
                .description(inventoryApi.getDescription())
                .build();

        return commandGateway.sendAndWait(event);
    }
}
