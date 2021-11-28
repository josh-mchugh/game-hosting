package com.example.demo.web.registration.service;

import com.example.demo.sample.SampleBuilder;
import com.example.demo.user.entity.model.User;
import com.example.demo.web.registration.service.model.ExistsUserByEmailQuery;
import com.example.demo.web.registration.service.model.ExistsUserByEmailResponse;
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
public class RegistrationServiceExistsByEmailTest {

    @Autowired
    private RegistrationService service;

    @Autowired
    private SampleBuilder sampleBuilder;

    @Test
    public void whenParamIsNullThenExpectException() {

        Assertions.assertThrows(UndeclaredThrowableException.class, () -> service.existsByEmail(null));
    }

    @Test
    public void whenUserExistsThenReturnTrue() {

        User user = sampleBuilder.builder()
                .user()
                .build()
                .getUser();

        ExistsUserByEmailResponse response = service.existsByEmail(new ExistsUserByEmailQuery(user.getEmail()));

        Assertions.assertTrue(response.exists());
    }

    @Test
    public void whenUserDoesNotExistThenReturnFalse() {

        ExistsUserByEmailResponse response = service.existsByEmail(new ExistsUserByEmailQuery("test@test"));

        Assertions.assertFalse(response.exists());
    }
}
