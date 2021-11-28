package com.example.demo.framework.security.authentication.service;

import com.example.demo.framework.security.authentication.service.model.FetchAuthFailureByEmailQuery;
import com.example.demo.framework.security.authentication.service.model.FetchAuthFailureByEmailResponse;
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
public class AuthenticationFailureProjectorServiceFetchAuthFailureByEmailTest {

    @Autowired
    private AuthenticationFailureProjectorService service;

    @Autowired
    private SampleBuilder sampleBuilder;

    @Test
    public void whenParamIsNullThenExpectException() {

        Assertions.assertThrows(UndeclaredThrowableException.class, () -> service.fetchAuthFailureByEmail(null));
    }

    @Test
    public void whenParamHasNullEmailThenExpectException() {

        FetchAuthFailureByEmailQuery query = new FetchAuthFailureByEmailQuery(null);
        Assertions.assertThrows(UndeclaredThrowableException.class, () -> service.fetchAuthFailureByEmail(query));
    }

    @Test
    public void whenUserExistsThenExpectUserId() {

        User user = sampleBuilder.builder()
                .user()
                .build()
                .getUser();

        FetchAuthFailureByEmailQuery query = new FetchAuthFailureByEmailQuery(user.getEmail());
        FetchAuthFailureByEmailResponse response = service.fetchAuthFailureByEmail(query);

        Assertions.assertEquals(user.getId(), response.getId());
    }

    @Test
    public void whenUserDoesNotExistsThenReturnNull() {

        FetchAuthFailureByEmailQuery query = new FetchAuthFailureByEmailQuery("test@test");
        FetchAuthFailureByEmailResponse response = service.fetchAuthFailureByEmail(query);

        Assertions.assertNull(response.getId());
    }
}
