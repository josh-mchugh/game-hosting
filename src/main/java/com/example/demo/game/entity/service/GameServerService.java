package com.example.demo.game.entity.service;

import com.example.demo.game.aggregate.event.GameServerCreatedEvent;
import com.example.demo.game.entity.GameEntity;
import com.example.demo.game.entity.GameServerEntity;
import com.example.demo.game.entity.QGameEntity;
import com.example.demo.game.entity.mapper.GameServerMapper;
import com.example.demo.game.entity.model.GameServer;
import com.example.demo.ovh.flavor.entity.FlavorEntity;
import com.example.demo.ovh.flavor.entity.QFlavorEntity;
import com.example.demo.ovh.image.entity.ImageEntity;
import com.example.demo.ovh.image.entity.QImageEntity;
import com.example.demo.ovh.region.entity.QRegionEntity;
import com.example.demo.ovh.region.entity.RegionEntity;
import com.querydsl.jpa.JPQLQueryFactory;
import lombok.RequiredArgsConstructor;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class GameServerService implements IGameServerService {

    private final JPQLQueryFactory queryFactory;
    private final EntityManager entityManager;

    @Override
    @EventHandler
    public GameServer handleCreated(GameServerCreatedEvent event) {

        GameServerEntity entity = new GameServerEntity();
        entity.setId(event.getId());
        entity.setGameEntity(findGameEntity(event.getGameId()));
        entity.setRegionEntity(findRegionEntity(event.getRegionId()));
        entity.setFlavorEntity(findFlavorEntity(event.getFlavorId()));
        entity.setImageEntity(findImageEntity(event.getImageId()));
        entity.setName(event.getName());
        entity.setDescription(event.getDescription());

        entityManager.persist(entity);

        return GameServerMapper.map(entity);
    }

    private GameEntity findGameEntity(UUID id) {

        QGameEntity qGame = QGameEntity.gameEntity;

        return queryFactory.select(qGame)
                .from(qGame)
                .where(qGame.id.eq(id.toString()))
                .fetchOne();
    }

    private RegionEntity findRegionEntity(UUID id) {

        QRegionEntity qRegion = QRegionEntity.regionEntity;

        return queryFactory.select(qRegion)
                .from(qRegion)
                .where(qRegion.id.eq(id.toString()))
                .fetchOne();
    }

    private FlavorEntity findFlavorEntity(UUID id) {

        QFlavorEntity qFlavor = QFlavorEntity.flavorEntity;

        return queryFactory.select(qFlavor)
                .from(qFlavor)
                .where(qFlavor.id.eq(id.toString()))
                .fetchOne();
    }

    private ImageEntity findImageEntity(UUID id) {

        QImageEntity qImage = QImageEntity.imageEntity;

        return queryFactory.select(qImage)
                .from(qImage)
                .where(qImage.id.eq(id.toString()))
                .fetchOne();
    }
}
