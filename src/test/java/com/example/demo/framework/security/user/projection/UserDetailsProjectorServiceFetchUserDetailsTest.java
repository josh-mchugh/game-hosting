package com.example.demo.framework.security.user.projection;

import com.example.demo.framework.security.user.projection.model.FetchUserDetailsByEmailQuery;
import com.example.demo.framework.security.user.projection.model.FetchUserDetailsByEmailResponse;
import com.example.demo.framework.security.user.projection.projection.UserDetailsProjection;
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
public class UserDetailsProjectorServiceFetchUserDetailsTest {

    @Autowired
    private UserDetailsProjectorService service;

    @Autowired
    private SampleBuilder sampleBuilder;

    @Test
    public void whenParamIsNullThenExpectException() {

        Assertions.assertThrows(UndeclaredThrowableException.class, () -> service.fetchUserDetails(null));
    }

    @Test
    public void whenParamHasNullEmailThenExpectException() {

        FetchUserDetailsByEmailQuery query = new FetchUserDetailsByEmailQuery(null);

        Assertions.assertThrows(UndeclaredThrowableException.class, () -> service.fetchUserDetails(query));
    }

    @Test
    public void whenUserExistsThenReturnProjection() {

        User user = sampleBuilder.builder()
                .user()
                .build()
                .getUser();

        FetchUserDetailsByEmailQuery query = new FetchUserDetailsByEmailQuery(user.getEmail());
        FetchUserDetailsByEmailResponse response = service.fetchUserDetails(query);

        UserDetailsProjection expected = new UserDetailsProjection(user.getEmail(), user.getPassword(), user.getType());

        Assertions.assertEquals(expected, response.getUserDetails());
    }

    @Test
    public void whenUserDoesNotExistThenReturnNull() {

        FetchUserDetailsByEmailQuery query = new FetchUserDetailsByEmailQuery("test@test");
        FetchUserDetailsByEmailResponse response = service.fetchUserDetails(query);

        Assertions.assertNull(response.getUserDetails());
    }
}
