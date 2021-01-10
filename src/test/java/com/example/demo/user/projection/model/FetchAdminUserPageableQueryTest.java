package com.example.demo.user.projection.model;

import com.example.demo.user.entity.UserState;
import com.example.demo.user.entity.UserType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;
import java.util.Collections;

public class FetchAdminUserPageableQueryTest {

    @Test
    public void whenQueryHasEmailThenReturnEmail() {

        FetchAdminUserPageableQuery query = FetchAdminUserPageableQuery.builder()
                .email("email")
                .build();

        Assertions.assertEquals("email", query.getEmail());
    }

    @Test
    public void whenQueryHasUserStatesThenReturnUserStates() {

        FetchAdminUserPageableQuery query = FetchAdminUserPageableQuery.builder()
                .states(Collections.singletonList(UserState.ACTIVE))
                .build();

        Assertions.assertEquals(Collections.singletonList(UserState.ACTIVE), query.getStates());
    }

    @Test
    public void whenQueryHasTypesThenReturnTypes() {

        FetchAdminUserPageableQuery query = FetchAdminUserPageableQuery.builder()
                .types(Collections.singletonList(UserType.ADMIN))
                .build();

        Assertions.assertEquals(Collections.singletonList(UserType.ADMIN), query.getTypes());
    }

    @Test
    public void whenQueryHasPageableThenReturnPageable() {

        Pageable pageable = PageRequest.of(1, 1);

        FetchAdminUserPageableQuery query = FetchAdminUserPageableQuery.builder()
                .pageable(pageable)
                .build();

        Assertions.assertEquals(pageable, query.getPageable());
    }

    @Test
    public void whenQueryToString() {

        FetchAdminUserPageableQuery query = query();

        String expected = "FetchAdminUserPageableQuery(email=email, states=[], types=[], pageable=Page request [number: 1, size 1, sort: UNSORTED])";

        Assertions.assertEquals(expected, query.toString());
    }

    @Test
    public void whenQueryHashCode() {

        FetchAdminUserPageableQuery query = query();

        Assertions.assertEquals(863104872, query.hashCode());
    }

    @Test
    public void whenQueryEquals() {

        FetchAdminUserPageableQuery query1 = query();
        FetchAdminUserPageableQuery query2 = query();

        Assertions.assertEquals(query1, query2);
    }

    @Test
    public void whenQueryNotEquals() {

        FetchAdminUserPageableQuery query = query();

        Assertions.assertNotEquals(query, FetchAdminUserPageableQuery.builder().build());
    }

    private FetchAdminUserPageableQuery query() {

        return FetchAdminUserPageableQuery.builder()
                .email("email")
                .states(new ArrayList<>())
                .types(new ArrayList<>())
                .pageable(PageRequest.of(1, 1))
                .build();
    }
}
