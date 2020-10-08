package com.example.demo.awx.inventory.entity.service;

import com.example.demo.awx.inventory.aggregate.event.AwxInventoryCreatedEvent;
import com.example.demo.awx.inventory.entity.model.AwxInventory;

public interface IAwxInventoryService {

    AwxInventory handleCreated(AwxInventoryCreatedEvent event);
}
