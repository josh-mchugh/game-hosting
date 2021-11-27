package com.example.demo.web.admin.game.form;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class AdminGameServerSelect2Request {

    @NotBlank
    private String regionId;
    private String search;
}
