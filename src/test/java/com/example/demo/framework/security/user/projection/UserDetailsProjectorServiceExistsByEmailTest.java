package com.example.demo.framework.security.user.projection;

import com.example.demo.framework.security.user.projection.model.ExistsUserByEmailQuery;
import com.example.demo.framework.security.user.projection.model.ExistsUserByEmailResponse;
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
public class UserDetailsProjectorServiceExistsByEmailTest {

    @Autowired
    private IUserDetailsProjectorService service;

    @Autowired
    private SampleBuilder sampleBuilder;

    @Test
    public void whenParamIsNullThenExpectException() {

        Assertions.assertThrows(UndeclaredThrowableException.class, () -> service.existsByEmail(null));
    }

    @Test
    public void whenParamHasNullEmailThenExpectException() {

        ExistsUserByEmailQuery query = new ExistsUserByEmailQuery(null);

        Assertions.assertThrows(UndeclaredThrowableException.class, () -> service.existsByEmail(query));
    }

    @Test
    public void whenUserExistsThenReturnTrue() {

        User user = sampleBuilder.builder()
                .user()
                .build()
                .getUser();

        ExistsUserByEmailQuery query = new ExistsUserByEmailQuery(user.getEmail());
        ExistsUserByEmailResponse response = service.existsByEmail(query);

        Assertions.assertTrue(response.exists());
    }

    @Test
    public void whenUserDoesNotExistThenReturnFalse() {

        ExistsUserByEmailQuery query = new ExistsUserByEmailQuery("test@test");
        ExistsUserByEmailResponse response = service.existsByEmail(query);

        Assertions.assertFalse(response.exists());
    }
}
