package com.example.demo.user.projection;

import com.example.demo.sample.SampleBuilder;
import com.example.demo.user.entity.UserState;
import com.example.demo.user.entity.UserType;
import com.example.demo.user.projection.model.FetchAdminUserPageableProjection;
import com.example.demo.user.projection.model.FetchAdminUserPageableQuery;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.ActiveProfiles;

import javax.transaction.Transactional;
import java.util.Collections;

@SpringBootTest
@Transactional
@ActiveProfiles("test")
public class UserProjectionFetchAdminUserPageableProjectionTest {

    @Autowired
    private IUserProjector userProjector;

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

        Assertions.assertThrows(NullPointerException.class, () -> userProjector.fetchAdminUserPageable(null));
    }

    @Test
    public void whenQueryHasNullPageableThenExpectException() {

        FetchAdminUserPageableQuery query = FetchAdminUserPageableQuery.builder()
                .pageable(null)
                .build();

         Assertions.assertThrows(NullPointerException.class, () -> userProjector.fetchAdminUserPageable(query));
    }

    @Test
    public void whenQueryHasNullEmailThenExpectResults() {

        FetchAdminUserPageableQuery query = FetchAdminUserPageableQuery.builder()
                .email(null)
                .build();

        FetchAdminUserPageableProjection projection = userProjector.fetchAdminUserPageable(query);

        Assertions.assertTrue(projection.getPage().hasContent());
    }

    @Test
    public void whenQueryHasEmailThatDoesNotMatchThenExpectNoResults() {

        FetchAdminUserPageableQuery query = FetchAdminUserPageableQuery.builder()
                .email("noResults")
                .build();

        FetchAdminUserPageableProjection projection = userProjector.fetchAdminUserPageable(query);

        Assertions.assertFalse(projection.getPage().hasContent());
    }

    @Test
    public void whenQueryHasNoSelectedStatesThenExpectResults() {

        FetchAdminUserPageableQuery query = FetchAdminUserPageableQuery.builder()
                .states(null)
                .build();

        FetchAdminUserPageableProjection projection = userProjector.fetchAdminUserPageable(query);

        Assertions.assertTrue(projection.getPage().hasContent());
    }

    @Test
    public void whenQueryHasSelectedStatesThenExpectResults() {

        FetchAdminUserPageableQuery query = FetchAdminUserPageableQuery.builder()
                .states(Collections.singletonList(UserState.ACTIVE))
                .build();

        FetchAdminUserPageableProjection projection = userProjector.fetchAdminUserPageable(query);

        Assertions.assertTrue(projection.getPage().hasContent());
    }

    @Test
    public void whenQueryHasNoSelectedTypesThenExpectResults() {

        FetchAdminUserPageableQuery query = FetchAdminUserPageableQuery.builder()
                .types(null)
                .build();

        FetchAdminUserPageableProjection projection = userProjector.fetchAdminUserPageable(query);

        Assertions.assertTrue(projection.getPage().hasContent());
    }

    @Test
    public void whenQueryHasSelectedTypesThenExpectResults() {

        FetchAdminUserPageableQuery query = FetchAdminUserPageableQuery.builder()
                .types(Collections.singletonList(UserType.ADMIN))
                .build();

        FetchAdminUserPageableProjection projection = userProjector.fetchAdminUserPageable(query);

        Assertions.assertTrue(projection.getPage().hasContent());
    }

    @Test
    public void whenQueryIsUnSortThenExpectIsUnsorted() {

        FetchAdminUserPageableQuery query = FetchAdminUserPageableQuery.builder()
                .pageable(PageRequest.of(0, 20, Sort.unsorted()))
                .build();

        FetchAdminUserPageableProjection projection = userProjector.fetchAdminUserPageable(query);

        Assertions.assertTrue(projection.getPage().getSort().isUnsorted());
    }

    @Test
    public void whenQueryHasEmailSortedThenExpectIsSortedAsc() {

        FetchAdminUserPageableQuery query = FetchAdminUserPageableQuery.builder()
                .pageable(PageRequest.of(0, 20, Sort.by(Sort.Direction.ASC, "email")))
                .build();

        FetchAdminUserPageableProjection projection = userProjector.fetchAdminUserPageable(query);

        Assertions.assertTrue(projection.getPage().getSort().isSorted());
    }

    @Test
    public void whenQueryHasEmailSortedThenExpectIsSortedDesc() {

        FetchAdminUserPageableQuery query = FetchAdminUserPageableQuery.builder()
                .pageable(PageRequest.of(0, 20, Sort.by(Sort.Direction.DESC, "email")))
                .build();

        FetchAdminUserPageableProjection projection = userProjector.fetchAdminUserPageable(query);

        Assertions.assertTrue(projection.getPage().getSort().isSorted());
    }

    @Test
    public void whenQueryHasStateSortedThenExpectIsSortedAsc() {

        FetchAdminUserPageableQuery query = FetchAdminUserPageableQuery.builder()
                .pageable(PageRequest.of(0, 20, Sort.by(Sort.Direction.ASC, "state")))
                .build();

        FetchAdminUserPageableProjection projection = userProjector.fetchAdminUserPageable(query);

        Assertions.assertTrue(projection.getPage().getSort().isSorted());
    }

    @Test
    public void whenQueryHasStateSortedThenExpectIsSortedDesc() {

        FetchAdminUserPageableQuery query = FetchAdminUserPageableQuery.builder()
                .pageable(PageRequest.of(0, 20, Sort.by(Sort.Direction.DESC, "state")))
                .build();

        FetchAdminUserPageableProjection projection = userProjector.fetchAdminUserPageable(query);

        Assertions.assertTrue(projection.getPage().getSort().isSorted());
    }

    @Test
    public void whenQueryHasTypeSortedThenExpectIsSortedAsc() {

        FetchAdminUserPageableQuery query = FetchAdminUserPageableQuery.builder()
                .pageable(PageRequest.of(0, 20, Sort.by(Sort.Direction.ASC, "type")))
                .build();

        FetchAdminUserPageableProjection projection = userProjector.fetchAdminUserPageable(query);

        Assertions.assertTrue(projection.getPage().getSort().isSorted());
    }

    @Test
    public void whenQueryHasTypeSortedThenExpectIsSortedDesc() {

        FetchAdminUserPageableQuery query = FetchAdminUserPageableQuery.builder()
                .pageable(PageRequest.of(0, 20, Sort.by(Sort.Direction.DESC, "type")))
                .build();

        FetchAdminUserPageableProjection projection = userProjector.fetchAdminUserPageable(query);

        Assertions.assertTrue(projection.getPage().getSort().isSorted());
    }

    @Test
    public void whenQueryHasProjectCountSortedThenExpectIsSortedAsc() {

        FetchAdminUserPageableQuery query = FetchAdminUserPageableQuery.builder()
                .pageable(PageRequest.of(0, 20, Sort.by(Sort.Direction.ASC, "projectCount")))
                .build();

        FetchAdminUserPageableProjection projection = userProjector.fetchAdminUserPageable(query);

        Assertions.assertTrue(projection.getPage().getSort().isSorted());
    }

    @Test
    public void whenQueryHasProjectCountSortedThenExpectIsSorted() {

        FetchAdminUserPageableQuery query = FetchAdminUserPageableQuery.builder()
                .pageable(PageRequest.of(0, 20, Sort.by(Sort.Direction.DESC, "projectCount")))
                .build();

        FetchAdminUserPageableProjection projection = userProjector.fetchAdminUserPageable(query);

        Assertions.assertTrue(projection.getPage().getSort().isSorted());
    }
}
