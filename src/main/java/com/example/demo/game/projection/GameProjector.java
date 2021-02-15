package com.example.demo.game.projection;

import com.example.demo.game.entity.GameEntity;
import com.example.demo.game.entity.GameType;
import com.example.demo.game.entity.QGameEntity;
import com.example.demo.game.entity.mapper.GameMapper;
import com.example.demo.game.entity.model.Game;
import com.querydsl.jpa.JPQLQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class GameProjector implements IGameProjector {

    private final JPQLQueryFactory queryFactory;

    @Override
    public boolean existsAny() {

        QGameEntity qGame = QGameEntity.gameEntity;

        long count = queryFactory.select(qGame.id)
                .from(qGame)
                .fetchCount();

        return count >= 1;
    }

    @Override
    public Game getGameByType(GameType type) {

        QGameEntity qGame = QGameEntity.gameEntity;

        GameEntity entity = queryFactory.select(qGame)
                .from(qGame)
                .where(qGame.type.eq(type))
                .fetchOne();

        return GameMapper.map(entity);
    }
}
