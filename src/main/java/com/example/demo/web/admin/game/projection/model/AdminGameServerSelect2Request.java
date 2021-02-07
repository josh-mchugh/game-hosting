package com.example.demo.web.admin.game.projection.model;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class AdminGameServerSelect2Request {

    @NotBlank
    private String regionId;
    private String search;
}
