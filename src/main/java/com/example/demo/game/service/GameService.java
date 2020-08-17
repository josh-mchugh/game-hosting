package com.example.demo.game.service;

import com.example.demo.game.entity.GameEntity;
import com.example.demo.game.entity.QGameEntity;
import com.example.demo.game.mapper.GameMapper;
import com.example.demo.game.model.Game;
import com.example.demo.game.service.model.GameCreateRequest;
import com.querydsl.jpa.JPQLQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

@Component
@Transactional
@RequiredArgsConstructor
public class GameService implements IGameService {

    private final JPQLQueryFactory queryFactory;
    private final EntityManager entityManager;

    @Override
    public boolean existsAny() {

        QGameEntity qGame = QGameEntity.gameEntity;

        long count = queryFactory.select(qGame.id)
                .from(qGame)
                .fetchCount();

        return count >= 1;
    }

    @Override
    public Game handleGameCreateRequest(GameCreateRequest request) {

        GameEntity entity = new GameEntity();
        entity.setType(request.getType());

        entityManager.persist(entity);

        return GameMapper.map(entity);
    }
}
