package com.example.demo.user.projection;

import com.example.demo.sample.SampleBuilder;
import com.example.demo.user.entity.model.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import javax.transaction.Transactional;

@SpringBootTest
@Transactional
@ActiveProfiles("test")
public class UserProjectionExistsByVerificationTokenTest {

    @Autowired
    private IUserProjector userProjector;

    @Autowired
    private SampleBuilder sampleBuilder;

    @Test
    public void whenParamIsNullThenExpectException() {

        Assertions.assertThrows(IllegalArgumentException.class, () -> userProjector.existsByVerificationToken(null));
    }

    @Test
    public void whenVerificationTokenExistsThenReturnTrue() {

        User user = sampleBuilder.builder()
                .user()
                .build()
                .getUser();

        Assertions.assertTrue(userProjector.existsByVerificationToken(user.getVerification().getToken()));
    }

    @Test
    public void whenVerificationTokenDoesNotExistThenReturnFalse() {

        Assertions.assertFalse(userProjector.existsByVerificationToken("token"));
    }
}
