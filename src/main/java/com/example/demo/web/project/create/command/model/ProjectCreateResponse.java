package com.example.demo.web.project.create.command.model;

import lombok.AllArgsConstructor;
import lombok.Value;

import java.util.UUID;

@Value
@AllArgsConstructor
public class ProjectCreateResponse {

    UUID id;
}
