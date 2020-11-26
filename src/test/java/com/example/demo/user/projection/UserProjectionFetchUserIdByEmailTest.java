package com.example.demo.user.projection;

import com.example.demo.sample.SampleBuilder;
import com.example.demo.user.entity.model.User;
import com.example.demo.user.projection.model.FetchUserIdByEmailProjection;
import com.example.demo.user.projection.model.FetchUserIdByEmailQuery;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import javax.transaction.Transactional;

@SpringBootTest
@Transactional
@ActiveProfiles("test")
public class UserProjectionFetchUserIdByEmailTest {

    @Autowired
    private IUserProjector userProjector;

    @Autowired
    private SampleBuilder sampleBuilder;

    @Test
    public void whenParamIsNullThenExpectException() {

        Assertions.assertThrows(NullPointerException.class, () -> userProjector.fetchUserIdByEmail(null));
    }

    @Test
    public void whenParamHasNullEmailThenExpectException() {

        FetchUserIdByEmailQuery query = new FetchUserIdByEmailQuery(null);

        Assertions.assertThrows(IllegalArgumentException.class, () -> userProjector.fetchUserIdByEmail(query));
    }

    @Test
    public void whenEntityExistsThenReturnUserId() {

        User user = sampleBuilder.builder()
                .user()
                .build()
                .getUser();

        FetchUserIdByEmailQuery query = new FetchUserIdByEmailQuery(user.getEmail());
        FetchUserIdByEmailProjection projection = userProjector.fetchUserIdByEmail(query);

        Assertions.assertEquals(user.getId(), projection.getId());
    }

    @Test
    public void whenEntityDoesNotExistThenReturnNull() {

        FetchUserIdByEmailQuery query = new FetchUserIdByEmailQuery("test@test");
        FetchUserIdByEmailProjection projection = userProjector.fetchUserIdByEmail(query);

        Assertions.assertNull(projection);
    }
}
