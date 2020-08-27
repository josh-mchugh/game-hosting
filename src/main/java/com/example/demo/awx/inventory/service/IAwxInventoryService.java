package com.example.demo.awx.inventory.service;

import com.example.demo.awx.inventory.model.AwxInventory;
import com.example.demo.awx.inventory.service.model.AwxInventoryCreateRequest;

public interface IAwxInventoryService {

    boolean existsAny();

    AwxInventory handleCreateRequest(AwxInventoryCreateRequest request);
}
