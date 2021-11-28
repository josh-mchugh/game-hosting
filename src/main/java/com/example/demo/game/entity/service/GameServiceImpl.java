package com.example.demo.game.entity.service;

import com.example.demo.game.aggregate.event.GameCreatedEvent;
import com.example.demo.game.entity.GameEntity;
import com.example.demo.game.entity.mapper.GameMapper;
import com.example.demo.game.entity.model.Game;
import lombok.RequiredArgsConstructor;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

@Component
@Transactional
@RequiredArgsConstructor
public class GameServiceImpl implements GameService {

    private final EntityManager entityManager;

    @Override
    @EventHandler
    public Game handleCreated(GameCreatedEvent event) {

        GameEntity entity = new GameEntity();
        entity.setId(event.getId());
        entity.setType(event.getType());

        entityManager.persist(entity);

        return GameMapper.map(entity);
    }
}
