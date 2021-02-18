package com.example.demo.web.admin.user.service.model;

import com.example.demo.web.admin.user.service.projection.AdminUserProjection;
import lombok.AllArgsConstructor;
import lombok.Value;
import org.springframework.data.domain.Page;

@Value
@AllArgsConstructor
public class FetchAdminUserTableResponse {

    Page<AdminUserProjection> page;
}
