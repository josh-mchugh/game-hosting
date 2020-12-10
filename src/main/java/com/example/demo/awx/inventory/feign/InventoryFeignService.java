package com.example.demo.awx.inventory.feign;

import com.example.demo.awx.feign.ListResponse;
import com.example.demo.awx.inventory.feign.model.InventoryApi;
import com.example.demo.awx.inventory.feign.model.InventoryCreateApi;
import com.example.demo.framework.properties.AwxConfig;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class InventoryFeignService implements IInventoryFeignService {

    private final AwxConfig awxConfig;
    private final IInventoryClient inventoryClient;

    @Override
    public ListResponse<InventoryApi> getInventories() {

        return inventoryClient.getInventories(awxConfig.getOrganization().getId());
    }

    @Override
    public InventoryApi createInventory(InventoryCreateApi body) {

        return inventoryClient.createInventory(body);
    }
}
