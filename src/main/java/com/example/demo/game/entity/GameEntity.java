package com.example.demo.game.entity;

import com.example.demo.framework.database.AbstractAggregateEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

@Getter
@Setter
@Entity
@Table(name = "game")
public class GameEntity extends AbstractAggregateEntity {

    @Enumerated(EnumType.STRING)
    @Column(name = "type", unique = true, nullable = false)
    private GameType type;
}
