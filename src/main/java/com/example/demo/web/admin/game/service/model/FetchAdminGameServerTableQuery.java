package com.example.demo.web.admin.game.service.model;

import lombok.AllArgsConstructor;
import lombok.Value;
import org.springframework.data.domain.Pageable;

@Value
@AllArgsConstructor
public class FetchAdminGameServerTableQuery {

    Pageable pageable;
}
