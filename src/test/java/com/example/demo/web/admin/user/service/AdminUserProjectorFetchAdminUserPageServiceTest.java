package com.example.demo.web.admin.user.service;

import com.example.demo.user.projection.IUserProjector;
import com.example.demo.user.projection.model.AdminUserProjection;
import com.example.demo.user.projection.model.FetchAdminUserPageableProjection;
import com.example.demo.web.admin.user.form.AdminUserFilter;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import java.util.ArrayList;

public class AdminUserProjectorFetchAdminUserPageServiceTest {

    private IUserProjector userProjector;

    @BeforeEach
    public void setup() {

        userProjector = Mockito.mock(IUserProjector.class);
    }

    @Test
    public void whenFilterParamIsNullThenThrowException() {

        AdminUserProjectorService service = new AdminUserProjectorService(userProjector);

        Assertions.assertThrows(NullPointerException.class, () -> service.fetchAdminUsersPage(null, PageRequest.of(1, 1)));
    }

    @Test
    public void whenPageableParamIsNullThenDoNotThrowException() {

        Mockito.when(userProjector.fetchAdminUserPageable(Mockito.any())).thenReturn(new FetchAdminUserPageableProjection(new PageImpl<>(new ArrayList<>())));

        AdminUserProjectorService service = new AdminUserProjectorService(userProjector);

        Assertions.assertDoesNotThrow(() -> service.fetchAdminUsersPage(new AdminUserFilter(), null));
    }

    @Test
    public void whenFetchAdminUsersPAgeIsValidThenReturnPageable() {


        Mockito.when(userProjector.fetchAdminUserPageable(Mockito.any())).thenReturn(new FetchAdminUserPageableProjection(new PageImpl<>(new ArrayList<>())));

        AdminUserProjectorService service = new AdminUserProjectorService(userProjector);

        Page<AdminUserProjection> expected = new PageImpl<>(new ArrayList<>());

        Assertions.assertEquals(expected, service.fetchAdminUsersPage(new AdminUserFilter(), PageRequest.of(1, 1)));
    }
}
