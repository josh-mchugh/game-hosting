package com.example.demo.web.admin.game.service.model;

import lombok.AllArgsConstructor;
import lombok.Value;

@Value
@AllArgsConstructor
public class ExistsGameServerByNameQuery {

    String name;
}
