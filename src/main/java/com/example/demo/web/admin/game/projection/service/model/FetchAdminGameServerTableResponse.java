package com.example.demo.web.admin.game.projection.service.model;

import com.example.demo.web.admin.game.projection.service.projection.AdminGameServerTableProjection;
import lombok.AllArgsConstructor;
import lombok.Value;
import org.springframework.data.domain.Page;

@Value
@AllArgsConstructor
public class FetchAdminGameServerTableResponse {

    Page<AdminGameServerTableProjection> gameServers;
}
