package com.example.demo.web.awx.service.model;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder(builderClassName = "Builder")
public class PlaybookCreateRequest {

    Long projectId;
}
