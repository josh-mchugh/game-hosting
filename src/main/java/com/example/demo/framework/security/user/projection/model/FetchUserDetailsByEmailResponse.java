package com.example.demo.framework.security.user.projection.model;

import com.example.demo.framework.security.user.projection.projection.UserDetailsProjection;
import lombok.AllArgsConstructor;
import lombok.Value;

@Value
@AllArgsConstructor
public class FetchUserDetailsByEmailResponse {

    UserDetailsProjection userDetails;
}
