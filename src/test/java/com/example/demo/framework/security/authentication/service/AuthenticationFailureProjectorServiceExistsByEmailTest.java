package com.example.demo.framework.security.authentication.service;

import com.example.demo.framework.security.authentication.service.model.ExistsUserByEmailQuery;
import com.example.demo.framework.security.authentication.service.model.ExistsUserByEmailResponse;
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
public class AuthenticationFailureProjectorServiceExistsByEmailTest {

    @Autowired
    private AuthenticationFailureProjectorService service;

    @Autowired
    private SampleBuilder sampleBuilder;

    @Test
    public void whenParamIsNullThenExpectException() {

        Assertions.assertThrows(UndeclaredThrowableException.class, () -> service.existsByEmail(null));
    }

    @Test
    public void whenParamHasNullEmailThenExpectException() {

        Assertions.assertThrows(UndeclaredThrowableException.class, () -> service.existsByEmail(new ExistsUserByEmailQuery(null)));
    }

    @Test
    public void whenUserExistsThenExpectExistsTrue() {

        User user = sampleBuilder.builder()
                .user()
                .build()
                .getUser();

        ExistsUserByEmailQuery query = new ExistsUserByEmailQuery(user.getEmail());
        ExistsUserByEmailResponse response = service.existsByEmail(query);

        Assertions.assertTrue(response.exists());
    }

    @Test
    public void whenUserDoesNotExistsThenExpectExistsFalse() {

        ExistsUserByEmailQuery query = new ExistsUserByEmailQuery("test@test");
        ExistsUserByEmailResponse response = service.existsByEmail(query);

        Assertions.assertFalse(response.exists());
    }
}
