package com.example.demo.web.admin.user.service;

import com.example.demo.user.projection.IUserProjector;
import com.example.demo.user.projection.model.AdminUserProjection;
import com.example.demo.user.projection.model.FetchAdminUserPageableProjection;
import com.example.demo.user.projection.model.FetchAdminUserPageableQuery;
import com.example.demo.web.admin.user.form.AdminUserFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AdminUserProjectorService implements IAdminUserProjectorService {

    private final IUserProjector userProjector;

    @Override
    public Page<AdminUserProjection> fetchAdminUsersPage(AdminUserFilter filter, Pageable pageable) {

        FetchAdminUserPageableQuery query = FetchAdminUserPageableQuery.builder()
                .email(filter.getEmail())
                .states(filter.getSelectedStates())
                .types(filter.getSelectedTypes())
                .pageable(pageable)
                .build();

        FetchAdminUserPageableProjection projection = userProjector.fetchAdminUserPageable(query);

        return projection.getPage();
    }
}
