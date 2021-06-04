package com.example.demo.web.project.util.model;

import lombok.Builder;
import lombok.Value;

@Value
@Builder(builderClassName = "Builder")
public class ProjectStateValidationResponse {

    boolean valid;
    String redirectUrl;
}
