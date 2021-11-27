package com.example.demo.web.password.forgot.service;

import com.example.demo.sample.SampleBuilder;
import com.example.demo.user.entity.model.User;
import com.example.demo.web.password.forgot.service.model.FetchUserIdByEmailQuery;
import com.example.demo.web.password.forgot.service.model.FetchUserIdByEmailResponse;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import javax.transaction.Transactional;

@ActiveProfiles("test")
@Transactional
@SpringBootTest
public class ForgotPasswordServiceGetUserByEmailTest {

    @Autowired
    private ForgotPasswordService service;

    @Autowired
    private SampleBuilder sampleBuilder;

    @Test
    public void whenParamIsNullThenExpectException() {

        Assertions.assertThrows(NullPointerException.class, () -> service.getUserIdByEmail(null));
    }

    @Test
    public void whenParamHasNullEmailThenExpectException() {

        FetchUserIdByEmailQuery query = new FetchUserIdByEmailQuery(null);

        Assertions.assertThrows(IllegalArgumentException.class, () -> service.getUserIdByEmail(query));
    }

    @Test
    public void whenUserExistsThenReturnUserId() {

        User user = sampleBuilder.builder()
                .user()
                .build()
                .getUser();

        FetchUserIdByEmailQuery query = new FetchUserIdByEmailQuery(user.getEmail());
        FetchUserIdByEmailResponse response = service.getUserIdByEmail(query);

        Assertions.assertEquals(user.getId(), response.getId());
    }

    @Test
    public void whenUserExistsThenExpectExistsFalse() {

        FetchUserIdByEmailQuery query = new FetchUserIdByEmailQuery("test");
        FetchUserIdByEmailResponse response = service.getUserIdByEmail(query);

        Assertions.assertNull(response.getId());
    }
}
