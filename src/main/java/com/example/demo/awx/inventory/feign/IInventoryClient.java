package com.example.demo.awx.inventory.feign;

import com.example.demo.awx.feign.ListResponse;
import com.example.demo.awx.inventory.feign.model.InventoryApi;
import com.example.demo.awx.inventory.feign.model.InventoryCreateApi;
import com.example.demo.framework.feign.FeignAwxConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "inventoryClient", url = "${awx.base-url}", configuration = FeignAwxConfig.class)
public interface IInventoryClient {

    @GetMapping("/api/v2/organizations/{organizationId}/inventories/")
    ListResponse<InventoryApi> getInventories(@PathVariable("organizationId") Long organizationId);

    @PostMapping("/api/v2/inventories/")
    InventoryApi createInventory(@RequestBody InventoryCreateApi body);
}
