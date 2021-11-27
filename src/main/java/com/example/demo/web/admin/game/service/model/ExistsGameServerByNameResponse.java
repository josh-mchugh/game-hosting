package com.example.demo.web.admin.game.service.model;

import lombok.AllArgsConstructor;
import lombok.Value;
import lombok.experimental.Accessors;

@Value
@AllArgsConstructor
public class ExistsGameServerByNameResponse {

    @Accessors(fluent = true)
    boolean exists;
}
