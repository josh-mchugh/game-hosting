package com.example.demo.web.admin.user.service;

import com.example.demo.user.projection.model.AdminUserProjection;
import com.example.demo.web.admin.user.form.AdminUserFilter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IAdminUserProjectorService {

    Page<AdminUserProjection> fetchAdminUsersPage(AdminUserFilter filter, Pageable pageable);
}
