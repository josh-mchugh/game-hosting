package com.example.demo.web.verification.service;

import com.example.demo.sample.SampleBuilder;
import com.example.demo.user.entity.model.User;
import com.example.demo.web.verification.service.model.ExistsUserByVerifyTokenQuery;
import com.example.demo.web.verification.service.model.ExistsUserByVerifyTokenResponse;
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
public class VerificationServiceFetchUserIdByVerificationTokenTest {

    @Autowired
    private VerifyProjectorService service;

    @Autowired
    private SampleBuilder sampleBuilder;

    @Test
    public void whenParamIsNullThenExpectException() {

        Assertions.assertThrows(UndeclaredThrowableException.class, () -> service.existsByToken(null));
    }

    @Test
    public void whenVerificationTokenExistsThenReturnTrue() {

        User user = sampleBuilder.builder()
                .user()
                .build()
                .getUser();

        ExistsUserByVerifyTokenResponse response = service.existsByToken(new ExistsUserByVerifyTokenQuery(user.getVerification().getToken()));

        Assertions.assertTrue(response.isExist());
    }

    @Test
    public void whenVerificationTokenDoesNotExistThenReturnFalse() {

        ExistsUserByVerifyTokenResponse response = service.existsByToken(new ExistsUserByVerifyTokenQuery("token"));

        Assertions.assertFalse(response.isExist());
    }
}
