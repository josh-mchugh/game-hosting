package com.example.demo.framework.seed.awx.inventory.projection;

import com.example.demo.framework.seed.awx.inventory.projection.model.ExistsAnyAwxInventoryQuery;
import com.example.demo.framework.seed.awx.inventory.projection.model.ExistsAnyAwxInventoryResponse;

public interface IAwxInventorySeedProjectionService {

    ExistsAnyAwxInventoryResponse existsAny(ExistsAnyAwxInventoryQuery query);
}
