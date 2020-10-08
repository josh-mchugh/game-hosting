package com.example.demo.awx.inventory.projection;

import com.example.demo.awx.inventory.entity.AwxInventoryEntity;
import com.example.demo.awx.inventory.entity.QAwxInventoryEntity;
import com.example.demo.awx.inventory.entity.mapper.AwxInventoryMapper;
import com.example.demo.awx.inventory.entity.model.AwxInventory;
import com.querydsl.jpa.JPQLQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AwxInventoryProjector implements IAwxInventoryProjector {

    private final JPQLQueryFactory queryFactory;

    @Override
    public boolean existsAny() {

        QAwxInventoryEntity qAwxInventory = QAwxInventoryEntity.awxInventoryEntity;

        long count = queryFactory.select(qAwxInventory)
                .from(qAwxInventory)
                .fetchCount();

        return count >= 1;
    }

    @Override
    public AwxInventory findByName(String name) {

        QAwxInventoryEntity qAwxInventory = QAwxInventoryEntity.awxInventoryEntity;

        AwxInventoryEntity entity = queryFactory.select(qAwxInventory)
                .from(qAwxInventory)
                .where(qAwxInventory.name.eq(name))
                .fetchOne();

        return AwxInventoryMapper.map(entity);
    }
}
