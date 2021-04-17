package com.example.demo.framework.seed.awx.inventory.projection;

import com.example.demo.awx.inventory.entity.QAwxInventoryEntity;
import com.example.demo.awx.organization.entity.QAwxOrganizationEntity;
import com.example.demo.framework.seed.awx.inventory.projection.model.ExistsAnyAwxInventoryQuery;
import com.example.demo.framework.seed.awx.inventory.projection.model.ExistsAnyAwxInventoryResponse;
import com.example.demo.framework.seed.awx.inventory.projection.model.FetchAwxOrganizationIdByAwxIdQuery;
import com.example.demo.framework.seed.awx.inventory.projection.model.FetchAwxOrganizationIdByAwxIdResponse;
import com.querydsl.jpa.JPQLQueryFactory;
import lombok.RequiredArgsConstructor;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class AwxInventorySeedProjectionService implements IAwxInventorySeedProjectionService {

    private final JPQLQueryFactory queryFactory;

    @Override
    @QueryHandler
    public ExistsAnyAwxInventoryResponse existsAny(ExistsAnyAwxInventoryQuery query) {

        QAwxInventoryEntity qAwxInventory = QAwxInventoryEntity.awxInventoryEntity;

        long count = queryFactory.select(qAwxInventory)
                .from(qAwxInventory)
                .fetchCount();

        return new ExistsAnyAwxInventoryResponse(count >= 1);
    }

    @Override
    @QueryHandler
    public FetchAwxOrganizationIdByAwxIdResponse fetchAwxOrganizationIdByAwxId(FetchAwxOrganizationIdByAwxIdQuery query) {

        QAwxOrganizationEntity qAwxOrganization = QAwxOrganizationEntity.awxOrganizationEntity;

        String id = queryFactory.select(qAwxOrganization.id)
                .from(qAwxOrganization)
                .where(qAwxOrganization.awxId.eq(query.getAwxId()))
                .fetchOne();

        return new FetchAwxOrganizationIdByAwxIdResponse(id != null ? UUID.fromString(id) : null);
    }
}
