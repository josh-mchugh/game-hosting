package com.example.demo.web.password.forgot.service;

import com.example.demo.sample.SampleBuilder;
import com.example.demo.user.entity.model.User;
import com.example.demo.web.password.forgot.service.model.ExistsUserByEmailQuery;
import com.example.demo.web.password.forgot.service.model.ExistsUserByEmailResponse;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import javax.transaction.Transactional;

@ActiveProfiles("test")
@Transactional
@SpringBootTest
public class ForgotPasswordQueryServiceExistsByEmailTest {

    @Autowired
    private ForgotPasswordQueryService service;

    @Autowired
    private SampleBuilder sampleBuilder;

    @Test
    public void whenParamIsNullThenExpectException() {

        Assertions.assertThrows(NullPointerException.class, () -> service.existsByEmail(null));
    }

    @Test
    public void whenParamHasNullEmailThenExpectException() {

        ExistsUserByEmailQuery query = new ExistsUserByEmailQuery(null);

        Assertions.assertThrows(IllegalArgumentException.class, () -> service.existsByEmail(query));
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
    public void whenUserExistsThenExpectExistsFalse() {

        ExistsUserByEmailQuery query = new ExistsUserByEmailQuery("test");
        ExistsUserByEmailResponse response = service.existsByEmail(query);

        Assertions.assertFalse(response.exists());
    }
}
