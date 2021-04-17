package com.example.demo.framework.seed.awx.inventory.projection;

import com.example.demo.framework.seed.awx.inventory.projection.model.ExistsAnyAwxInventoryQuery;
import com.example.demo.framework.seed.awx.inventory.projection.model.ExistsAnyAwxInventoryResponse;
import com.example.demo.framework.seed.awx.inventory.projection.model.FetchAwxOrganizationIdByAwxIdQuery;
import com.example.demo.framework.seed.awx.inventory.projection.model.FetchAwxOrganizationIdByAwxIdResponse;

public interface IAwxInventorySeedProjectionService {

    ExistsAnyAwxInventoryResponse existsAny(ExistsAnyAwxInventoryQuery query);

    FetchAwxOrganizationIdByAwxIdResponse fetchAwxOrganizationIdByAwxId(FetchAwxOrganizationIdByAwxIdQuery query);
}
