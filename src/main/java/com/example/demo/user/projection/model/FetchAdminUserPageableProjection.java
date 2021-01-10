package com.example.demo.user.projection.model;

import lombok.AllArgsConstructor;
import lombok.Value;
import org.springframework.data.domain.Page;

@Value
@AllArgsConstructor
public class FetchAdminUserPageableProjection {

    Page<AdminUserProjection> page;
}
