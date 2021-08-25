package com.example.demo.web.project.create.query.model;

import lombok.AllArgsConstructor;
import lombok.Value;

import java.util.UUID;

@Value
@AllArgsConstructor
public class FetchProjectStatusAndStateQuery {

    UUID id;
}
