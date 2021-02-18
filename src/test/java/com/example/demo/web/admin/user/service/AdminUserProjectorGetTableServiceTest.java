package com.example.demo.web.admin.user.service;

import com.example.demo.sample.SampleBuilder;
import com.example.demo.user.entity.UserState;
import com.example.demo.user.entity.UserType;
import com.example.demo.web.admin.user.service.model.FetchAdminUserTableQuery;
import com.example.demo.web.admin.user.service.model.FetchAdminUserTableResponse;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.ActiveProfiles;

import javax.transaction.Transactional;
import java.lang.reflect.UndeclaredThrowableException;
import java.util.Collections;

@SpringBootTest
@Transactional
@ActiveProfiles("test")
public class AdminUserProjectorGetTableServiceTest {

    @Autowired
    private IAdminUserProjectorService service;

    @Autowired
    private SampleBuilder sampleBuilder;

    @BeforeEach
    public void setup() {

        sampleBuilder.builder()
                .user()
                .adminUser()
                .build();
    }

    @Test
    public void whenQueryIsThenNullThenThrowException() {

        Assertions.assertThrows(UndeclaredThrowableException.class, () -> service.getTable(null));
    }

    @Test
    public void whenQueryHasNullPageableThenExpectException() {

        FetchAdminUserTableQuery query = FetchAdminUserTableQuery.builder()
                .pageable(null)
                .build();

        Assertions.assertThrows(UndeclaredThrowableException.class, () -> service.getTable(query));
    }

    @Test
    public void whenQueryHasNullEmailThenExpectResults() {

        FetchAdminUserTableQuery query = FetchAdminUserTableQuery.builder()
                .email(null)
                .build();

        FetchAdminUserTableResponse projection = service.getTable(query);

        Assertions.assertTrue(projection.getPage().hasContent());
    }

    @Test
    public void whenQueryHasEmailThatDoesNotMatchThenExpectNoResults() {

        FetchAdminUserTableQuery query = FetchAdminUserTableQuery.builder()
                .email("noResults")
                .build();

        FetchAdminUserTableResponse projection = service.getTable(query);

        Assertions.assertFalse(projection.getPage().hasContent());
    }

    @Test
    public void whenQueryHasNoSelectedStatesThenExpectResults() {

        FetchAdminUserTableQuery query = FetchAdminUserTableQuery.builder()
                .states(null)
                .build();

        FetchAdminUserTableResponse projection = service.getTable(query);

        Assertions.assertTrue(projection.getPage().hasContent());
    }

    @Test
    public void whenQueryHasSelectedStatesThenExpectResults() {

        FetchAdminUserTableQuery query = FetchAdminUserTableQuery.builder()
                .states(Collections.singletonList(UserState.ACTIVE))
                .build();

        FetchAdminUserTableResponse projection = service.getTable(query);

        Assertions.assertTrue(projection.getPage().hasContent());
    }

    @Test
    public void whenQueryHasNoSelectedTypesThenExpectResults() {

        FetchAdminUserTableQuery query = FetchAdminUserTableQuery.builder()
                .types(null)
                .build();

        FetchAdminUserTableResponse projection = service.getTable(query);

        Assertions.assertTrue(projection.getPage().hasContent());
    }

    @Test
    public void whenQueryHasSelectedTypesThenExpectResults() {

        FetchAdminUserTableQuery query = FetchAdminUserTableQuery.builder()
                .types(Collections.singletonList(UserType.ADMIN))
                .build();

        FetchAdminUserTableResponse projection = service.getTable(query);

        Assertions.assertTrue(projection.getPage().hasContent());
    }

    @Test
    public void whenQueryIsUnSortThenExpectIsUnsorted() {

        FetchAdminUserTableQuery query = FetchAdminUserTableQuery.builder()
                .pageable(PageRequest.of(0, 20, Sort.unsorted()))
                .build();

        FetchAdminUserTableResponse projection = service.getTable(query);

        Assertions.assertTrue(projection.getPage().getSort().isUnsorted());
    }

    @Test
    public void whenQueryHasEmailSortedThenExpectIsSortedAsc() {

        FetchAdminUserTableQuery query = FetchAdminUserTableQuery.builder()
                .pageable(PageRequest.of(0, 20, Sort.by(Sort.Direction.ASC, "email")))
                .build();

        FetchAdminUserTableResponse projection = service.getTable(query);

        Assertions.assertTrue(projection.getPage().getSort().isSorted());
    }

    @Test
    public void whenQueryHasEmailSortedThenExpectIsSortedDesc() {

        FetchAdminUserTableQuery query = FetchAdminUserTableQuery.builder()
                .pageable(PageRequest.of(0, 20, Sort.by(Sort.Direction.DESC, "email")))
                .build();

        FetchAdminUserTableResponse projection = service.getTable(query);

        Assertions.assertTrue(projection.getPage().getSort().isSorted());
    }

    @Test
    public void whenQueryHasStateSortedThenExpectIsSortedAsc() {

        FetchAdminUserTableQuery query = FetchAdminUserTableQuery.builder()
                .pageable(PageRequest.of(0, 20, Sort.by(Sort.Direction.ASC, "state")))
                .build();

        FetchAdminUserTableResponse projection = service.getTable(query);

        Assertions.assertTrue(projection.getPage().getSort().isSorted());
    }

    @Test
    public void whenQueryHasStateSortedThenExpectIsSortedDesc() {

        FetchAdminUserTableQuery query = FetchAdminUserTableQuery.builder()
                .pageable(PageRequest.of(0, 20, Sort.by(Sort.Direction.DESC, "state")))
                .build();

        FetchAdminUserTableResponse projection = service.getTable(query);

        Assertions.assertTrue(projection.getPage().getSort().isSorted());
    }

    @Test
    public void whenQueryHasTypeSortedThenExpectIsSortedAsc() {

        FetchAdminUserTableQuery query = FetchAdminUserTableQuery.builder()
                .pageable(PageRequest.of(0, 20, Sort.by(Sort.Direction.ASC, "type")))
                .build();

        FetchAdminUserTableResponse projection = service.getTable(query);

        Assertions.assertTrue(projection.getPage().getSort().isSorted());
    }

    @Test
    public void whenQueryHasTypeSortedThenExpectIsSortedDesc() {

        FetchAdminUserTableQuery query = FetchAdminUserTableQuery.builder()
                .pageable(PageRequest.of(0, 20, Sort.by(Sort.Direction.DESC, "type")))
                .build();

        FetchAdminUserTableResponse projection = service.getTable(query);

        Assertions.assertTrue(projection.getPage().getSort().isSorted());
    }

    @Test
    public void whenQueryHasProjectCountSortedThenExpectIsSortedAsc() {

        FetchAdminUserTableQuery query = FetchAdminUserTableQuery.builder()
                .pageable(PageRequest.of(0, 20, Sort.by(Sort.Direction.ASC, "projectCount")))
                .build();

        FetchAdminUserTableResponse projection = service.getTable(query);

        Assertions.assertTrue(projection.getPage().getSort().isSorted());
    }

    @Test
    public void whenQueryHasProjectCountSortedThenExpectIsSorted() {

        FetchAdminUserTableQuery query = FetchAdminUserTableQuery.builder()
                .pageable(PageRequest.of(0, 20, Sort.by(Sort.Direction.DESC, "projectCount")))
                .build();

        FetchAdminUserTableResponse projection = service.getTable(query);

        Assertions.assertTrue(projection.getPage().getSort().isSorted());
    }
}
