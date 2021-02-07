package com.example.demo.game.projection.model;

import lombok.AllArgsConstructor;
import lombok.Value;

@Value
@AllArgsConstructor
public class ExistsGameServerByNameQuery {

    String name;
}
