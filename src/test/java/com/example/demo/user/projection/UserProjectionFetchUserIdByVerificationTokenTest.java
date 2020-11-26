package com.example.demo.user.projection;

import com.example.demo.sample.SampleBuilder;
import com.example.demo.user.entity.model.User;
import com.example.demo.user.projection.model.FetchUserIdByVerificationTokenProjection;
import com.example.demo.user.projection.model.FetchUserIdByVerificationTokenQuery;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import javax.transaction.Transactional;

@SpringBootTest
@Transactional
@ActiveProfiles("test")
public class UserProjectionFetchUserIdByVerificationTokenTest {

    @Autowired
    private IUserProjector userProjector;

    @Autowired
    private SampleBuilder sampleBuilder;

    @Test
    public void whenParamIsNullThenExpectException() {

        Assertions.assertThrows(NullPointerException.class, () -> userProjector.fetchUserIdByVerificationToken(null));
    }

    @Test
    public void whenParamHasNullTokenThenExpectException() {

        FetchUserIdByVerificationTokenQuery query = new FetchUserIdByVerificationTokenQuery(null);

        Assertions.assertThrows(IllegalArgumentException.class, () -> userProjector.fetchUserIdByVerificationToken(query));
    }

    @Test
    public void whenEntityExistsThenReturnId() {

        User user = sampleBuilder.builder()
                .user()
                .build()
                .getUser();

        FetchUserIdByVerificationTokenQuery query = new FetchUserIdByVerificationTokenQuery(user.getVerification().getToken());
        FetchUserIdByVerificationTokenProjection projection = userProjector.fetchUserIdByVerificationToken(query);

        Assertions.assertEquals(user.getId(), projection.getId());
    }

    @Test
    public void whenEntityDoesNotExistThenReturnNull() {

        FetchUserIdByVerificationTokenQuery query = new FetchUserIdByVerificationTokenQuery("token");
        FetchUserIdByVerificationTokenProjection projection = userProjector.fetchUserIdByVerificationToken(query);

        Assertions.assertNull(projection);
    }
}
