package com.example.demo.framework.security.authentication.service.model;

import com.example.demo.framework.security.authentication.service.projection.AuthSuccessProjection;
import lombok.AllArgsConstructor;
import lombok.Value;

@Value
@AllArgsConstructor
public class FetchAuthSuccessByEmailResponse {

    AuthSuccessProjection user;
}
