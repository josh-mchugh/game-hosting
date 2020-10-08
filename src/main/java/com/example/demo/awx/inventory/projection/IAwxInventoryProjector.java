package com.example.demo.awx.inventory.projection;

import com.example.demo.awx.inventory.entity.model.AwxInventory;

public interface IAwxInventoryProjector {

    boolean existsAny();

    AwxInventory findByName(String name);
}
