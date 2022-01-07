package com.example.demo.awx.inventory.service;

import com.example.demo.awx.inventory.entity.model.AwxInventory;
import com.example.demo.awx.inventory.service.model.AwxInventoryCreateRequest;

public interface AwxInventoryService {

    AwxInventory handleCreate(AwxInventoryCreateRequest request);
}
