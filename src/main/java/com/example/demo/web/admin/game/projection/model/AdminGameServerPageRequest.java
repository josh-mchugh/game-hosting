package com.example.demo.web.admin.game.projection.model;

import lombok.AllArgsConstructor;
import lombok.Value;
import org.springframework.data.domain.Pageable;

@Value
@AllArgsConstructor
public class AdminGameServerPageRequest {

    Pageable pageable;
}
