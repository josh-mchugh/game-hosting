package com.example.demo.web.verification.service;

import com.example.demo.sample.SampleBuilder;
import com.example.demo.user.entity.model.User;
import com.example.demo.web.verification.service.model.FetchUserIdByVerificationTokenQuery;
import com.example.demo.web.verification.service.model.FetchUserIdByVerificationTokenResponse;
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
public class VerifyProjectorServiceExistsUserByVerifyTokenTest {

    @Autowired
    private IVerifyProjectorService service;

    @Autowired
    private SampleBuilder sampleBuilder;

    @Test
    public void whenParamIsNullThenExpectException() {

        Assertions.assertThrows(UndeclaredThrowableException.class, () -> service.fetchUserIdByVerificationToken(null));
    }

    @Test
    public void whenParamHasNullTokenThenExpectException() {

        FetchUserIdByVerificationTokenQuery query = new FetchUserIdByVerificationTokenQuery(null);

        Assertions.assertThrows(UndeclaredThrowableException.class, () -> service.fetchUserIdByVerificationToken(query));
    }

    @Test
    public void whenEntityExistsThenReturnId() {

        User user = sampleBuilder.builder()
                .user()
                .build()
                .getUser();

        FetchUserIdByVerificationTokenQuery query = new FetchUserIdByVerificationTokenQuery(user.getVerification().getToken());
        FetchUserIdByVerificationTokenResponse projection = service.fetchUserIdByVerificationToken(query);

        Assertions.assertEquals(user.getId(), projection.getId());
    }

    @Test
    public void whenEntityDoesNotExistThenReturnNull() {

        FetchUserIdByVerificationTokenQuery query = new FetchUserIdByVerificationTokenQuery("token");
        FetchUserIdByVerificationTokenResponse projection = service.fetchUserIdByVerificationToken(query);

        Assertions.assertNull(projection.getId());
    }
}
