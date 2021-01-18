package com.example.demo.game.entity;

import com.example.demo.framework.database.AbstractAggregateEntity;
import com.example.demo.ovh.flavor.entity.FlavorEntity;
import com.example.demo.ovh.image.entity.ImageEntity;
import com.example.demo.ovh.region.entity.RegionEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Getter
@Setter
@Entity
@Table(name = "game_server")
public class GameServerEntity extends AbstractAggregateEntity  {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "game_id", nullable = false)
    private GameEntity gameEntity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ovh_region_id", nullable = false)
    private RegionEntity regionEntity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ovh_flavor_id", nullable = false)
    private FlavorEntity flavorEntity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ovh_image_id", nullable = false)
    private ImageEntity imageEntity;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "description")
    private String description;
}
