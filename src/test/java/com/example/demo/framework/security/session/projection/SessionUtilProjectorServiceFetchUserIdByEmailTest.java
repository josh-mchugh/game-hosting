package com.example.demo.framework.security.session.projection;

import com.example.demo.framework.security.session.projection.model.FetchUserIdByEmailQuery;
import com.example.demo.framework.security.session.projection.model.FetchUserIdByEmailResponse;
import com.example.demo.sample.SampleBuilder;
import com.example.demo.user.entity.model.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import javax.transaction.Transactional;
import java.lang.reflect.UndeclaredThrowableException;

@SpringBootTest
@Transactional
@ActiveProfiles("test")
public class SessionUtilProjectorServiceFetchUserIdByEmailTest {

    @Autowired
    private ISessionUtilProjectorService service;

    @Autowired
    private SampleBuilder sampleBuilder;

    @Test
    public void whenParamIsNullThenExpectException() {

        Assertions.assertThrows(UndeclaredThrowableException.class, () -> service.fetchUserIdByEmail(null));
    }

    @Test
    public void whenParamHasNullEmailThenExpectException() {

        FetchUserIdByEmailQuery query = new FetchUserIdByEmailQuery(null);

        Assertions.assertThrows(UndeclaredThrowableException.class, () -> service.fetchUserIdByEmail(query));
    }

    @Test
    public void whenUserExistsThenExpectUserId() {

        User user = sampleBuilder.builder()
                .user()
                .build()
                .getUser();

        FetchUserIdByEmailQuery query = new FetchUserIdByEmailQuery(user.getEmail());
        FetchUserIdByEmailResponse response = service.fetchUserIdByEmail(query);

        Assertions.assertEquals(user.getId(), response.getId());
    }

    @Test
    public void whenUserDoesNotExistsThenExpectNull() {

        FetchUserIdByEmailQuery query = new FetchUserIdByEmailQuery("test@test");
        FetchUserIdByEmailResponse response = service.fetchUserIdByEmail(query);

        Assertions.assertNull(response.getId());
    }
}
