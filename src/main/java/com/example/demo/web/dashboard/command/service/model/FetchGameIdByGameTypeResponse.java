package com.example.demo.web.dashboard.command.service.model;

import lombok.AllArgsConstructor;
import lombok.Value;

import java.util.UUID;

@Value
@AllArgsConstructor
public class FetchGameIdByGameTypeResponse {

    UUID id;
}
