package com.example.demo.web.project.create.projection.model;

import lombok.AllArgsConstructor;
import lombok.Value;

import java.util.UUID;

@Value
@AllArgsConstructor
public class FetchProjectImageIdResponse {

    UUID imageId;
}
