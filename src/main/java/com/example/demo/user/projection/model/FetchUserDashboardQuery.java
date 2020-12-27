package com.example.demo.user.projection.model;

import lombok.AllArgsConstructor;
import lombok.Value;

@Value
@AllArgsConstructor
public class FetchUserDashboardQuery {

    String email;
}
