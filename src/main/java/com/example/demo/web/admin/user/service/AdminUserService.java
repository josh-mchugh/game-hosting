package com.example.demo.web.admin.user.service;

import com.example.demo.web.admin.user.service.model.FetchAdminUserTableResponse;
import com.example.demo.web.admin.user.service.model.FetchAdminUserTableQuery;

public interface AdminUserService {

    FetchAdminUserTableResponse getTable(FetchAdminUserTableQuery query);
}
