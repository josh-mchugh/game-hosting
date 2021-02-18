package com.example.demo.web.admin.user.service.model;

import com.example.demo.user.entity.UserState;
import com.example.demo.user.entity.UserType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;
import java.util.Collections;

public class FetchAdminUserTableQueryTest {

    @Test
    public void whenQueryHasEmailThenReturnEmail() {

        FetchAdminUserTableQuery query = FetchAdminUserTableQuery.builder()
                .email("email")
                .build();

        Assertions.assertEquals("email", query.getEmail());
    }

    @Test
    public void whenQueryHasUserStatesThenReturnUserStates() {

        FetchAdminUserTableQuery query = FetchAdminUserTableQuery.builder()
                .states(Collections.singletonList(UserState.ACTIVE))
                .build();

        Assertions.assertEquals(Collections.singletonList(UserState.ACTIVE), query.getStates());
    }

    @Test
    public void whenQueryHasTypesThenReturnTypes() {

        FetchAdminUserTableQuery query = FetchAdminUserTableQuery.builder()
                .types(Collections.singletonList(UserType.ADMIN))
                .build();

        Assertions.assertEquals(Collections.singletonList(UserType.ADMIN), query.getTypes());
    }

    @Test
    public void whenQueryHasPageableThenReturnPageable() {

        Pageable pageable = PageRequest.of(1, 1);

        FetchAdminUserTableQuery query = FetchAdminUserTableQuery.builder()
                .pageable(pageable)
                .build();

        Assertions.assertEquals(pageable, query.getPageable());
    }

    @Test
    public void whenQueryToString() {

        FetchAdminUserTableQuery query = query();

        String expected = "FetchAdminUserTableQuery(email=email, states=[], types=[], pageable=Page request [number: 1, size 1, sort: UNSORTED])";

        Assertions.assertEquals(expected, query.toString());
    }

    @Test
    public void whenQueryHashCode() {

        FetchAdminUserTableQuery query = query();

        Assertions.assertEquals(863104872, query.hashCode());
    }

    @Test
    public void whenQueryEquals() {

        FetchAdminUserTableQuery query1 = query();
        FetchAdminUserTableQuery query2 = query();

        Assertions.assertEquals(query1, query2);
    }

    @Test
    public void whenQueryNotEquals() {

        FetchAdminUserTableQuery query = query();

        Assertions.assertNotEquals(query, FetchAdminUserTableQuery.builder().build());
    }

    private FetchAdminUserTableQuery query() {

        return FetchAdminUserTableQuery.builder()
                .email("email")
                .states(new ArrayList<>())
                .types(new ArrayList<>())
                .pageable(PageRequest.of(1, 1))
                .build();
    }
}
