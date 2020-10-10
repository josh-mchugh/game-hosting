package com.example.demo.game.projection;

import com.example.demo.game.entity.QGameEntity;
import com.querydsl.jpa.JPQLQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class GameProjection implements IGameProjection {

    private final JPQLQueryFactory queryFactory;

    @Override
    public boolean existsAny() {

        QGameEntity qGame = QGameEntity.gameEntity;

        long count = queryFactory.select(qGame.id)
                .from(qGame)
                .fetchCount();

        return count >= 1;
    }
}
