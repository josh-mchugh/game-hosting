package com.example.demo.web.admin.game.projection.service.projection;

import com.example.demo.game.entity.GameType;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Value;

@Value
public class AdminGameServerTableProjection {

    String id;
    String name;
    GameType gameType;
    String regionName;
    Integer vcpus;
    Integer ram;
    String imageName;

    @QueryProjection
    public AdminGameServerTableProjection(String id, String name, GameType gameType, String regionName, Integer vcpus, Integer ram, String imageName) {
        this.id = id;
        this.name = name;
        this.gameType = gameType;
        this.regionName = regionName;
        this.vcpus = vcpus;
        this.ram = ram;
        this.imageName = imageName;
    }
}
