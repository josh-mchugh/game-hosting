package com.example.demo.awx.inventory.feign;

import com.example.demo.awx.feign.ListResponse;
import com.example.demo.awx.inventory.feign.model.InventoryApi;
import com.example.demo.awx.inventory.feign.model.InventoryCreateApi;

public interface IInventoryFeignService {

    ListResponse<InventoryApi> getInventories();

    InventoryApi createInventory(InventoryCreateApi body);
}
