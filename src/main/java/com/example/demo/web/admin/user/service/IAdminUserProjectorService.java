package com.example.demo.web.admin.user.service;

import com.example.demo.web.admin.user.service.model.FetchAdminUserTableResponse;
import com.example.demo.web.admin.user.service.model.FetchAdminUserTableQuery;

public interface IAdminUserProjectorService {

    FetchAdminUserTableResponse getTable(FetchAdminUserTableQuery query);
}
