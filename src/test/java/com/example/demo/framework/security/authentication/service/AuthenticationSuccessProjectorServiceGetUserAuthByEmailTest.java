package com.example.demo.framework.security.authentication.service;

import com.example.demo.framework.security.authentication.service.model.FetchAuthSuccessByEmailQuery;
import com.example.demo.framework.security.authentication.service.model.FetchAuthSuccessByEmailResponse;
import com.example.demo.framework.security.authentication.service.projection.AuthSuccessProjection;
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
public class AuthenticationSuccessProjectorServiceGetUserAuthByEmailTest {

    @Autowired
    private AuthenticationSuccessProjectorService service;

    @Autowired
    private SampleBuilder sampleBuilder;

    @Test
    public void whenParamIsNullThenExpectException() {

        Assertions.assertThrows(UndeclaredThrowableException.class, () -> service.getUserAuthByEmail(null));
    }

    @Test
    public void whenParamHasNullEmailThenExpectException() {

        Assertions.assertThrows(UndeclaredThrowableException.class, () -> service.getUserAuthByEmail(new FetchAuthSuccessByEmailQuery(null)));
    }

    @Test
    public void whenRegularUserExistsThenReturnUserProjection() {

        User user = sampleBuilder.builder()
                .user()
                .build()
                .getUser();

        FetchAuthSuccessByEmailQuery query = new FetchAuthSuccessByEmailQuery(user.getEmail());
        FetchAuthSuccessByEmailResponse response = service.getUserAuthByEmail(query);

        Assertions.assertEquals(new AuthSuccessProjection(user.getId().toString(), false), response.getUser());
    }

    @Test
    public void whenAdminUserExistsThenReturnUserProjection() {

        User user = sampleBuilder.builder()
                .adminUser()
                .build()
                .getUser();

        FetchAuthSuccessByEmailQuery query = new FetchAuthSuccessByEmailQuery(user.getEmail());
        FetchAuthSuccessByEmailResponse response = service.getUserAuthByEmail(query);

        Assertions.assertEquals(new AuthSuccessProjection(user.getId().toString(), true), response.getUser());
    }

    @Test
    public void whenUserDoesNotExistThenReturnNull() {

        FetchAuthSuccessByEmailQuery query = new FetchAuthSuccessByEmailQuery("test@test");
        FetchAuthSuccessByEmailResponse response = service.getUserAuthByEmail(query);

        Assertions.assertNull(response.getUser());
    }
}
