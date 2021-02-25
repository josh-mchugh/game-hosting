package com.example.demo.web.dashboard.command.service;

import com.example.demo.game.entity.QGameEntity;
import com.example.demo.web.dashboard.command.service.model.FetchGameIdByGameTypeQuery;
import com.example.demo.web.dashboard.command.service.model.FetchGameIdByGameTypeResponse;
import com.querydsl.jpa.JPQLQueryFactory;
import lombok.RequiredArgsConstructor;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class DashboardCommandService implements IDashboardCommandService {

    private final JPQLQueryFactory queryFactory;

    @Override
    @QueryHandler
    public FetchGameIdByGameTypeResponse getGameId(FetchGameIdByGameTypeQuery query) {

        QGameEntity qGame = QGameEntity.gameEntity;

        String id = queryFactory.select(qGame.id)
                .from(qGame)
                .where(qGame.type.eq(query.getType()))
                .fetchOne();

        return new FetchGameIdByGameTypeResponse(id != null ? UUID.fromString(id) : null);
    }
}
