package com.example.demo.awx.inventory.projection;

import com.example.demo.awx.inventory.entity.model.AwxInventory;

public interface IAwxInventoryProjector {

    AwxInventory findByName(String name);
}
